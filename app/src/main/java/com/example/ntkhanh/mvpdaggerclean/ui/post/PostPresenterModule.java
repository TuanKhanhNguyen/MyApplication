package com.example.ntkhanh.mvpdaggerclean.ui.post;

import android.app.Activity;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ntkhanh on 3/26/17.
 */

@Module
public class PostPresenterModule {
    private final PostContract.View mView;
    private final Activity mActivity;

    public PostPresenterModule(Activity activity, PostContract.View view) {
        this.mView = view;
        this.mActivity = activity;
    }

    @Provides
    @NonNull
    PostContract.View providerPostContractView() {
        return mView;
    }

    @Provides
    @NonNull
    Activity provideActivity() {
        return mActivity;
    }


}
