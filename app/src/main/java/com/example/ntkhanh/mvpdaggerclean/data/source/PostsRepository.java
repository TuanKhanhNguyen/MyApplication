package com.example.ntkhanh.mvpdaggerclean.data.source;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ntkhanh.mvpdaggerclean.data.model.Post;
import com.example.ntkhanh.mvpdaggerclean.injection.qualifier.Local;
import com.example.ntkhanh.mvpdaggerclean.injection.qualifier.Remote;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ntkhanh on 3/26/17.
 */

public class PostsRepository implements PostsDataSource {

    private static final String TAG = PostsRepository.class.getSimpleName();
    private final PostsDataSource mPostsLocalDataSource;
    private final PostsDataSource mPostsRemoteDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<Integer, Post> mCachePost;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    /**
     * By marking the constructor with {@code @Inject}, Dagger will try to inject the dependencies
     * required to create an instance of the TasksRepository. Because {@link PostsDataSource} is an
     * interface, we must provide to Dagger a way to build those arguments, this is done in
     * {@link PostsRepositoryModule}.
     * <p>
     * When two arguments or more have the same type, we must provide to Dagger a way to
     * differentiate them. This is done using a qualifier.
     * <p>
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */

    @Inject
    public PostsRepository(@Local PostsDataSource postsLocalDataSource, @Remote PostsDataSource postsRemoteDataSource) {
        this.mPostsLocalDataSource = postsLocalDataSource;
        this.mPostsRemoteDataSource = postsRemoteDataSource;
    }

    @Override
    public void deleteAllPosts() {
        mPostsLocalDataSource.deleteAllPosts();
        mPostsRemoteDataSource.deleteAllPosts();
        if (mCachePost == null) {
            mCachePost = new LinkedHashMap<>();
        }
        mCachePost.clear();
    }

    @Override
    public void savePost(Post post) {

        mPostsRemoteDataSource.savePost(post);
        mPostsLocalDataSource.savePost(post);
        if (mCachePost == null) {
            mCachePost = new LinkedHashMap<>();
        }
        mCachePost.put(post.getId(), post);
    }

    @Override
    public void getPosts(@NonNull final LoadPostsCallback callback) {
        checkNotNull(callback);
        if (mCachePost != null && mCachePost.size() > 0 && !mCacheIsDirty) {
            Log.d(TAG, "getPosts from cache");
            callback.onPostLoaded(new ArrayList<>(mCachePost.values()));
            return;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getPostFromRemoteDataSource(callback);
        } else {
            // Query the local storage if available. If not, query the network.
            mPostsLocalDataSource.getPosts(new LoadPostsCallback() {
                @Override
                public void onPostLoaded(List<Post> postList) {
                    Log.d(TAG, "getPosts from local data source success");
                    refreshCache(postList);
                    callback.onPostLoaded(postList);
                }

                @Override
                public void onDataNotAvailable() {
                    Log.d(TAG, "getPosts from local data source failed");
                    getPostFromRemoteDataSource(callback);
                }
            });
        }
    }

    private void getPostFromRemoteDataSource(final LoadPostsCallback callback) {
        mPostsRemoteDataSource.getPosts(new LoadPostsCallback() {
            @Override
            public void onPostLoaded(List<Post> postList) {
                Log.d(TAG, "getPostFromRemoteDataSource success");
                refreshCache(postList);
                //TODO: save to local
                refreshLocalDataSource(postList);
                callback.onPostLoaded(postList);
            }

            @Override
            public void onDataNotAvailable() {
                Log.d(TAG, "getPostFromRemoteDataSource failed");
                mPostsLocalDataSource.getPosts(callback);
            }
        });
    }

    private void refreshLocalDataSource(List<Post> postList) {
        mPostsLocalDataSource.deleteAllPosts();
        for (Post post : postList) {
            mPostsLocalDataSource.savePost(post);
        }
    }

    private void refreshCache(List<Post> posts) {
        if (mCachePost == null) {
            mCachePost = new LinkedHashMap<>();
        }
        mCachePost.clear();
        for (Post post : posts) {
            mCachePost.put(post.getId(), post);
        }
        mCacheIsDirty = false;
    }

    public void refreshPost() {
        mCacheIsDirty = true;
    }
}
