package com.studio.suku.made.LocalDb;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class Contract {

    public static final String Authority = "com.studio.suku.made";
    private static final String SCHEME = "content";

    public Contract() {

    }

    public static final class Entry implements BaseColumns{
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RATE = "rate";
        public static final String COLUMN_TYPE = "type";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(Authority)
                .appendPath(TABLE_NAME)
                .build();
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }


}
