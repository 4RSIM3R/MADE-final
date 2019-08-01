package com.studio.suku.made;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.studio.suku.made.LocalDb.Contract;
import com.studio.suku.made.LocalDb.Favorite;
import com.studio.suku.made.LocalDb.FavoriteHelper;
import com.studio.suku.made.Model.MoviesResults;

import java.util.ArrayList;


public class DetailFilmActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_DATA = "Extra Data";
    TextView title, desc, rating;
    Double rate;
    Button button;
    private String name;
    private String Desc;
    private String Img;
    private Double Rate;
    Favorite favorite = new Favorite();
    private FavoriteHelper favoriteHelper;
    ImageView poster;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        MoviesResults.ResultsBean resultsBean = getIntent().getParcelableExtra(EXTRA_DATA);
        favoriteHelper = new FavoriteHelper(getApplicationContext());

        title = findViewById(R.id.judul);
        poster = findViewById(R.id.poster);
        desc = findViewById(R.id.desc);
        rating = findViewById(R.id.production);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        rate = resultsBean.getVote_average();

        name = resultsBean.getOriginal_title();
        Desc = resultsBean.getOverview();
        Img = resultsBean.getPoster_path();
        title.setText(resultsBean.getOriginal_title());
        desc.setText(resultsBean.getOverview());
        rating.setText("Rate : " + rate);
        Picasso.get().load(resultsBean.getPoster_path()).into(poster);

        favorite.setName(resultsBean.getOriginal_title());
        favorite.setOverview(resultsBean.getOverview());
        favorite.setImage(resultsBean.getPoster_path());
        favorite.setType("Film");
        favorite.setRate( resultsBean.getVote_average());

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button){
            //Toast.makeText(DetailFilmActivity.this, favorite.getName(), Toast.LENGTH_SHORT).show();
            favoriteHelper.open();
            try {
               if ( favoriteHelper.checkData(favorite.getName()).moveToFirst()){
                   Toast.makeText(DetailFilmActivity.this, "Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
               }
               else {
                   long result = favoriteHelper.insertFavorite(favorite);
                   if (result > 0){
                       Toast.makeText(DetailFilmActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       Toast.makeText(DetailFilmActivity.this, "Ada Error", Toast.LENGTH_SHORT).show();
                   }
               }
            }catch (Exception e){
                e.printStackTrace();
                Log.d("Ada Error", e.getMessage());
                Toast.makeText(this, "Ada Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
