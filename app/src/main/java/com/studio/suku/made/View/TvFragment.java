package com.studio.suku.made.View;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;
import com.studio.suku.made.Adapter.FilmAdapter;
import com.studio.suku.made.Adapter.TvAdapter;
import com.studio.suku.made.Model.TvResults;
import com.studio.suku.made.R;
import com.studio.suku.made.SearchFilmActivity;
import com.studio.suku.made.SearchTvActivity;
import com.studio.suku.made.ViewModel.FilmViewModel;
import com.studio.suku.made.ViewModel.TvViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {

    View view;
    RecyclerView list_tv;
    ProgressBar progressBarTv;
    TvAdapter tvAdapter;
    SearchBox search_tv;
    private final List<TvResults> beanList = new ArrayList<>();


    public TvFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TvViewModel tvViewModel = ViewModelProviders.of(getActivity()).get(TvViewModel.class);
        tvViewModel.getTv().observe(getActivity(), new Observer<TvResults>() {
            @Override
            public void onChanged(@Nullable TvResults tvResults) {
                beanList.add(tvResults);
                Log.d("Datanya", "Panjang nya " + beanList.get(0).getResults().get(0).getName());
                if (tvResults == null){
                    showLoading(true);
                }
                else {
                    showLoading(false);
                    tvAdapter = new TvAdapter(getActivity(),  beanList.get(0).getResults());
                    list_tv.setAdapter(tvAdapter);
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tv, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list_tv = view.findViewById(R.id.list_tv);
        search_tv = view.findViewById(R.id.search_tv);
        progressBarTv = view.findViewById(R.id.progressBarTv);
        list_tv.setHasFixedSize(true);
        list_tv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        search_tv.setSearchListener(new SearchBox.SearchListener() {
            @Override
            public void onSearchOpened() {

            }

            @Override
            public void onSearchCleared() {

            }

            @Override
            public void onSearchClosed() {

            }

            @Override
            public void onSearchTermChanged(String s) {

            }

            @Override
            public void onSearch(String s) {
                Intent intent = new Intent(getActivity(), SearchTvActivity.class);
                intent.putExtra(SearchTvActivity.PARAMS, s);
                startActivity(intent);
            }

            @Override
            public void onResultClick(SearchResult searchResult) {

            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBarTv.setVisibility(View.VISIBLE);
        } else {
            progressBarTv.setVisibility(View.GONE);
        }
    }
}
