package com.studio.suku.made;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.studio.suku.made.LocalDb.Contract.*;

import com.squareup.picasso.Picasso;
import com.studio.suku.made.LocalDb.Favorite;
import com.studio.suku.made.LocalDb.FavoriteHelper;
import com.studio.suku.made.Model.MoviesResults;

import static com.studio.suku.made.LocalDb.Contract.Entry.CONTENT_URI;
//import static com.studio.suku.made.LocalDb.Provider.CONTENT_URI;


public class DetailFilmActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_DATA = "Extra Data";
    private TextView title;
    private TextView desc;
    private TextView rating;
    private Double rate;
    private Button button;
    private Favorite favorite = new Favorite();
    private FavoriteHelper favoriteHelper;
    private ImageView poster;
    ContentValues cv = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        favoriteHelper = new FavoriteHelper(getApplicationContext());
        MoviesResults.ResultsBean resultsBean = getIntent().getParcelableExtra(EXTRA_DATA);

        title = findViewById(R.id.judul);
        poster = findViewById(R.id.poster);
        desc = findViewById(R.id.desc);
        rating = findViewById(R.id.production);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        rate = resultsBean.getVote_average();

        title.setText(resultsBean.getOriginal_title());
        desc.setText(resultsBean.getOverview());
        rating.setText("Rate : " + rate);
        Picasso.get().load(resultsBean.getPoster_path()).into(poster);

        favorite.setName(resultsBean.getOriginal_title());
        favorite.setOverview(resultsBean.getOverview());
        favorite.setImage(resultsBean.getPoster_path());
        favorite.setType("Film");
        favorite.setRate( resultsBean.getVote_average());


        cv.put(Entry.COLUMN_NAME, resultsBean.getOriginal_title());
        cv.put(Entry.COLUMN_OVERVIEW, resultsBean.getOverview());
        cv.put(Entry.COLUMN_IMAGE, resultsBean.getPoster_path());
        cv.put(Entry.COLUMN_TYPE, "Film");
        cv.put(Entry.COLUMN_RATE, resultsBean.getVote_average());
//        Log.d("Content Values nya", "Content Values nya : " + cv.size());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button){

            favoriteHelper.open();
            try {
               if ( favoriteHelper.checkData(favorite.getName()).moveToFirst()){
                   Toast.makeText(DetailFilmActivity.this, "Sudah Ditambahkan", Toast.LENGTH_LONG).show();
               }
               else {
                   //long result = favoriteHelper.insertFavorite(favorite);
                   Uri uri = getContentResolver().insert(
                           CONTENT_URI, cv
                   );
                   if (uri.toString() != null){
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
