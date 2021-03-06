package com.example.yuboyang.ilovemovie1.listing.sort;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.yuboyang.ilovemovie1.BaseApplication;
import com.example.yuboyang.ilovemovie1.R;
import com.example.yuboyang.ilovemovie1.listing.MoviesListingPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yuboyang on 10/14/17.
 */

public class SortingDialogFragment extends DialogFragment {
    private static final String TAG = "SortingDialogFragment";
    @Inject
    MoviesListingPresenter listingPresenter;
    @Inject
    SortPreferanceStore sortPreferanceStore;

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
        application.getListComponent().inject(this);
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
                sortPreferanceStore.setSelectedOption(SortType.MOST_POPULAR);
                break;
            case R.id.radio_rated:
                sortPreferanceStore.setSelectedOption(SortType.HIGHEST_RATED);
                break;
            case R.id.radio_favorite:
                sortPreferanceStore.setSelectedOption(SortType.FAVORITES);
                break;
        }
        listingPresenter.displayMovies();
        dismiss();
    }

    public void restoreLastOption() {
        int sortYype = sortPreferanceStore.getSelectedOption();
        if (sortYype == SortType.MOST_POPULAR.getValue()) {
            radio_popular.setChecked(true);
        } else if (sortYype == SortType.HIGHEST_RATED.getValue()) {
            radio_rated.setChecked(true);
        } else if (sortYype == SortType.FAVORITES.getValue()) {
            radio_favorite.setChecked(true);
        } else {
            Log.d("tag", "restoreLastOption: error");
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
