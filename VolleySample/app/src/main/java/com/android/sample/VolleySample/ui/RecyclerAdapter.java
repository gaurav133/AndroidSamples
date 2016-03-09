package com.android.sample.VolleySample.ui;

import android.content.Context;
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

/**
 * Created by gaurav on 8/3/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ImageHolder> {

    private final String LOG_TAG = RecyclerAdapter.class.getSimpleName();

    private String[] mUrlArray = null;

    private Context mContext;

    /*
    Constructor
    */
    RecyclerAdapter(Context context, String[] urlArray) {
        this.mContext = context;
        mUrlArray = urlArray;
    }

    /*
    This method constructs a viewholder object of type ImageHolder from the given ViewGroup.
     */
    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (BuildConfig.DEBUG) {
            Log.d (LOG_TAG, "onCreateViewHolder call");
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
        final NetworkImageView imageView = holder.mImageView;
        final TextView textView = holder.mDescription;

        imageView.setImageUrl(mUrlArray[position], VolleySingleton.getInstance(mContext).getImageLoader());
        textView.setText(String.valueOf(position));


    }

    @Override
    public int getItemCount() {

        if (BuildConfig.DEBUG) {
            Log.d (LOG_TAG, "Item count: " + mUrlArray.length);
        }
        return mUrlArray.length;
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
}
