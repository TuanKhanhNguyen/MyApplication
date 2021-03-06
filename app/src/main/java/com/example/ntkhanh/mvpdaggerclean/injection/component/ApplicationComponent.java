package com.example.ntkhanh.mvpdaggerclean.injection.component;

import com.example.ntkhanh.mvpdaggerclean.injection.module.ApplicationModule;
import com.example.ntkhanh.mvpdaggerclean.data.source.PostsRepositoryModule;
import com.example.ntkhanh.mvpdaggerclean.data.source.remote.PostsRemoteDataSource;
import com.example.ntkhanh.mvpdaggerclean.ui.post.PostComponent;
import com.example.ntkhanh.mvpdaggerclean.ui.post.PostPresenterModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by ntkhanh on 3/26/17.
 */

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                PostsRepositoryModule.class})
                //NetModule.class})
public interface ApplicationComponent {
    PostComponent plus(PostPresenterModule postPresenterModule);
    Retrofit getRetrofit();

    void inject (PostsRemoteDataSource postsRemoteDataSource); // if want to inject Retrofit to remote data source.
    //Retrofit getRetrofit();
    //void inject(PostActivity postActivity);
    //void inject(PostActivity postActivity);
    //PostComponent plus(PostPresenterModule postPresenterModule);
    //void inject(PostActivity postActivity);
}
