package com.android.sample.VolleySample.ui;

import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.sample.VolleySample.BuildConfig;
import com.android.sample.VolleySample.R;
import com.android.sample.VolleySample.utils.VolleySingleton;
import com.android.volley.toolbox.NetworkImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by gaurav on 8/3/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ImageHolder> {

    private final String TAG = RecyclerAdapter.class.getSimpleName();

    private ArrayList<String> mUrlList = null;

    private Context mContext;
    /*
    Constructor
    */
    RecyclerAdapter(Context context, ArrayList<String> urlList) {
        this.mContext = context;
        mUrlList = urlList;
    }

    /*
    This method constructs a viewholder object of type ImageHolder from the given ViewGroup.
     */
    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (BuildConfig.DEBUG) {
            Log.d (TAG, "onCreateViewHolder call");
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_card_view, parent, false);
        ImageHolder holder = new ImageHolder(view);
        return holder;
    }


    /*
    Binds the data to respective UI elements inside the holder for a given position.
     */
    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {

        if (BuildConfig.DEBUG) {
            Log.d (TAG, "onBindViewHolder called");
        }
        final NetworkImageView imageView = holder.mImageView;
        final TextView textView = holder.mDescription;

        imageView.setImageUrl(mUrlList.get(position), VolleySingleton.getInstance(mContext).getImageLoader());
        textView.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {

        if (BuildConfig.DEBUG) {
            Log.d (TAG, "Item count: " + mUrlList.size());
        }
        return mUrlList.size();
    }

    public static class ImageHolder extends RecyclerView.ViewHolder {

        NetworkImageView mImageView;
        TextView mDescription;
        CardView mCardView;

        public ImageHolder(View itemView) {
            super(itemView);

            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mImageView = (NetworkImageView) itemView.findViewById(R.id.imageView);
            mDescription = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public void updateData(ArrayList<String> urlList) {
        mUrlList = urlList;
    }

/*
    @Override
    public void loadMoreData(String[] urlArray) {
        */
/** Add data to adapter and refresh it. *//*

        mUrlArray = urlArray;
        notifyDataSetChanged();
    }
*/

}
