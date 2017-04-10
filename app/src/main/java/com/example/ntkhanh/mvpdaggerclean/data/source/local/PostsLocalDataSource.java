package com.example.ntkhanh.mvpdaggerclean.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ntkhanh.mvpdaggerclean.data.model.Post;
import com.example.ntkhanh.mvpdaggerclean.data.source.PostsDataSource;
import com.example.ntkhanh.mvpdaggerclean.data.source.local.PostsPersistenceContract.PostEntry;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ntkhanh on 3/26/17.
 */

public class PostsLocalDataSource implements PostsDataSource {

    private static final String TAG = PostsLocalDataSource.class.getSimpleName();
    private static PostsLocalDataSource INSTANCE;
    private PostDbHelper mDbHelper;



    // Prevent direct instantiation.
    public PostsLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new PostDbHelper(context);
    }

    public static PostsLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PostsLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getPosts(@NonNull LoadPostsCallback loadPostsCallback) {

        Log.d(TAG, "getPosts");
        List<Post> postList = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String projection[] = {
                PostEntry.COLUMN_NAME_USER_ID,
                PostEntry.COLUMN_NAME_ENTRY_ID,
                PostEntry.COLUMN_NAME_TITLE,
                PostEntry.COLUMN_NAME_BODY
        };

        try {
            Cursor c = db.query(
                    PostsPersistenceContract.PostEntry.TABLE_NAME, projection, null, null, null, null, null);

            if (c != null && c.getCount() > 0) {
                while (c.moveToNext()) {
                    int userId = c.getInt(c.getColumnIndexOrThrow(PostEntry.COLUMN_NAME_USER_ID));
                    int itemId = c.getInt(c.getColumnIndexOrThrow(PostEntry.COLUMN_NAME_ENTRY_ID));
                    String title = c.getString(c.getColumnIndexOrThrow(PostEntry.COLUMN_NAME_TITLE));
                    String body =
                            c.getString(c.getColumnIndexOrThrow(PostEntry.COLUMN_NAME_BODY));
                    Post post = new Post(userId, itemId, title, body);
                    postList.add(post);
                }
            }
            if (c != null) {
                c.close();
            }

            db.close();
            if (postList.isEmpty()) {
                Log.d(TAG, "getPosts onDataNotAvailable");
                loadPostsCallback.onDataNotAvailable();
            } else {
                Log.d(TAG, "getPosts onPostLoaded");
                loadPostsCallback.onPostLoaded(postList);
            }
        } catch (Exception ex) {
            Log.d(TAG, "getPosts: ex" + ex.toString());
            loadPostsCallback.onDataNotAvailable();
        }
    }

    @Override
    public void deleteAllPosts() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.delete(PostEntry.TABLE_NAME, null, null);

        db.close();
    }

    @Override
    public void savePost(Post post) {
        Log.d(TAG, "savePost");
        checkNotNull(post);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PostEntry.COLUMN_NAME_USER_ID, post.getUserId());
        values.put(PostEntry.COLUMN_NAME_ENTRY_ID, post.getId());
        values.put(PostEntry.COLUMN_NAME_TITLE, post.getTitle());
        values.put(PostEntry.COLUMN_NAME_BODY, post.getBody());

        db.insert(PostEntry.TABLE_NAME, null, values);

        db.close();
    }
}
