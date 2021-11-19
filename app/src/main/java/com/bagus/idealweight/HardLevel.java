package com.bagus.idealweight;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HardLevel extends AppCompatActivity {

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<Hard> mSportsData;
    private HardAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_level);

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ArrayList that will contain the data.
        mSportsData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new HardAdapter(this, mSportsData);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();
    }

    /**
     * Initialize the sports data from resources.
     */
    private void initializeData() {
        // Get the resources from the XML file.
        String[] sportsList = getResources()
                .getStringArray(R.array.sports_titles_hard);
        String[] sportsInfo = getResources()
                .getStringArray(R.array.sports_info);

        // Clear the existing data (to avoid duplication).
        mSportsData.clear();

        TypedArray easyImageResources =
                getResources().obtainTypedArray(R.array.sports_images_hard);

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<sportsList.length;i++){
            mSportsData.add(new Hard(sportsList[i],sportsInfo[i], easyImageResources.getResourceId(i,0)));
        }

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();

        easyImageResources.recycle();
    }
}
