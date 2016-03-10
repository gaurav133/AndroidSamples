package com.android.sample.fragmentsample;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private final String FRAGMENT_TWO_TAG = "fragmentTwoTag";

    private final String LOG_TAG = FragmentTwo.class.getSimpleName();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "MainActivityFragment : onCreateView");
        }
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "MainActivityFragment : onCreate");
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "MainActivityFragment : onPause");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "MainActivityFragment : onStart");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "MainActivityFragment : onResume");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "MainActivityFragment : onActivityCreated");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "MainActivityFragment : onAttach");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "MainActivityFragment : onDestroy");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "MainActivityFragment : onDestroyView");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "MainActivityFragment : onDetach");
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "MainActivityFragment : onStop");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (BuildConfig.DEBUG) {
            Log.d (LOG_TAG, "MainActivityFragment : onAttach");
        }
    }

    /*public void buttonClick(View v) {

        // Load fragment two on button click.
        FragmentManager manager = getActivity().getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(new FragmentTwo(), FRAGMENT_TWO_TAG);
        transaction.commit();
    }*/
}
