package com.example.rw18;

import android.app.Application;

import com.mob.MobSDK;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MobSDK.init(this);
    }
}
