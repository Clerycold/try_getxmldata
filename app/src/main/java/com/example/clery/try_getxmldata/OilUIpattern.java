package com.example.clery.try_getxmldata;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by clery on 2017/2/8.
 */

public class OilUIpattern extends RelativeLayout{

    TextView textprice;
    TextView textOilname;

    public OilUIpattern(Context context) {
        super(context);

        initView(context);
    }

    public OilUIpattern(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }

    private void initView(Context context){

        int Heigt = ScreenWH.getNoStatus_bar_Height(context);

        LayoutInflater.from(context).inflate(R.layout.oiluipattern, this,true);

        textprice = (TextView)findViewById(R.id.textprice) ;
        textOilname = (TextView)findViewById(R.id.textOilname);

        RelativeLayout.LayoutParams textprice_lay = new RelativeLayout.LayoutParams((int)(double)((Heigt/10)*2),(int)(double)((Heigt/10)*1.5));
        textprice.setLayoutParams(textprice_lay);

        RelativeLayout.LayoutParams textname_lay = new RelativeLayout.LayoutParams((int)(double)((Heigt/10)*2),(int)(double)((Heigt/10)*0.5));
        textname_lay.addRule(RelativeLayout.BELOW,textprice.getId());
        textOilname.setLayoutParams(textname_lay);

    }
    public void setTextPrice(String price){
        textprice.setText(price);
    }
    public void setTextname(String oilName){
        textOilname.setText(oilName);
    }
    public void setTextOilnameBgColor(int color){
        textOilname.setBackgroundColor(color);
    }
}
