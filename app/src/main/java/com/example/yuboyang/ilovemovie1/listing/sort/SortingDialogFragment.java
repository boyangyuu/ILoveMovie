package com.example.yuboyang.ilovemovie1.listing.sort;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.view.menu.ListMenuPresenter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.yuboyang.ilovemovie1.BaseApplication;
import com.example.yuboyang.ilovemovie1.R;
import com.example.yuboyang.ilovemovie1.listing.MoviesListingPresenter;
import com.example.yuboyang.ilovemovie1.listing.MoviesListingPresenterImpl;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yuboyang on 10/14/17.
 */

public class SortingDialogFragment extends DialogFragment {
    @Inject
    MoviesListingPresenter listingPresenter;
    @Inject
    SortPreferance sortPreferance;

    @Bind(R.id.radio_group)
    RadioGroup radioGroup;
    @Bind(R.id.radio_popular)
    RadioButton radio_popular;
    @Bind(R.id.radio_rated)
    RadioButton radio_rated;
    @Bind(R.id.radio_favorite)
    RadioButton radio_favorite;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication application = (BaseApplication)getActivity().getApplication();
        application.getAppComponent().inject(this);
        setRetainInstance(true);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.sorting_options, null);
        ButterKnife.bind(this, inflate);
        initView();
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(inflate);
        dialog.setTitle("Sort By");
        dialog.show();
        return dialog;
    }

    private void initView() {
        this.restoreLastOption();
        radioGroup.setOnCheckedChangeListener(this::onCheckedChanged);
    }

    private void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radio_popular:
                onPopularMoviesSelected();
                listingPresenter.displayMovies();
            case R.id.radio_rated:
                onHighestRatedMoviesSelected();
                listingPresenter.displayMovies();
            case R.id.radio_favorite:
                onFavoritesSelected();
                listingPresenter.displayMovies();
        }
        dismiss();
    }


    public void restoreLastOption() {
        int sortYype = sortPreferance.getSelectedOption();
        if (sortYype == SortType.MOST_POPULAR.getValue()) {
            onPopularMoviesSelected();
        } else if (sortYype == SortType.HIGHEST_RATED.getValue()) {
            onHighestRatedMoviesSelected();
        } else if (sortYype == SortType.FAVORITES.getValue()) {
            onFavoritesSelected();
        } else {
            Log.d("tag", "restoreLastOption: error");
        }
    }
    
    private void onPopularMoviesSelected() {
        radio_favorite.setChecked(true);
    }
    

    private void onFavoritesSelected() {
        radio_favorite.setChecked(true);
    }

    private void onHighestRatedMoviesSelected() {
        radio_rated.setChecked(true);
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }

}
