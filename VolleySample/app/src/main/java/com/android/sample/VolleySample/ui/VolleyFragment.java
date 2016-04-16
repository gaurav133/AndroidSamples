package com.android.sample.VolleySample.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.sample.VolleySample.BuildConfig;
import com.android.sample.VolleySample.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by gaurav on 8/3/16.
 */
public class VolleyFragment extends Fragment implements View.OnClickListener, View.OnLayoutChangeListener {

    /** Constant specifying the number of images to show per page */
    private static final int IMAGES_DISPLAY_PER_PAGE = 10;

    /** Constant specifying total number of images */
    private int TOTAL_IMAGE_COUNT;

    /** Button to load more images and display a progress bar while loading */
    private Button mLoadMoreBtn;

    /** Index of last item currently displayed */
    private int lastItemIndex;

    /** Boolean to indicate whether loading is in progress */
    private boolean isLoading = false;

    /** Progress view to display while images are loaded. */
    private ProgressBar mProgressBar;

    /** Framelayout at bottom for loading changes */
    private FrameLayout mLoadingLayout;

    /** Textview to indicate there are no more items left to load. */
    private TextView mNoItemsLeftTextView;

    private Context mContext;

    private String[] mAllUrlArray;

    private ArrayList<String> mDisplayUrlList;

    private final String LOG_TAG = VolleyFragment.this.getClass().getSimpleName();

    private RecyclerView mRecyclerView;

    private RecyclerAdapter mAdapter;

    private NetworkChangeReceiver mReceiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        mReceiver = new NetworkChangeReceiver();
        getActivity().registerReceiver(mReceiver, filter);

        View rootView = inflater.inflate(R.layout.fragment_volley, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mLoadingLayout = (FrameLayout) rootView.findViewById(R.id.fl_loading_view);

        mLoadMoreBtn = (Button) mLoadingLayout.findViewById(R.id.btn_load_more);
        mProgressBar = (ProgressBar) mLoadingLayout.findViewById(R.id.pb_loading);
        mNoItemsLeftTextView = (TextView) mLoadingLayout.findViewById(R.id.tv_no_more_items);

        mLoadMoreBtn.setOnClickListener(this);
        //    mRecyclerView.setHasFixedSize(true);

        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "onCreateView VolleyFragment");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), Configuration.ORIENTATION_PORTRAIT, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new RecyclerAdapter(getActivity(), mDisplayUrlList);

        if (mDisplayUrlList.size() == TOTAL_IMAGE_COUNT) {
            mLoadMoreBtn.setVisibility(View.GONE);
            mNoItemsLeftTextView.setVisibility(View.VISIBLE);
        } else {
            mLoadMoreBtn.setVisibility(View.VISIBLE);
            mNoItemsLeftTextView.setVisibility(View.GONE);
        }
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnLayoutChangeListener(this);

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

        mAllUrlArray = getResources().getStringArray(R.array.image_url_array);
        TOTAL_IMAGE_COUNT = mAllUrlArray.length;

        int numItems = getActivity().getPreferences(Context.MODE_PRIVATE).getInt("num_loaded_items", 0);

        lastItemIndex = (numItems == 0 ? IMAGES_DISPLAY_PER_PAGE : numItems);
        mDisplayUrlList = new ArrayList<>(IMAGES_DISPLAY_PER_PAGE);

        int i;
        // Add first 10 images to url array.
        for (i = 0; i < lastItemIndex; i++) {
            mDisplayUrlList.add(mAllUrlArray[i]);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        /** Store number of loaded items in shared preferences. */
        getActivity().getPreferences(Context.MODE_PRIVATE).edit().putInt("num_loaded_items", lastItemIndex).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load_more : /** Load more items here and notify adapter */
                int i;
                for (i = lastItemIndex; i < lastItemIndex + IMAGES_DISPLAY_PER_PAGE && i < TOTAL_IMAGE_COUNT; i++) {
                    mDisplayUrlList.add(mAllUrlArray[i]);
                }
                lastItemIndex = i;
                mAdapter.updateData(mDisplayUrlList);
                mLoadMoreBtn.setVisibility(View.GONE);
                mProgressBar.setAlpha(1f);
                mProgressBar.setVisibility(View.VISIBLE);
                loadAnimation();
        }
    }

    /**
     * Cross fade animation to display between views.
     */
    private void loadAnimation() {
        mLoadMoreBtn.setVisibility(View.VISIBLE);
        mLoadMoreBtn.setAlpha(0f);

        mLoadMoreBtn.animate()
                .alpha(1f)
                .setDuration(5000)
                .setListener(null);

        mProgressBar.animate()
                .setDuration(5000)
                .alpha(0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mProgressBar.setVisibility(View.GONE);
                        super.onAnimationEnd(animation);
                    }
                });
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        switch(v.getId()) {
            case R.id.recycler_view :
                if (BuildConfig.DEBUG) {
                    Log.d (LOG_TAG, "Items loaded.");
                }
                //mLoadMoreBtn.setVisibility(View.VISIBLE);
        }
    }

    class NetworkChangeReceiver extends BroadcastReceiver {

        private final String TAG = NetworkChangeReceiver.class.getSimpleName();

        @Override
        public void onReceive(Context context, Intent intent) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "onReceive called");
            }

            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}