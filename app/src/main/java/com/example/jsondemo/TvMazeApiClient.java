package com.example.jsondemo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvMazeApiClient {

    public static String BASE_URL ="https://api.tvmaze.com/";
    private static Retrofit retrofit;
    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
