package com.example.xz.mytelegraphapp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by xz on 16/05/2018.
 * build myapplication context for application
 * build the LeakCanary to check memory leak
 */
public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    private void setInstance(MyApplication instance) {
        MyApplication.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);

//        initLeakCanary();
    }


    //test any memory leak appear
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

}
