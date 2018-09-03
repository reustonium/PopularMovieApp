package com.reustonium.popularmoviesapp;

import com.reustonium.popularmoviesapp.model.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;

interface MovieService {
    String apiKey = "ENTER YOUR KEY";
    @GET("discover/movie?" + "api_key=" + apiKey + "&sort_by=popularity.desc")
    Call<MovieList> getPopularMovies();

    @GET("movie/top_rated?" + "api_key=" + apiKey)
    Call<MovieList> getTopRatedMovies();
}
