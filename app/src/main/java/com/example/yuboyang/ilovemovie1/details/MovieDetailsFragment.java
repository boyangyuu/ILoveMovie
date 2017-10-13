package com.example.yuboyang.ilovemovie1.details;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yuboyang.ilovemovie1.BaseApplication;
import com.example.yuboyang.ilovemovie1.Constants;
import com.example.yuboyang.ilovemovie1.Movie;
import com.example.yuboyang.ilovemovie1.R;
import com.example.yuboyang.ilovemovie1.Review;
import com.example.yuboyang.ilovemovie1.Video;
import com.example.yuboyang.ilovemovie1.listing.AppComponent;
import com.example.yuboyang.ilovemovie1.util.Api;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yuboyang on 10/12/17.
 */

public class MovieDetailsFragment extends Fragment implements MovieDetailView {

    private static final String TAG = "MovieDetailsFragment";
    private Context activity;
    private Movie movie;

    @Bind(R.id.movie_poster)
    ImageView iv_poster;
    @Bind(R.id.movie_name)
    TextView tv_movie_name;
    @Bind(R.id.movie_year)
    TextView tv_movie_year;
    @Bind(R.id.movie_rating)
    TextView tv_movie_rating;
    @Bind(R.id.movie_description)
    TextView tv_movie_desc;
    @Bind(R.id.favorite)
    FloatingActionButton bt_favorite;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("tag", "onAttach() called with: context = [" + context + "]");
        this.activity = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.getAppComponent().inject(this);
        Movie movie = getActivity().getIntent().getParcelableExtra(Constants.MOVIE);
        this.movie = movie;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        ButterKnife.bind(this, view);
        initDetail();
        return view;
    }

    private void initDetail() {
        Glide.with(this)
                .load(Api.getBackdropPath(this.movie.getBackdropPath()))
                .into(this.iv_poster);

        Log.d(TAG, this.movie.getBackdropPath());
        this.tv_movie_desc.setText(this.movie.getOverview());
        this.tv_movie_name.setText(this.movie.getTitle());
        this.tv_movie_year.setText(String.format("Release Date: %s", this.movie.getReleaseDate()));
        this.tv_movie_rating.setText(String.format("%s/10", String.valueOf(this.movie.getVoteAverage())));
    }

    @Override
    public void showTrailers(List<Video> videos) {

    }

    @Override
    public void showReviews(List<Review> reviews) {

    }
}
