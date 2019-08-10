package com.studio.suku.made.LocalDb;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.HashMap;

import static android.provider.BaseColumns._ID;
import static com.studio.suku.made.LocalDb.Contract.Entry.COLUMN_NAME;
import static com.studio.suku.made.LocalDb.Contract.Entry.CONTENT_URI;
import static com.studio.suku.made.LocalDb.Contract.Entry.TABLE_NAME;

public class Provider extends ContentProvider {

    static final String PROVIDER_NAME = "com.studio.suku.made";
//    static final String URL = "content://" + PROVIDER_NAME + "/favorites";
//    public static final Uri CONTENT_URI = Uri.parse(URL);
    static final int FAVORITE = 1;
    static final int FAVORITE_ID = 2;
    FavoriteHelper favoriteHelper;
    SQLiteDatabase db;
    private static HashMap<String, String> FAVORITE_MAP;

    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, TABLE_NAME, FAVORITE);
        uriMatcher.addURI(PROVIDER_NAME, TABLE_NAME, FAVORITE_ID);
    }


    @Override
    public boolean onCreate() {
        favoriteHelper = new FavoriteHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri,String[] projection,String selection,String[] selectionArgs,  String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);
        switch (uriMatcher.match(uri)){
            case FAVORITE:
                qb.setProjectionMap(FAVORITE_MAP);
                break;
            case FAVORITE_ID :
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                break;
        }
        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on student names
             */
            sortOrder = COLUMN_NAME;
        }

        Cursor c = qb.query(db,	projection,	selection,
                selectionArgs,null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long favId = favoriteHelper.insertProvider(values);
        if (favId > 0){
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, favId);
            //getContext().getContentResolver().insert(CONTENT_URI, values);
            getContext().getContentResolver().notifyChange(_uri, null);
            return Uri.parse(CONTENT_URI + "/" + favId);
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update( Uri uri, ContentValues values,  String selection,  String[] selectionArgs) {
        return 0;
    }
}
