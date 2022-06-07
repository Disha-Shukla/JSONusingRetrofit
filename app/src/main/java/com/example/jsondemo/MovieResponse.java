package com.example.jsondemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {

    @SerializedName("show")
    @Expose
    private Movie show;

    public Movie getShow() {
        return show;
    }
}
