package com.studio.suku.made;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import com.studio.suku.made.Adapter.SearchTvAdapter;
import com.studio.suku.made.Model.SearchTvResults;
import com.studio.suku.made.ViewModel.SearchTvViewModel;
import java.util.ArrayList;
import java.util.List;

public class SearchTvActivity extends AppCompatActivity {

    public static final String PARAMS = "Ilzam";
    ProgressBar progressBar;
    String params;
    SearchTvAdapter adapter;
    RecyclerView list_search_tv;
    final List<SearchTvResults> searchTvResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tv);

        progressBar = findViewById(R.id.loading_search_tv);
        list_search_tv = findViewById(R.id.list_search_tv);

        list_search_tv.setHasFixedSize(true);
        list_search_tv.setLayoutManager(new LinearLayoutManager(this));
        params = getIntent().getStringExtra(PARAMS);
        SearchTvViewModel.setSEARCH(params);

        SearchTvViewModel searchTvViewModel = ViewModelProviders.of(this).get(SearchTvViewModel.class);
        searchTvViewModel.getSearchTV().observe(this, new Observer<SearchTvResults>() {
            @Override
            public void onChanged(@Nullable SearchTvResults Results) {
                searchTvResults.add(Results);
                if (Results == null){
                    progressBar.setVisibility(View.VISIBLE);
                }
                else {
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter = new SearchTvAdapter(SearchTvActivity.this, searchTvResults.get(0).getResults());
                    list_search_tv.setAdapter(adapter);
                }
            }
        });

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

}
