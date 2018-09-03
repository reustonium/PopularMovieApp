package com.reustonium.popularmoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.reustonium.popularmoviesapp.model.Movie;
import com.reustonium.popularmoviesapp.model.MovieList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MovieRecyclerViewAdapter.ItemClickListener{

    private List<Movie> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sortByPopular();
    }

    private void sortByPopular() {
        //TODO show loading spinner
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);
        Call<MovieList> call = movieService.getPopularMovies();
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                populateMovieList(response.body().getMovies());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                //TODO Display Error Text
            }
        });
    }

    private void sortByRating() {
        //TODO show loading spinner
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);
        Call<MovieList> call = movieService.getTopRatedMovies();
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                populateMovieList(response.body().getMovies());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                //TODO Display Error Text
            }
        });
    }

    private void populateMovieList(List<Movie> movies) {
        mMovies = movies;
        RecyclerView recyclerView = findViewById(R.id.moviesRV);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        MovieRecyclerViewAdapter mAdapter = new MovieRecyclerViewAdapter(this, movies);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View v, int pos) {
        Intent movieDetailIntent = new Intent(this, MovieDetailActivity.class);
        Movie movie = mMovies.get(pos);
        if(movie != null){
            movieDetailIntent.putExtra("movie", mMovies.get(pos));
            startActivity(movieDetailIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selected = item.getItemId();
        switch (selected){
            case R.id.menu_sort_popular:
                sortByPopular();
                return true;
            case R.id.menu_sort_rating:
                sortByRating();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
