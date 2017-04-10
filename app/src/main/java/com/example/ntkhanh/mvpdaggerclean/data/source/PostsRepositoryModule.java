package com.example.ntkhanh.mvpdaggerclean.data.source;

import android.content.Context;

import com.example.ntkhanh.mvpdaggerclean.MyApplication;
import com.example.ntkhanh.mvpdaggerclean.data.source.local.PostsLocalDataSource;
import com.example.ntkhanh.mvpdaggerclean.data.source.remote.PostsRemoteDataSource;
import com.example.ntkhanh.mvpdaggerclean.injection.qualifier.Local;
import com.example.ntkhanh.mvpdaggerclean.injection.qualifier.Remote;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by ntkhanh on 3/26/17.
 */

@Module
public class PostsRepositoryModule {

    @Singleton
    @Provides
    @Local
    PostsDataSource providePostLocalDataSource() {
        return new PostsLocalDataSource(MyApplication.getInstance());
    }

    @Singleton
    @Provides
    @Remote
    PostsDataSource providePostRemoteDataSource(Retrofit retrofit) {
        return new PostsRemoteDataSource(retrofit);
    }

}
