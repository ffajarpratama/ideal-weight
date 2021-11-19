package com.bagus.idealweight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {
    // Member variables.
    private ArrayList<Track> mSportsData;
    private Context mContext;
    private ImageView mEasyImage;

    ArrayList<String> workout;

    /**
     * Constructor that passes in the sports data and the context.
     *
     * @param sportsData ArrayList containing the sports data.
     * @param context Context of the application.
     */
    TrackAdapter(Context context, ArrayList<Track> sportsData, ArrayList<String> workout) {
        this.workout = workout;
        this.mSportsData = sportsData;
        this.mContext = context;
    }

    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent The ViewGroup into which the new View will be added
     *               after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public TrackAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new TrackAdapter.ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item_trackprogress, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(TrackAdapter.ViewHolder holder, int position) {
        // Get current sport.
        Track currentSport = mSportsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentSport);
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mSportsData.size();
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mCategoryText;
        private int index;
        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.tracktitle);
            mCategoryText = itemView.findViewById(R.id.trackcategory);
            mEasyImage = itemView.findViewById(R.id.trackimage);
            index = 0;

        }

        void bindTo(Track currentSport){
            mTitleText.setText(currentSport.getTitle());
            mCategoryText.setText(currentSport.getInfo());
            Glide.with(mContext).load(currentSport.getImageResource()).into(mEasyImage);

        }
    }
}
