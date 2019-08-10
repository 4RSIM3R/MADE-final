package com.studio.suku.made.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;
import com.studio.suku.made.LocalDb.Contract;
import com.studio.suku.made.LocalDb.DatabaseHelper;
import com.studio.suku.made.LocalDb.Favorite;
import com.studio.suku.made.LocalDb.FavoriteHelper;
import com.studio.suku.made.Model.MoviesResults;
import com.studio.suku.made.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.studio.suku.made.LocalDb.Contract.Entry.CONTENT_URI;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {


    private final Context context;
    private int mAppWidgetid;
    private final List<Bitmap> mWidgetItems = new ArrayList<>();
    FavoriteHelper favoriteHelper;
    ArrayList<Favorite> list = new ArrayList<>();
    Bitmap preview;

    StackRemoteViewsFactory(Context context) {
        this.context = context;
        favoriteHelper = new FavoriteHelper(context);
        favoriteHelper.open();
    }



    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        //Cara Nambahin Dari Sqlite Gimana yah ?
        list.clear();
        list = favoriteHelper.getFavorite("Film");
    }



    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        try {
            final int fix_position = position + 1;
            preview = Glide.with(context)
                    .asBitmap()
                    .load(list.get(position).getImage())
                    .apply(new RequestOptions().fitCenter())
                    .submit()
                    .get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rv.setImageViewBitmap(R.id.imageViewWidget, preview);

        Bundle extras = new Bundle();
        extras.putInt(FavoriteWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageViewWidget, fillInIntent);
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
