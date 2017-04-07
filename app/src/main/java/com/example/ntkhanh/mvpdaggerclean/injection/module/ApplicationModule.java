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

@Module
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

//    @Provides
//    @Singleton
//    NetModule provideNetModule() {
//        return new NetModule("http://jsonplaceholder.typicode.com/", MyApplication.getInstance());
//    }



    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mMyApplication);
    }
    @Provides
    @Singleton
    Cache provideHttpCache(MyApplication application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(mMyApplication.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonClient(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BuildConfig.baseURL)
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
