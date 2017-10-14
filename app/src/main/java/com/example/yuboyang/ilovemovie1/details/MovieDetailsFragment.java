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
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
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

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yuboyang on 10/12/17.
 */

public class MovieDetailsFragment extends Fragment implements MovieDetailView {

    private static final String TAG = "MovieDetailsFragment";
    private Context activity;
    private Movie movie;

    @Inject
    MovieDetailPresenter presenter;

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
    @Bind(R.id.trailers_label)
    TextView label;
    @Bind(R.id.trailers)
    LinearLayout trailers;
    @Bind(R.id.trailers_container)
    HorizontalScrollView horizontalScrollView;
    @Bind(R.id.reviews_label)
    TextView reviews;
    @Bind(R.id.reviews)
    LinearLayout reviewsContainer;

    @Bind(R.id.favorite)
    FloatingActionButton bt_favorite;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        initDetail();
    }

    private void initDetail() {
        Glide.with(this)
                .load(Api.getBackdropPath(this.movie.getBackdropPath()))
                .into(this.iv_poster);


        this.tv_movie_desc.setText(this.movie.getOverview());
        this.tv_movie_name.setText(this.movie.getTitle());
        this.tv_movie_year.setText(String.format("Release Date: %s", this.movie.getReleaseDate()));
        this.tv_movie_rating.setText(String.format("%s/10", String.valueOf(this.movie.getVoteAverage())));

        presenter.displayReviews(movie.getId());
        presenter.displayTrails(movie.getId());
    }

    @Override
    public void showTrailers(List<Video> videos) {
        Log.d(TAG, "showTrailers() called with: videos = [" + videos + "]");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        RequestManager requestManager = Glide.with(this);
        for (Video video : videos) {
//            Log.i(TAG, "showTrailers: video" + video);
            View view = inflater.inflate(R.layout.video, this.trailers, false);
            ImageView thumbView = ButterKnife.findById(view, R.id.video_thumb);
            requestManager.load(Video.getThumbnailUrl(video))
                    .centerCrop()
                    .into(thumbView);
            this.trailers.addView(view);
        }
    }

    @Override
    public void showReviews(List<Review> reviews) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View inflate = inflater.inflate(R.layout.review, this.reviewsContainer, false);
        for (Review review : reviews) {
            TextView tv_author = ButterKnife.findById(inflate, R.id.review_author);
            tv_author.setText(review.getAuthor());
            TextView tv_content = ButterKnife.findById(inflate, R.id.review_content);
            tv_content.setText(review.getContent());
            this.reviewsContainer.addView(inflate);
        }
    }
}
