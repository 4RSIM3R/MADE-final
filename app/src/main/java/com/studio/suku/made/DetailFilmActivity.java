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
import com.studio.suku.made.LocalDb.Helper;
import com.studio.suku.made.Model.MoviesResults;

public class DetailFilmActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_DATA = "Extra Data";
    TextView title, desc, rating;
    Double rate;
    Button button;
    private String name;
    private String Desc;
    private String Img;
    private Double Rate;
    ImageView poster;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        MoviesResults.ResultsBean resultsBean = getIntent().getParcelableExtra(EXTRA_DATA);
        Helper helper = new Helper(this);
        sqLiteDatabase = helper.getWritableDatabase();

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
        Rate = resultsBean.getVote_average();
        title.setText(resultsBean.getOriginal_title());
        desc.setText(resultsBean.getOverview());
        rating.setText("Rate : " + rate);
        Picasso.get().load(resultsBean.getPoster_path()).into(poster);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button){
            Log.d("Hmmm", name);
            ContentValues cv = new ContentValues();
            cv.put(Contract.Entry.COLUMN_NAME, name);
            cv.put(Contract.Entry.COLUMN_IMAGE, Img);
            cv.put(Contract.Entry.COLUMN_OVERVIEW, Desc);
            cv.put(Contract.Entry.COLUMN_RATE, Rate);
            cv.put(Contract.Entry.COLUMN_TYPE, "Film");
            try {
                //Prevent The Duplicate Data
                Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM favorite WHERE name = ? ", new String[]{name});
                if (c.moveToFirst()){
                    Toast.makeText(this, "Sudah Di Tambahkan", Toast.LENGTH_SHORT).show();
                }
                else {
                    sqLiteDatabase.insert(Contract.Entry.TABLE_NAME, null, cv);
                    Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Log.d("Ada Error : " , e.getMessage());
                e.printStackTrace();
                Toast.makeText(this, "Ada Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
