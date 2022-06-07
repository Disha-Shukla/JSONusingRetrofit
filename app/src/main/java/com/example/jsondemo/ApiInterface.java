package com.example.jsondemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("search/shows?q=girls")
    Call<List<MovieResponse>> getMovies();
}
