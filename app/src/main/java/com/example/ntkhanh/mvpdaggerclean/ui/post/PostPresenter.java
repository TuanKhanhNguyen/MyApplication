package com.example.ntkhanh.mvpdaggerclean.ui.post;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ntkhanh.mvpdaggerclean.UseCase;
import com.example.ntkhanh.mvpdaggerclean.UseCaseHandler;
import com.example.ntkhanh.mvpdaggerclean.data.model.Post;
import com.example.ntkhanh.mvpdaggerclean.ui.post.domain.GetPosts;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ntkhanh on 3/26/17.
 */

public class PostPresenter implements PostContract.Presenter {

    private static final String TAG = PostPresenter.class.getSimpleName();

    private final PostContract.View mView;
    private final UseCaseHandler mUseCaseHandler;
    private final GetPosts mGetPosts;
    //private final Retrofit mRetrofit;

    @Inject
    public PostPresenter(@NonNull GetPosts getPosts,
                         @NonNull PostContract.View view,
                         @NonNull UseCaseHandler useCaseHandler) {
                         //@NonNull Retrofit retrofit) {

        this.mGetPosts = checkNotNull(getPosts, "postsRepository cannot be null");
        this.mView = checkNotNull(view, "view cannot be null");
        this.mUseCaseHandler = checkNotNull(useCaseHandler, "useCaseHandler cannot be null");
        //this.mRetrofit = checkNotNull(retrofit, "retrofit cannot be null");

    }


    @Inject
    Retrofit retrofit;
    /**
     * Method injection is used here to safely reference {@code this} after the object is created.
     * For more information, see Java Concurrency in Practice.
     */
    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        Log.d(TAG, "start()");
        loadPost();
        if (retrofit == null) {
            Log.d(TAG, "retrofit  = null" );
        } else {
            Log.d(TAG, "retrofit  != null" );
        }


    }

    @Override
    public void loadPost() {
        Log.d(TAG, "oadPost()");
        mView.showProgressDialog();
        mUseCaseHandler.execute(mGetPosts, new GetPosts.RequestValues(false),
                new UseCase.UseCaseCallback<GetPosts.ResponseValue>() {
                    @Override
                    public void onSuccess(GetPosts.ResponseValue response) {
                        Log.d(TAG, "loadPost success");
                        List<Post> posts = response.getPostList();
                        mView.hideProgressDialog();
                        mView.showPost(posts);
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "loadPost error");
                    }
                });

    }
}
