package com.example.clery.try_getxmldata;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends Activity implements Runnable{

    MyHandler myHandler;
    SaveData saveData;

    Titledesign activitytitle;
    TextView oilchinese ,oilfpcc;
    Oilxmlpattern oilchineseprice ,oilfpccprice;
    TextView oilprediction ,oilpredictionprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        new Thread(this).start();

    }

    private void initView(){

        myHandler = MyHandler.getmyHandler(this);
        saveData = SaveData.getsaveData(getApplicationContext());

        activitytitle = (Titledesign)findViewById(R.id.activitytitle);
        activitytitle.setTextView("即時油價");

        oilprediction = (TextView)findViewById(R.id.oilprediction);
        oilpredictionprice = (TextView)findViewById(R.id.oilpredictionprice);
        oilchinese = (TextView)findViewById(R.id.oilchinese);
        oilchineseprice = (Oilxmlpattern)findViewById(R.id.oilchineseprice);
        oilfpcc = (TextView)findViewById(R.id.oilfpcc);
        oilfpccprice = (Oilxmlpattern)findViewById(R.id.oilfpccprice);
    }

    //中國石油 油價
    private void ChineseOil() throws Exception {

        String[] chineseoil = new String[5];

        Document xmlDoc = Jsoup.connect("http://new.cpc.com.tw/Home/").get();

        Element elements = xmlDoc.getElementById("OilPrice2");

        for (int i =0;i<4;i++){
            if(i == 3){
                chineseoil[i] = "$"+elements.select("strong").get(i+1).text();
            }else{
                chineseoil[i] = "$"+elements.select("strong").get(i).text();
            }
        }
        chineseoil[4] = elements.select("dt").text();

        saveData.setStringArray("chineseoil",chineseoil);

        setMessage(0,chineseoil);
    }
    //台塑石油油價
    private void FpccOil()throws Exception{

        String[] fpccoil = new String[5];

        Document xmlDoc = Jsoup.connect("http://www.fpcc.com.tw/tc/affiliate.php").get();

        Elements elements = null;
        for(int i=1;i<=4;i++){
            if(i == 1){
                elements = xmlDoc.select("div.GasPrice"+i);
                fpccoil[2] = elements.select("p.pricing").get(0).text();
            }else if(i == 3){
                elements = xmlDoc.select("div.GasPrice"+i);
                fpccoil[0] = elements.select("p.pricing").get(0).text();
            }else{
                elements = xmlDoc.select("div.GasPrice"+i);
                fpccoil[i-1] = elements.select("p.pricing").get(0).text();
            }
        }

        elements = xmlDoc.select("p.effective_blue");
        fpccoil[4] = elements.get(0).text();

        saveData.setStringArray("fpccoil",fpccoil);

        setMessage(1,fpccoil);

    }
    //每周油價預測
    private void OilPrediction()throws Exception{

        Document xmlDoc = Jsoup.connect("http://gas.goodlife.tw/").get();

        Elements elements = xmlDoc.select("div.panel");
        String[] oilnextEx = elements.select("li.main").select("p").text().split(",");
        String oilnextPrice = elements.select("li.main").select("h2").text();

        saveData.setStringArray("oilnextEx",oilnextEx);
        saveData.saveStringData("oilnextPrice",oilnextPrice);

        setMessage(2,oilnextEx,oilnextPrice);
    }
    private void setMessage(int msg,String[] stringarray){

        Message message = new Message();
        message.what = msg;
        message.obj = stringarray;

        myHandler.sendMessage(message);
    }
    private void setMessage(int msg,String[] stringarray,String string){
        Message message = new Message();
        message.what = msg;

        Bundle bundle = new Bundle();
        bundle.putStringArray("oilnextEx",stringarray);
        bundle.putString("oilnextPrice", string);
        message.setData(bundle);

        myHandler.sendMessage(message);
    }
    private boolean compareData(String oldDate,String newDate){
        Date d1 = java.sql.Date.valueOf(oldDate);
        Date d2 = java.sql.Date.valueOf(newDate);
        return d1.after(d2) || d1.equals(d2);
    }
    public void setoilchineseprice(String[] chineseoil){
        oilchineseprice.setoilprice(chineseoil);
    }
    public void setOilfpccprice(String[] fpccoil){
        oilfpccprice.setoilprice(fpccoil);
    }
    public void setOilprediction(String[] oilnextEx,String oilnextPrice){
        if(oilnextEx.length == 1){
            oilprediction.setText(oilnextEx[0]);
        }else if(oilnextEx.length == 2){
            oilprediction.setText(oilnextEx[0] + "\n" + "\n" + oilnextEx[1] );
        }else{
            oilprediction.setText("無法取得資料");
        }

        oilpredictionprice.setText(oilnextPrice);
        oilpredictionprice.setTextColor(getPrediction(oilnextPrice));
    }
    //預測下周油價顏色
    private int getPrediction(String s){
        if(String.valueOf(s.charAt(0)).equals("降"))return getResources().getColor(R.color.oil95);
        else if(String.valueOf(s.charAt(0)).equals("升"))return getResources().getColor(R.color.oil92);
        else return getResources().getColor(R.color.oilPriceGray);
    }
    //設定下次油價更新的日期
    public void setNextUpData(){
        saveData.saveStringData("nextUpData",getNextMonday());
        if(saveData.readDataBoolean("isFirstUpData")){
            saveData.saveBooleanData("isFirstUpData",false);
        }
    }

    /**如果是第一次登入 或者 當天開啟的日期 超過 上次登入後的星期一日期 重新下載
     * 其它都去更新 下周油價浮動預測
     */
    @Override
    public void run() {
        try {
            if(saveData.readDataBoolean("isFirstUpData") ||
                    compareData(getNowTime("yyyy-MM-dd"),saveData.getStringData("nextUpData"))){
                ChineseOil();
                FpccOil();
                OilPrediction();
            }else{

                setoilchineseprice(saveData.getStringArryData("chineseoil"));
                setOilfpccprice(saveData.getStringArryData("fpccoil"));
                setOilprediction(saveData.getStringArryData("oilnextEx"),saveData.getStringData("oilnextPrice"));

                OilPrediction();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *使用弱引用 以免發生內存遺漏的可能
     */
    private static class MyHandler extends Handler {
        private final WeakReference<Activity> mActivity;
        int i = 0;

        private static MyHandler myHandler;

        static MyHandler getmyHandler(Activity activity) {
            if (null == myHandler) {
                myHandler = new MyHandler(activity);
            }
            return myHandler;
        }

        private MyHandler(Activity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }
        //msg.what 0：更新中國石油 1：更新台塑石油 2：更新浮動油價預測 當三個都更新後才正式儲存下次更新日期
        @Override
        public void handleMessage(Message msg) {
            MainActivity mainActivity = (MainActivity) mActivity.get();
            if (mainActivity!= null) {
               switch (msg.what){
                   case 0:
                       mainActivity.setoilchineseprice((String[])msg.obj);
                       break;
                   case 1:
                       mainActivity.setOilfpccprice((String[])msg.obj);
                       break;
                   case 2:
                       Bundle bundle = msg.getData();
                       mainActivity.setOilprediction(bundle.getStringArray("oilnextEx"),bundle.getString("oilnextPrice"));
                       break;
               }
                i++;
                if(i == 3){
                    mainActivity.setNextUpData();

                }
            }
        }
    }
    //取得當天的星期
    private int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 獲得今天是一周的第幾天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中國禮拜一作为第一天所以這裏減1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }
    //取得當天的下星期一的日期
    private String getNextMonday() {
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
        Date monday = currentDate.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(monday);
    }
    //取得當天的日期
    private String getNowTime(String dateformat) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
        return dateFormat.format(now);
    }
}
