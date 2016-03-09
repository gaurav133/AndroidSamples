package com.android.sample.VolleySample.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.sample.VolleySample.BuildConfig;
import com.android.sample.VolleySample.R;

/**
 * Created by gaurav on 8/3/16.
 */
public class VolleyFragment extends Fragment {

    private Context mContext;

    private String[] mUrlArray;

    private final String LOG_TAG = VolleyFragment.this.getClass().getSimpleName();

    private RecyclerView mRecyclerView;

    private RecyclerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_volley, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
    //    mRecyclerView.setHasFixedSize(true);

        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "onCreateView VolleyFragment");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), Configuration.ORIENTATION_PORTRAIT, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new RecyclerAdapter(getActivity(), mUrlArray);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }
    

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mUrlArray = getResources().getStringArray(R.array.image_url_array);
    }
}
