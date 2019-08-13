package com.studio.suku.made.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.studio.suku.made.LocalDb.Favorite;
import com.studio.suku.made.LocalDb.FavoriteHelper;
import com.studio.suku.made.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {


    private final Context context;
    final FavoriteHelper favoriteHelper;
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
