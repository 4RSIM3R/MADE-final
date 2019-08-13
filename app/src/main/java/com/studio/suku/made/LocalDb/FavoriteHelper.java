package com.studio.suku.made.LocalDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.studio.suku.made.LocalDb.Contract.*;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

public class FavoriteHelper {

    private static final String DATABASE_TABLE = Contract.Entry.TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static FavoriteHelper INSTANCE;

    private static SQLiteDatabase database;

    public FavoriteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<Favorite> getFavorite(String type){
        ArrayList<Favorite> arrayList = new ArrayList<>();
        String Query = "SELECT  * FROM " + Contract.Entry.TABLE_NAME + " WHERE type = ? ";
        Cursor cursor = database.rawQuery(Query, new String[]{type});
        cursor.moveToFirst();
        Favorite favorite;
        if (cursor.getCount() > 0){
            do {

                favorite = new Favorite();
                favorite.setName(cursor.getString(cursor.getColumnIndexOrThrow(Entry.COLUMN_NAME)));
                favorite.setImage(cursor.getString(cursor.getColumnIndexOrThrow(Entry.COLUMN_IMAGE)));
                favorite.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(Entry.COLUMN_OVERVIEW)));
                favorite.setRate(cursor.getDouble(cursor.getColumnIndexOrThrow(Entry.COLUMN_RATE)));
                favorite.setType(cursor.getString(cursor.getColumnIndexOrThrow(Entry.COLUMN_TYPE)));
                arrayList.add(favorite);
                cursor.moveToNext();

            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertFavorite(Favorite favorite){
        ContentValues cv = new ContentValues();
        Log.d("Kiriman", favorite.getName());
        cv.put(Entry.COLUMN_NAME, favorite.getName());
        cv.put(Entry.COLUMN_IMAGE, favorite.getImage());
        cv.put(Entry.COLUMN_RATE, favorite.getRate());
        cv.put(Entry.COLUMN_TYPE, favorite.getType());
        cv.put(Entry.COLUMN_OVERVIEW, favorite.getOverview());

        return database.insert(Entry.TABLE_NAME, null, cv);
    }

    public int deleteFavorite(String name){
        return database.delete(Entry.TABLE_NAME, Entry.COLUMN_NAME + " = '" + name + "'", null);
    }

    public Cursor checkData(String name){
        return database.rawQuery("SELECT * FROM favorite WHERE name = ? ", new String[]{name});
    }

    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE
                , null
                , Entry.COLUMN_TYPE + " = ?"
                , new String[]{"Film"}
                , null
                , null
                , _ID + " ASC");
    }

    public long insertProvider(ContentValues cv){
        return database.insert(DATABASE_TABLE, null, cv);
    }

    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(DATABASE_TABLE, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

}
