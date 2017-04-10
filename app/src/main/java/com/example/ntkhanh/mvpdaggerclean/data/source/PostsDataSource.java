package com.example.ntkhanh.mvpdaggerclean.data.source;

import android.support.annotation.NonNull;

import com.example.ntkhanh.mvpdaggerclean.data.model.Post;

import java.util.List;

import rx.Observable;

/**
 * Created by ntkhanh on 3/26/17.
 */

public interface PostsDataSource {

    void deleteAllPosts();

    void savePost(Post post);

    interface LoadPostsCallback {
        void onPostLoaded(List<Post> postList);
        void onDataNotAvailable();
    }

    void getPosts(@NonNull LoadPostsCallback loadPostsCallback);
}
