package com.example.ntkhanh.mvpdaggerclean.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by ntkhanh on 4/8/17.
 */

public class PostsPersistenceContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private PostsPersistenceContract() {}

    /* Inner class that defines the table contents */
    public static abstract class PostEntry implements BaseColumns {
        public static final String TABLE_NAME = "post";
        public static final String COLUMN_NAME_USER_ID = "userid";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_BODY = "body";
    }
}
