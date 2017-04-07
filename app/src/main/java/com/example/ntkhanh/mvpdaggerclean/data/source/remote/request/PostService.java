package com.example.ntkhanh.mvpdaggerclean.data.source.remote.request;

import com.example.ntkhanh.mvpdaggerclean.data.model.Post;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ntkhanh on 3/26/17.
 */

public interface PostService {
    @GET("/posts")
    Observable<List<Post>> getPostList();
}
