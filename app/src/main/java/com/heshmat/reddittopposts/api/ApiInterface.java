package com.heshmat.reddittopposts.api;

import com.heshmat.reddittopposts.models.RedditPosts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top.json?limit=2")
    Call<RedditPosts> getData();
    @GET("top.json")
    Call<RedditPosts> getDataBefore(@Query("before") String before, @Query("limit") int limit);
    @GET("top.json")
    Call<RedditPosts> getDataAfter(@Query("after") String before, @Query("limit") int limit);


}
