package com.android.sample.fragmentsample;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gaurav on 10/3/16.
 */
public class FragmentTwo extends Fragment {

    private static final String LOG_TAG = FragmentTwo.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "onCreateView FragmentTwo");
        }
        View rootView = inflater.inflate(R.layout.fragment_two, container, false);
        return rootView;
    }
}
