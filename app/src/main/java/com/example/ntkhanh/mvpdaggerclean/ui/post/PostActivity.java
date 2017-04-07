package com.example.ntkhanh.mvpdaggerclean.ui.post;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ntkhanh.mvpdaggerclean.MyApplication;
import com.example.ntkhanh.mvpdaggerclean.R;


import javax.inject.Inject;

import com.example.ntkhanh.mvpdaggerclean.util.ActivityUtils;

public class PostActivity extends AppCompatActivity {
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

//        DaggerPostComponent.builder()
//                .netComponent(((MyApplication) getApplication()).getNetComponent())
//                .postPresenterModule(new PostPresenterModule(postFragment))
//                .build()
//                .inject(this);

//        DaggerApplicationComponent.builder()
//                .applicationModule(this.getApplication())
//                .build();
//        DaggerPostComponent.builder()
//                .postPresenterModule(new PostPresenterModule(postFragment))
//                .build();
        MyApplication.getInstance().getApplicationComponent()
                .plus(new PostPresenterModule(this, mPostFragment))
                .inject(this);
        ;

    }
}
