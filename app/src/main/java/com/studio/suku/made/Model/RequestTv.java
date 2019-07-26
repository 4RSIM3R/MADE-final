package com.studio.suku.made.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestTv {

    @GET("3/discover/tv")
    Call<TvResults> getTv(
        @Query("api_key") String Api_key
    );

}
