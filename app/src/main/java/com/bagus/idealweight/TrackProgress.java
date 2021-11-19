package com.bagus.idealweight;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bagus.idealweight.Model.BeratModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrackProgress extends AppCompatActivity {
    private static final String LOG_TAG = ExercisePlans.class.getSimpleName();
    DatabaseReference databaseReference;
    BeratModel beratModel;
    FirebaseAuth auth;
    String GetUID;
    ArrayList<String> workout;
    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<Track> mSportsData;
    private TrackAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_progress);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        auth = FirebaseAuth.getInstance();

        GetUID = auth.getCurrentUser().getUid();

        beratModel = new BeratModel();

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);
        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ArrayList that will contain the data.
        mSportsData = new ArrayList<>();
        workout = new ArrayList<>();
        loadData();


        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new TrackAdapter(this, mSportsData, workout);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.

    }

    public void loadData() {
        databaseReference.child("User").child(GetUID).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    BeratModel beratModel = data.getValue(BeratModel.class);
                    workout = beratModel.getExercise();
                    Log.d("LOG_TAG", workout.toString());

                    initializeData();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initializeData() {
        // Get the resources from the XML file.
        String[] sportsList = getResources().getStringArray(R.array.track_title);
        String[] sportsInfo = getResources().getStringArray(R.array.track_category);

        // Clear the existing data (to avoid duplication).
        mSportsData.clear();

        TypedArray trackImageResources =
                getResources().obtainTypedArray(R.array.track_image);

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for (int i = 0; i < sportsList.length; i++) {
            for (int j = 0; j < workout.size(); j++) {
                if (workout.get(j).equals(sportsList[i])) {
                    mSportsData.add(new Track(sportsList[i], sportsInfo[i], trackImageResources.getResourceId(i, 0)));
                }
            }
        }

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();

        trackImageResources.recycle();
    }
}
