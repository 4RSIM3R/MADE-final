package com.studio.suku.made.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.studio.suku.made.Model.MoviesResults;
import com.studio.suku.made.Model.RequestFilm;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmViewModel extends ViewModel {

    private static final String BASE_URL = "https://api.themoviedb.org";
    private static final String API_KEY = "24f2356bed948a69b6ce4946afbf4f67";
    private MutableLiveData<MoviesResults> liveData;

    //Request To API here

    public LiveData<MoviesResults> getFilms(){
        if (liveData == null){
            liveData = new MutableLiveData<MoviesResults>();
            setLiveData();

        }
        return liveData;
    }

    private void setLiveData(){
        final ArrayList<MoviesResults> beans = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestFilm requestFilm = retrofit.create(RequestFilm.class);
        Call<MoviesResults> call = requestFilm.getMovies(API_KEY);

        call.enqueue(new Callback<MoviesResults>() {
            @Override
            public void onResponse(Call<MoviesResults> call, Response<MoviesResults> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MoviesResults> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
