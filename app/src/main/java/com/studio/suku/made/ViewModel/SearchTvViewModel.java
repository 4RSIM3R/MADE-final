package com.studio.suku.made.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.studio.suku.made.Model.SearchFilm;
import com.studio.suku.made.Model.SearchFilmResults;
import com.studio.suku.made.Model.SearchTv;
import com.studio.suku.made.Model.SearchTvResults;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchTvViewModel extends ViewModel {

    private static final String BASE_URL = "https://api.themoviedb.org";
    private static final String API_KEY = "24f2356bed948a69b6ce4946afbf4f67";
    public static String SEARCH = "BBC";
    private MutableLiveData<SearchTvResults> liveData;


    public LiveData<SearchTvResults> getSearchTV(){
        if (liveData == null){
            liveData = new MutableLiveData<SearchTvResults>();
            setLiveData();
        }
        return liveData;
    }

    private void setLiveData(){

        final ArrayList<SearchTvResults> beans = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SearchTv searchTv = retrofit.create(SearchTv.class);

        Call<SearchTvResults> call = searchTv.getSearchTv(API_KEY, SEARCH);

        call.enqueue(new Callback<SearchTvResults>() {
            @Override
            public void onResponse(Call<SearchTvResults> call, Response<SearchTvResults> response) {
                
            }

            @Override
            public void onFailure(Call<SearchTvResults> call, Throwable t) {

            }
        });
    }

//    public static void setSEARCH(String params){
//        SEARCH = params;
//    }

}
