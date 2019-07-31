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
import android.widget.Toast;

import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;
import com.studio.suku.made.Adapter.FilmAdapter;
import com.studio.suku.made.Model.MoviesResults;
import com.studio.suku.made.R;
import com.studio.suku.made.SearchFilmActivity;
import com.studio.suku.made.ViewModel.FilmViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends Fragment {

    private RecyclerView list_film;
    private FilmAdapter filmAdapter;
    private ProgressBar progressBar;
    SearchBox searchBox;
    private final List<MoviesResults> beanList = new ArrayList<>();
    String title;



    public FilmFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Call The View Model Here
        final FilmViewModel filmViewModel = ViewModelProviders.of(getActivity()).get(FilmViewModel.class);
        filmViewModel.getFilms().observe(getActivity(), new Observer<MoviesResults>() {
            @Override
            public void onChanged(@Nullable MoviesResults moviesResults) {
                beanList.add(moviesResults);
                Log.d("Datanya", "Panjang nya " + beanList.get(0).getResults().get(0).getTitle());
                if (moviesResults == null){
                   showLoading(true);
                }
                else{
                    showLoading(false);
                    list_film.setAdapter(new FilmAdapter(getContext(), beanList.get(0).getResults(), new FilmAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(MoviesResults.ResultsBean item) {
                            Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
                        }
                    }));
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_film, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list_film = view.findViewById(R.id.list_film);
        searchBox = view.findViewById(R.id.search_film);
        progressBar = view.findViewById(R.id.progressBar);
        list_film.setHasFixedSize(true);
        list_film.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        searchBox.setSearchListener(new SearchBox.SearchListener() {
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
                Intent intent = new Intent(getActivity(), SearchFilmActivity.class);
                intent.putExtra(SearchFilmActivity.PARAMS, s);
                startActivity(intent);
            }

            @Override
            public void onResultClick(SearchResult searchResult) {

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
