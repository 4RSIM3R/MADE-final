package com.studio.suku.made;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.studio.suku.made.LocalDb.Favorite;
import com.studio.suku.made.LocalDb.FavoriteAdapter;
import com.studio.suku.made.LocalDb.FavoriteHelper;
import com.studio.suku.made.LocalDb.LoadFavoriteCallback;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity implements LoadFavoriteCallback {

    public static String Type;
    FavoriteHelper favoriteHelper;
    FavoriteAdapter adapter;
    RecyclerView list_favorite;
    private static final String EXTRA_STATE = "EXTRA_STATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        favoriteHelper = new FavoriteHelper(getApplicationContext());
        favoriteHelper.open();
        list_favorite = findViewById(R.id.list_favorite);
        list_favorite.setLayoutManager(new LinearLayoutManager(this));
        list_favorite.setHasFixedSize(true);
        adapter = new FavoriteAdapter(this);
        list_favorite.setAdapter(adapter);
        if (savedInstanceState == null) {
            new LoadFavoriteAsync(favoriteHelper, this).execute();
        }
        else {
            ArrayList<Favorite> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                adapter.setListFavorite(list);
            }
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getListFavorite());
    }

    @Override
    public void preExecute() {

    }

    @Override
    public void postExecute(ArrayList<Favorite> favoriteList) {
        adapter.setListFavorite(favoriteList);
    }

    private static class LoadFavoriteAsync extends AsyncTask<Void, Void, ArrayList<Favorite>>{

        private final WeakReference<FavoriteHelper> weakNoteHelper;
        private final WeakReference<LoadFavoriteCallback> weakCallback;

        private LoadFavoriteAsync(WeakReference<FavoriteHelper> weakNoteHelper, WeakReference<LoadFavoriteCallback> weakCallback) {
            this.weakNoteHelper = weakNoteHelper;
            this.weakCallback = weakCallback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<Favorite> doInBackground(Void... voids) {
            return weakNoteHelper.get().getFavorite("Film");
        }

        @Override
        protected void onPostExecute(ArrayList<Favorite> favorites) {
            super.onPostExecute(favorites);
            weakCallback.get().postExecute(favorites);
        }
    }
}
