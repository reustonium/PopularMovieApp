package com.reustonium.popularmoviesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.reustonium.popularmoviesapp.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.detailTitleTV) TextView titleTV;
    @BindView(R.id.detailPosterIV) ImageView posterIV;
    @BindView(R.id.detailPlotTitleTV) TextView plotTitleTV;
    @BindView(R.id.detailPlotTV) TextView plotTv;
    @BindView(R.id.detailRatingTitleTV) TextView ratingTitleTV;
    @BindView(R.id.detailRatingTV) TextView ratingTV;
    @BindView(R.id.releasedTV) TextView releasedTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        if(movie != null) {
            titleTV.setText(movie.getTitle());
            String baseUrl = "http://image.tmdb.org/t/p/";
            String size = "w342"; //"w185/";
            String url = movie.getPosterPath();
            Picasso.with(this).load(baseUrl+size+url).into(posterIV);
            plotTitleTV.setText("Plot Summary: ");
            plotTv.setText(movie.getOverview());
            ratingTitleTV.setText("Rating: ");
            ratingTV.setText(movie.getVoteAverage().toString());
            releasedTV.setText("Release Date: " + movie.getReleaseDate());
        }
    }
}
