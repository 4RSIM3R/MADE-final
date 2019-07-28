package com.studio.suku.made.LocalDb;

import android.provider.BaseColumns;

public class Contract {

    public Contract() {

    }

    public final class Entry implements BaseColumns{
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RATE = "rate";
        public static final String COLUMN_TYPE = "type";
    }


}
