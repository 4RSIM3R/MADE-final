package com.studio.suku.made;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.suku.made.Model.TvResults;

public class DetailTvActivity extends AppCompatActivity {

    public static final String EXTRA_DATA = "extra_data";

    ImageView poster;
    TextView title, desc, vote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);

        TvResults.ResultsBean resultsBean = getIntent().getParcelableExtra(EXTRA_DATA);

        poster = findViewById(R.id.poster_tv);
        title  = findViewById(R.id.judul_tv);
        desc = findViewById(R.id.desc_tv);
        vote = findViewById(R.id.production_tv);

        Picasso.get().load(resultsBean.getPoster_path()).into(poster);
        title.setText(resultsBean.getOriginal_name());
        desc.setText(resultsBean.getOverview());
        vote.setText("Rate : " + resultsBean.getVote_average());


    }
}
