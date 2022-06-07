package com.example.jsondemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("summary")
    @Expose
    private String summary;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @SerializedName("image")
    @Expose
    private Image image;
    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }
    public Image getImageUrl() {
        return image;
    }

    public void setImageUrl(Image image) {
        this.image = image;
    }
}
