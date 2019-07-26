package com.studio.suku.made.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchFilm {

    @GET("/3/search/movie")
    Call<SearchFilmResults> getSearchFilm(
            @Query("api_key") String Key,
            @Query("query") String name
    );

}
