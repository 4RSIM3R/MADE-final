package com.studio.suku.made.widget;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.squareup.picasso.Picasso;
import com.studio.suku.made.LocalDb.Contract;
import com.studio.suku.made.LocalDb.Helper;
import com.studio.suku.made.Model.MoviesResults;
import com.studio.suku.made.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final List<MoviesResults.ResultsBean> resultsBeans = new ArrayList<>();
    List<String> link;
    private final Context mContext;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    public StackRemoteViewsFactory(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        link.clear();
        //Load The Data Here
        LoadFavoriteData();
    }

    private void LoadFavoriteData(){
        Helper helper = new Helper(mContext);
        sqLiteDatabase = helper.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + Contract.Entry.TABLE_NAME + " WHERE type = ? ";
        cursor = sqLiteDatabase.rawQuery(selectQuery, new String[]{"Film"});
        if (cursor != null && cursor.moveToFirst()){
            for (int i = 0;  i < cursor.getColumnCount(); i++){
                link.add(cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_IMAGE)));
            }
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return link.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        try {
            Bitmap img = Picasso.get().load(link.get(position)).get();
            rv.setImageViewBitmap(R.id.imageView, img);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
