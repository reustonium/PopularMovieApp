package com.reustonium.popularmoviesapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {

    @SerializedName("results")
    @Expose
    private final List<Movie> movies = null;

    public List<Movie> getMovies() {
        return movies;
    }

}