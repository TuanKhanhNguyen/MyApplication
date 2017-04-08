package com.example.ntkhanh.mvpdaggerclean.injection.module;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.ntkhanh.mvpdaggerclean.BuildConfig;
import com.example.ntkhanh.mvpdaggerclean.MyApplication;
import com.example.ntkhanh.mvpdaggerclean.UseCaseHandler;
import com.example.ntkhanh.mvpdaggerclean.UseCaseThreadPoolScheduler;
import com.example.ntkhanh.mvpdaggerclean.util.schedulers.SchedulerProvider;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ntkhanh on 3/19/17.
 */

@Module (includes = NetModule.class)
public class ApplicationModule {
    private final MyApplication mMyApplication;

    public ApplicationModule(MyApplication mMyApplication) {
        this.mMyApplication = mMyApplication;
    }

    @Provides
    @Singleton
    MyApplication provideApplicationModule() {
        return mMyApplication;
    }

    @Provides
    @Singleton
    UseCaseHandler provideUseCaseHandler() {
        return new UseCaseHandler(new UseCaseThreadPoolScheduler());
    }

    @Provides
    @Singleton
    SchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }

    @Provides
    @Singleton
    NetModule provideNetModule() {
        return new NetModule();
    }

}
