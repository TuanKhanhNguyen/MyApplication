package com.example.ntkhanh.mvpdaggerclean.ui.post.domain;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ntkhanh.mvpdaggerclean.UseCase;
import com.example.ntkhanh.mvpdaggerclean.data.model.Post;
import com.example.ntkhanh.mvpdaggerclean.data.source.PostsDataSource;
import com.example.ntkhanh.mvpdaggerclean.data.source.PostsRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ntkhanh on 3/26/17.
 */

public class GetPosts extends UseCase<GetPosts.RequestValues, GetPosts.ResponseValue> {

    private static final String TAG = GetPosts.class.getSimpleName();

    private final PostsRepository mPostsRepository;

    @Inject
    public GetPosts(@NonNull PostsRepository postsRepository) {
        this.mPostsRepository = postsRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        if (requestValues.isForceUpdate()) {
            mPostsRepository.refreshPost();
        }
        Log.d(TAG, "executeUseCase");
        mPostsRepository.getPosts(new PostsDataSource.LoadPostsCallback() {
            @Override
            public void onPostLoaded(List<Post> postList) {
                ResponseValue responseValue = new ResponseValue(postList);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
    public static final class RequestValues implements UseCase.RequestValues {
        private final boolean mForceUpdate;

        public RequestValues(@NonNull boolean forceUpdate) {
            this.mForceUpdate = forceUpdate;
        }

        public boolean isForceUpdate() {
            return mForceUpdate;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue {
        private final List<Post> mPostList;

        public ResponseValue(List<Post> postList) {
            this.mPostList = postList;
        }

        public List<Post> getPostList() {
            return mPostList;
        }
    }
}
