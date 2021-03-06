package com.example.ntkhanh.mvpdaggerclean.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ntkhanh.mvpdaggerclean.MyApplication;
import com.example.ntkhanh.mvpdaggerclean.data.model.Post;
import com.example.ntkhanh.mvpdaggerclean.data.source.PostsDataSource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.example.ntkhanh.mvpdaggerclean.data.source.remote.request.PostService;

/**
 * Created by ntkhanh on 3/26/17.
 */

public class PostsRemoteDataSource implements PostsDataSource {

//    public PostsRemoteDataSource() {
//        MyApplication.getInstance().getApplicationComponent().inject(this);
//    }

    private static final String TAG = PostsRemoteDataSource.class.getSimpleName();
    @Inject
    Retrofit mRetrofit;

    // Use inject constructor
//    @Inject
//    PostsRemoteDataSource(Retrofit retrofit) {
//        mRetrofit = retrofit;
//    }

    @Override
    public void getPosts(@NonNull final LoadPostsCallback callback) {
        if (mRetrofit == null) {
            Log.d(TAG, "mRetrofit is null");
            //return;
            mRetrofit = MyApplication.getInstance().getApplicationComponent().getRetrofit();
        }
        //MyApplication.getInstance().getApplicationComponent().getRetrofit().create(PostService.class).getPostList().subscribeOn(Schedulers.io())
        mRetrofit.create(PostService.class).getPostList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "getPosts onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "getPosts onError = " + e.getMessage());
                        callback.onDataNotAvaiable();
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        Log.d(TAG, "getPosts onNext");
                        callback.onPostLoaded(posts);
                    }
                });

    }
}
