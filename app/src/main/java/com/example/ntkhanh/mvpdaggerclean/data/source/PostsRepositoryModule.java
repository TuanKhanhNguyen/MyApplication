package com.example.ntkhanh.mvpdaggerclean.data.source;

import com.example.ntkhanh.mvpdaggerclean.data.source.local.PostsLocalDataSource;
import com.example.ntkhanh.mvpdaggerclean.data.source.remote.PostsRemoteDataSource;
import com.example.ntkhanh.mvpdaggerclean.injection.qualifier.Local;
import com.example.ntkhanh.mvpdaggerclean.injection.qualifier.Remote;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ntkhanh on 3/26/17.
 */

@Module
public class PostsRepositoryModule {
    @Singleton
    @Provides
    @Local
    PostsDataSource providePostLocalDataSource() {
        return new PostsLocalDataSource();
    }

    @Singleton
    @Provides
    @Remote
    PostsDataSource providePostRemoteDataSource() {
        return new PostsRemoteDataSource();
    }

}
