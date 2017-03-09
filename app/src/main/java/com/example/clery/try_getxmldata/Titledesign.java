package com.example.clery.try_getxmldata;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by clery on 2017/2/9.
 */

public class Titledesign extends RelativeLayout {

    private TextView textView;
    int Width;
    int Height;

    public Titledesign(Context context, AttributeSet attrs) {
        super(context, attrs);
        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(context.getApplicationContext());

        textView = new TextView(context);

        RelativeLayout.LayoutParams r_title_lay =new RelativeLayout.LayoutParams((int)(double)Width,(int)(double)Height/10);
        textView.setLayoutParams(r_title_lay);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        textView.setTextColor(getResources().getColor(R.color.colorBlack));
        this.addView(textView);

    }
    public void setTextView(String string){
        textView.setText(string);
    }
}
