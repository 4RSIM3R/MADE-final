package com.studio.suku.made;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.studio.suku.made.LocalDb.Favorite;
import com.studio.suku.made.LocalDb.FavoriteAdapter;
import com.studio.suku.made.LocalDb.FavoriteHelper;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    public static String Type;
    FavoriteHelper favoriteHelper;
    FavoriteAdapter adapter;
    final ArrayList<Favorite> list = new ArrayList<>();
    RecyclerView list_favorite;
    public static final String EXTRA_STATE = "EXTRA_STATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        favoriteHelper = new FavoriteHelper(getApplicationContext());
        favoriteHelper.open();
        list_favorite = findViewById(R.id.list_favorite);
        list_favorite.setLayoutManager(new LinearLayoutManager(FavoriteActivity.this));
        list_favorite.setHasFixedSize(true);
        adapter = new FavoriteAdapter(this, getApplicationContext());
        list_favorite.setAdapter(adapter);
        if (favoriteHelper.getFavorite(getIntent().getStringExtra(EXTRA_STATE)).size() < 0){

        }else{
            list.addAll(favoriteHelper.getFavorite(getIntent().getStringExtra(EXTRA_STATE)));
        }
        try {
            adapter.setListFavorite(list);
        }catch (Exception e){
            Log.d("Ada Error : " , e.getMessage());
        }

    }


}
