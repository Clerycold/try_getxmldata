package com.example.clery.try_getxmldata;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by clery on 2017/2/8.
 */

public class Oilxmlpattern extends RelativeLayout{

    OilUIpattern oil98;
    OilUIpattern oil95;
    OilUIpattern oil92;
    OilUIpattern oil00;

    TextView oilupdata;

    public Oilxmlpattern(Context context) {
        super(context);
        initView(context);
        xmlArrange();
        oildata();
    }

    public Oilxmlpattern(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        xmlArrange();
        oildata();
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.oilxmlpattern, this,true);

        oil98 = (OilUIpattern)findViewById(R.id.oil98);
        oil95 = (OilUIpattern)findViewById(R.id.oil95);
        oil92 = (OilUIpattern)findViewById(R.id.oil92);
        oil00 = (OilUIpattern)findViewById(R.id.oil00);
        oilupdata = (TextView)findViewById(R.id.oilupdata);
    }

    private void xmlArrange() {

        RelativeLayout.LayoutParams oil98_lay = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        oil98_lay.setMargins((int)(ScreenWH.getScreenWidth()/6.4),0,0,0);
        oil98.setLayoutParams(oil98_lay);

        RelativeLayout.LayoutParams oil95_lay = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        oil95_lay.addRule(RelativeLayout.RIGHT_OF,oil98.getId());
        oil95_lay.setMargins(ScreenWH.getScreenWidth()/20,0,0,0);
        oil95.setLayoutParams(oil95_lay);

        RelativeLayout.LayoutParams oil92_lay = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        oil92_lay.addRule(RelativeLayout.BELOW,oil98.getId());
        oil92_lay.setMargins((int)(ScreenWH.getScreenWidth()/6.4),ScreenWH.getScreenWidth()/20,0,0);
        oil92.setLayoutParams(oil92_lay);

        RelativeLayout.LayoutParams oil00_lay = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        oil00_lay.addRule(RelativeLayout.BELOW,oil95.getId());
        oil00_lay.addRule(RelativeLayout.RIGHT_OF,oil92.getId());
        oil00_lay.setMargins(ScreenWH.getScreenWidth()/20,ScreenWH.getScreenWidth()/20,0,0);
        oil00.setLayoutParams(oil00_lay);

        RelativeLayout.LayoutParams oilupdata_lay = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        oilupdata_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        oilupdata_lay.addRule(RelativeLayout.BELOW,oil00.getId());
        oilupdata_lay.setMargins(0,24,0,0);
        oilupdata.setLayoutParams(oilupdata_lay);
    }
    private void oildata() {
        oil98.setTextname("98無鉛");
        oil98.setTextOilnameBgColor(getResources().getColor(R.color.oil98));
        oil95.setTextname("95無鉛");
        oil95.setTextOilnameBgColor(getResources().getColor(R.color.oil95));
        oil92.setTextname("92無鉛");
        oil92.setTextOilnameBgColor(getResources().getColor(R.color.oil92));
        oil00.setTextname("超級柴油");
        oil00.setTextOilnameBgColor(getResources().getColor(R.color.oil00));
    }
    public void setoilprice(String[] oilprice){

        oil92.setTextPrice(oilprice[0]);
        oil95.setTextPrice(oilprice[1]);
        oil98.setTextPrice(oilprice[2]);
        oil00.setTextPrice(oilprice[3]);
        oilupdata.setText(oilprice[4]);

    }
}
