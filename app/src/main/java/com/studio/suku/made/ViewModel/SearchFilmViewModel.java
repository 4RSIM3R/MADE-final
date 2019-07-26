package com.studio.suku.made.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.studio.suku.made.Model.SearchFilm;
import com.studio.suku.made.Model.SearchFilmResults;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFilmViewModel extends ViewModel {

    private static final String BASE_URL = "https://api.themoviedb.org";
    private static final String API_KEY = "24f2356bed948a69b6ce4946afbf4f67";
    public static String SEARCH = "Avenger";
    private MutableLiveData<SearchFilmResults> liveData;


    public LiveData<SearchFilmResults> getSearchFilm(){
        if (liveData == null){
            liveData = new MutableLiveData<SearchFilmResults>();
            setLiveData();
        }
        return liveData;
    }

    private void setLiveData(){

        final ArrayList<SearchFilmResults> beans = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SearchFilm searchFilm = retrofit.create(SearchFilm.class);

        Call<SearchFilmResults> call = searchFilm.getSearchFilm(API_KEY, SEARCH);

        call.enqueue(new Callback<SearchFilmResults>() {
            @Override
            public void onResponse(Call<SearchFilmResults> call, Response<SearchFilmResults> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SearchFilmResults> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public static void setSEARCH(String params){
        SEARCH = params;
    }

}
