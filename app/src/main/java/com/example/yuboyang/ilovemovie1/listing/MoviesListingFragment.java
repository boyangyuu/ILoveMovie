package com.example.yuboyang.ilovemovie1.listing;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuboyang.ilovemovie1.BaseApplication;
import com.example.yuboyang.ilovemovie1.Movie;
import com.example.yuboyang.ilovemovie1.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class MoviesListingFragment extends Fragment implements MoviesListingView {

    @Inject
    MoviesListingPresenter moviesPresenter;

    @Bind(R.id.movies_listing)
    RecyclerView moviesListing;

    private MoviesListingAdapter adapter;
    private Context activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication application = (BaseApplication)getActivity().getApplication();
        application.createListingComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);
        initLayout();
        return view;
    }

    private void initLayout() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        moviesListing.setHasFixedSize(false);
        moviesListing.setLayoutManager(layoutManager);
        adapter = new MoviesListingAdapter(this);
        moviesListing.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviesPresenter.setView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        this.moviesPresenter.onDestroy();
        activity = null;
    }

    @Override
    public void showMovies(List<Movie> movies) {
        adapter.setMovies(movies);
    }

    @Override
    public void loadingStarted() {

    }

    @Override
    public void loadingFailed(String errorMessage) {

    }

    @Override
    public void onMovieClicked(Movie movie) {

    }
}
