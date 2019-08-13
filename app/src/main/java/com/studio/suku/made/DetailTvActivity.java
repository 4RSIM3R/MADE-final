package com.studio.suku.made;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.studio.suku.made.LocalDb.Favorite;
import com.studio.suku.made.LocalDb.FavoriteHelper;
import com.studio.suku.made.Model.TvResults;

public class DetailTvActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_DATA = "extra_data";

    private final Favorite favorite = new Favorite();
    private FavoriteHelper favoriteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);

        TvResults.ResultsBean resultsBean = getIntent().getParcelableExtra(EXTRA_DATA);
        favoriteHelper = new FavoriteHelper(getApplicationContext());

        ImageView poster = findViewById(R.id.poster_tv);
        TextView title = findViewById(R.id.judul_tv);
        TextView desc = findViewById(R.id.desc_tv);
        TextView vote = findViewById(R.id.production_tv);
        Button button = findViewById(R.id.button_tv);
        button.setOnClickListener(this);

        Picasso.get().load(resultsBean.getPoster_path()).into(poster);
        title.setText(resultsBean.getOriginal_name());
        desc.setText(resultsBean.getOverview());
        vote.setText("Rate : " + resultsBean.getVote_average());

        favorite.setName(resultsBean.getOriginal_name());
        favorite.setOverview(resultsBean.getOverview());
        favorite.setImage(resultsBean.getPoster_path());
        favorite.setType("Tv");
        favorite.setRate( resultsBean.getVote_average());


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_tv){
            favoriteHelper.open();
            try {
                if (favoriteHelper.checkData(favorite.getName()).moveToFirst()){
                    Toast.makeText(DetailTvActivity.this, "Sudah Ditambahkan", Toast.LENGTH_SHORT).show();
                }
                else {
                    long result = favoriteHelper.insertFavorite(favorite);
                    if (result > 0){
                        Toast.makeText(DetailTvActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(DetailTvActivity.this, "Ada Error", Toast.LENGTH_SHORT).show();
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
