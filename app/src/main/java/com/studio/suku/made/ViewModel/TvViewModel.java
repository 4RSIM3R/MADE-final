package com.studio.suku.made.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

import com.studio.suku.made.Model.RequestTv;
import com.studio.suku.made.Model.TvResults;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvViewModel extends ViewModel {

    private static final String BASE_URL = "https://api.themoviedb.org";
    private static final String API_KEY = "24f2356bed948a69b6ce4946afbf4f67";
    private MutableLiveData<TvResults> mutableLiveData;

    public MutableLiveData<TvResults> getTv(){
        if (mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
            setMutableLiveData();

        }
        return mutableLiveData;
    }

    void setMutableLiveData(){

        final ArrayList<TvResults> bean = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestTv requestTv = retrofit.create(RequestTv.class);

        Call<TvResults> call = requestTv.getTv(API_KEY);

        call.enqueue(new Callback<TvResults>() {
            @Override
            public void onResponse(Call<TvResults> call, Response<TvResults> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TvResults> call, Throwable t) {
                t.printStackTrace();
            }
        });



    }
}
