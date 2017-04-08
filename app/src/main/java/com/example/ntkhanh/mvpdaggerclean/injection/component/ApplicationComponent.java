package com.example.ntkhanh.mvpdaggerclean.injection.component;

import com.example.ntkhanh.mvpdaggerclean.injection.module.ApplicationModule;
import com.example.ntkhanh.mvpdaggerclean.data.source.PostsRepositoryModule;
import com.example.ntkhanh.mvpdaggerclean.ui.post.PostComponent;
import com.example.ntkhanh.mvpdaggerclean.ui.post.PostPresenterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ntkhanh on 3/26/17.
 */

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                PostsRepositoryModule.class})
public interface ApplicationComponent {
    PostComponent plus(PostPresenterModule postPresenterModule);
}
