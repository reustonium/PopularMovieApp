package com.reustonium.popularmoviesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.reustonium.popularmoviesapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private final List<Movie> mMovies;
    private final LayoutInflater mLayoutInflater;
    private ItemClickListener mItemClickListener;

    public MovieRecyclerViewAdapter(Context context, List<Movie> movies) {
        mMovies = movies;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String baseUrl = "http://image.tmdb.org/t/p/";
        String size = "w342"; //"w185/";
        String url = mMovies.get(position).getPosterPath();
        Picasso.with(holder.movieIV.getContext()).load(baseUrl+size+url).into(holder.movieIV);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final ImageView movieIV;

        ViewHolder(View itemView) {
            super(itemView);
            movieIV = itemView.findViewById(R.id.detailPosterIV);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }
}
