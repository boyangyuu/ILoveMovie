package com.example.yuboyang.ilovemovie1.listing.sort;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.yuboyang.ilovemovie1.BaseApplication;

import javax.inject.Inject;

/**
 * Created by yuboyang on 10/15/17.
 */

public class SortPreferanceStore {
    private static final String TAG = "SortingDialogFragment";
    private final Context context;

    public SortPreferanceStore(Context context) {
        this.context = context;
    }

    public int getSelectedOption() {
        SortType defaultType = SortType.MOST_POPULAR;
        SharedPreferences sharedPref = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        int type = sharedPref.getInt(SortType.PREFER, defaultType.getValue());
        return type;
    }

    public void setSelectedOption(SortType selectedOption) {
        SharedPreferences sharedPref = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(SortType.PREFER, selectedOption.getValue());
        editor.apply();
    }

}
