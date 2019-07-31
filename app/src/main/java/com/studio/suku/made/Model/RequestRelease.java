package com.studio.suku.made.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestRelease {

    @GET("3/movie/upcoming")
    Call<ReleaseResults> getUpcomingFilm(
         @Query("api_key") String apiKey
    );
}
