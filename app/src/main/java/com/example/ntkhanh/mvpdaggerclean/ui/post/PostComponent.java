package com.example.ntkhanh.mvpdaggerclean.ui.post;

import dagger.Subcomponent;
import com.example.ntkhanh.mvpdaggerclean.injection.scope.FragmentScoped;

/**
 * Created by ntkhanh on 3/26/17.
 */
@FragmentScoped
@Subcomponent(modules = {PostPresenterModule.class})
public interface PostComponent {
    void inject(PostActivity postActivity);
}
