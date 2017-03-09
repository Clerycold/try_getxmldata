package com.example.clery.try_getxmldata;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by clery on 2017/2/9.
 */

public class SaveData {

    public final static String SETTING = "getSharedPreferences";

    private SharedPreferences settings;
    private Context context;

    private static SaveData saveData;

    private SaveData(Context context){

        this.context=context;

    }

    public static SaveData getsaveData(Context context) {
        if (null == saveData) {
            saveData = new SaveData(context);
        }
        return saveData;
    }

    public void saveBooleanData(String filename,Boolean data){
        settings = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        editor.putBoolean(filename,data);
        editor.apply();
    }
    public Boolean readDataBoolean(String filename){
        settings = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        //取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        Boolean aBoolean= settings.getBoolean(filename,true);

        return aBoolean;
    }
    public void saveStringData(String filename,String string){
        settings = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString(filename,string);
        editor.apply();
    }
    public String getStringData(String filename){
        settings = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        //取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        String aString= settings.getString(filename,"");

        return aString;
    }
    public void setStringArray(String filename,String[] string){
        settings = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        int size = string.length;
        putIntValue(context,filename + "size", size);
        for (int i=0,j=string.length;i<j;i++){
           editor.putString(filename+i,string[i]);
        }
        editor.apply();
    }
    public String[] getStringArryData(String filename){
        settings = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        int size = getIntValue(context,filename + "size", 0);
        String[] temp = new String[size];
        for(int i=0;i<size;i++){
            temp[i] = settings.getString(filename+i,"");
        }
        return temp;
    }
    private static int getIntValue(Context context,String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(SETTING,
                Context.MODE_PRIVATE);
        int value = sp.getInt(key, defValue);
        return value;
    }
    private static void putIntValue(Context context, String key, int value) {
        SharedPreferences.Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE)
                .edit();
        sp.putInt(key, value);
        sp.commit();
    }
}