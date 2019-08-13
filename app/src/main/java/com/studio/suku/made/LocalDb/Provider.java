package com.studio.suku.made.LocalDb;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;


import static com.studio.suku.made.LocalDb.Contract.Entry.CONTENT_URI;
import static com.studio.suku.made.LocalDb.Contract.Entry.TABLE_NAME;

public class Provider extends ContentProvider {

    static final String PROVIDER_NAME = "com.studio.suku.made";
    static final int FAVORITE = 1;
    static final int FAVORITE_ID = 2;
    FavoriteHelper favoriteHelper;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(PROVIDER_NAME, TABLE_NAME, FAVORITE);
        uriMatcher.addURI(PROVIDER_NAME, TABLE_NAME + "/#", FAVORITE_ID);
    }


    @Override
    public boolean onCreate() {
        favoriteHelper = new FavoriteHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri,String[] projection,String selection,String[] selectionArgs,  String sortOrder) {
        Cursor cursor;
        switch (uriMatcher.match(uri)){
            case FAVORITE :
                cursor = favoriteHelper.queryProvider();
                break;
            case FAVORITE_ID :
                cursor = favoriteHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = favoriteHelper.queryProvider();
                break;
        }
        return cursor;
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
            getContext().getContentResolver().notifyChange(CONTENT_URI, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deleted = 0;
        switch (uriMatcher.match(uri)){
            case FAVORITE_ID :
                deleted = favoriteHelper.deleteProvider(uri.getLastPathSegment());
                break;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        return deleted;
    }

    @Override
    public int update( Uri uri, ContentValues values,  String selection,  String[] selectionArgs) {
        return 0;
    }
}
