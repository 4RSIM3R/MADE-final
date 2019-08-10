package com.studio.suku.made;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.studio.suku.made.Adapter.SearchFilmAdapter;
import com.studio.suku.made.Model.SearchFilmResults;
import com.studio.suku.made.ViewModel.SearchFilmViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFilmActivity extends AppCompatActivity {

    RecyclerView list;
    private SearchFilmAdapter adapter;
    public static final String PARAMS = "Ilzam";
    ProgressBar progressBar;
    String params;
    private final List<SearchFilmResults> searchFilmResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_film);

        list = findViewById(R.id.list_search_filn);
        progressBar = findViewById(R.id.loading_search_film);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));

        Bundle extras = getIntent().getExtras();
        params = getIntent().getStringExtra(PARAMS);
        Log.d("Dari Fragment", params);
        SearchFilmViewModel.setSEARCH(params);

        SearchFilmViewModel viewModel = ViewModelProviders.of(this).get(SearchFilmViewModel.class);

        viewModel.getSearchFilm().observe(this, new Observer<SearchFilmResults>() {
            @Override
            public void onChanged(@Nullable SearchFilmResults results) {
                searchFilmResults.add(results);
                if (results == null){
                    showLoading(true);
                }
                else {
                    showLoading(false);
                    adapter = new SearchFilmAdapter(SearchFilmActivity.this, searchFilmResults.get(0).getResults());
                    list.setAdapter(adapter);
                }


            }
        });

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


}
