package com.heshmat.reddittopposts.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInit {
    public static final String BASE_URL = "https://www.reddit.com/";
    private Retrofit retrofit;
    private static RetrofitInit retrofitInit;

    private RetrofitInit() {
        Gson gson = new GsonBuilder().create();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    public static RetrofitInit getInstance() {
        if (retrofitInit == null) {
            retrofitInit = new RetrofitInit();

        }
        return retrofitInit;
    }

    public ApiInterface getApiInterface() {


        return retrofit.create(ApiInterface.class);
    }
}
