package com.heshmat.reddittopposts.api;

import com.heshmat.reddittopposts.models.RedditResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top.json?limit=2")
    Call<RedditResponse> getData();
    @GET("top.json")
    Call<RedditResponse> getDataBefore(@Query("before") String before, @Query("limit") int limit);
    @GET("top.json")
    Call<RedditResponse> getDataAfter(@Query("after") String before, @Query("limit") int limit);


}
