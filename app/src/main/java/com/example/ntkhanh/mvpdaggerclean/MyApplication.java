package com.example.ntkhanh.mvpdaggerclean;

import android.app.Application;

import com.example.ntkhanh.mvpdaggerclean.injection.component.ApplicationComponent;
import com.example.ntkhanh.mvpdaggerclean.injection.component.DaggerApplicationComponent;
import com.example.ntkhanh.mvpdaggerclean.injection.module.ApplicationModule;


/**
 * Created by ntkhanh on 3/19/17.
 */

public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }


    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static MyApplication getInstance() {
        return sInstance;
    }

}
