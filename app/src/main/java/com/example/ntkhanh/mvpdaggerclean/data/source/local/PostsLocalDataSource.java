package com.example.ntkhanh.mvpdaggerclean.data.source.local;

import android.support.annotation.NonNull;

import com.example.ntkhanh.mvpdaggerclean.data.model.Post;
import com.example.ntkhanh.mvpdaggerclean.data.source.PostsDataSource;

import java.util.List;

import rx.Observable;

/**
 * Created by ntkhanh on 3/26/17.
 */

public class PostsLocalDataSource implements PostsDataSource {
    @Override
    public void getPosts(@NonNull LoadPostsCallback loadPostsCallback) {
        loadPostsCallback.onDataNotAvaiable();
    }
}
