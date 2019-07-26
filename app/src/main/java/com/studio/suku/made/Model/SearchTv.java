package com.studio.suku.made.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchTv {

    @GET("3/search/tv")
    Call<SearchTvResults> getSearchTv(
            @Query("api_key") String Key,
            @Query("query") String name
    );

}
