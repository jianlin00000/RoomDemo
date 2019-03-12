package com.demo.application;

import android.app.Application;

/**
 *@author: cjl
 *@date: 2018/10/17 16:54
 *@desc:
 */
public class MyApplication
        extends Application {
    private static MyApplication mInstance=null;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
//        Pandora.get().disableShakeSwitch();
    }


    public static MyApplication getContext(){
        return mInstance;
    }
}
