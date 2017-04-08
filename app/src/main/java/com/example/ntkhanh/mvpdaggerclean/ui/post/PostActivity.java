package com.example.ntkhanh.mvpdaggerclean.ui.post;

import android.os.Bundle;
import android.util.Log;

import com.example.ntkhanh.mvpdaggerclean.MyApplication;
import com.example.ntkhanh.mvpdaggerclean.R;


import javax.inject.Inject;

import com.example.ntkhanh.mvpdaggerclean.ui.base.BaseActivity;
import com.example.ntkhanh.mvpdaggerclean.util.ActivityUtils;

public class PostActivity extends BaseActivity {
    private static final String TAG = PostActivity.class.getSimpleName();

    @Inject
    PostPresenter mPostPresenter;

    private PostFragment mPostFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, ">>>onCreate()");
        setContentView(R.layout.activity_post);

        mPostFragment = (PostFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);

        if (mPostFragment == null) {
            Log.d(TAG, ">>>onCreate() postFragment == null");
            mPostFragment = PostFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mPostFragment, R.id.content_frame);
        }

        MyApplication.getInstance().getApplicationComponent()
                .plus(new PostPresenterModule(this, mPostFragment))
                .inject(this);
        ;

    }
}
