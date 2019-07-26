package com.studio.suku.made.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestFilm {

    //Bind Params For URL here

    @GET("3/movie/upcoming")
    Call<MoviesResults> getMovies(
            @Query("api_key") String Key
    );

}
