package com.studio.suku.made;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.suku.made.Model.MoviesResults;

public class DetailFilmActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_DATA = "Extra Data";
    TextView title, desc, rating;
    Double rate;
    Button button;
    private String name;
    private String Desc;
    private String Img;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

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

    }

    @Override
    public void onClick(View v) {

    }
}
