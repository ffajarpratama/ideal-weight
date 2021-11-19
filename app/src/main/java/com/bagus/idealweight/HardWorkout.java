package com.bagus.idealweight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bagus.idealweight.Model.BeratModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HardWorkout extends AppCompatActivity {

    DatabaseReference databaseReference;

    FirebaseAuth auth;

    BeratModel beratModel;

    String GetUID;

    ArrayList<String> workout;

    String[] sportsList;

    String[] sportsCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_workout);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        auth = FirebaseAuth.getInstance();
        GetUID = auth.getCurrentUser().getUid();

        beratModel = new BeratModel();
        workout = new ArrayList<>();
        loadData();

        sportsList = getResources().getStringArray(R.array.track_title);
        sportsCalories = getResources().getStringArray(R.array.track_calories);

    }

    public void onButtonClickZeus(View view) {
        workout.add("Zeus");
        beratModel.setExercise(workout);
        for (int i = 0; i < sportsList.length; i++) {
            if (sportsList[i].equals("Zeus")) {
                double kalori = beratModel.getSelisihKalori() - Double.parseDouble(sportsCalories[i]);
                beratModel.setSelisihKalori(kalori);
                beratModel.setSelisihBerat(Math.round(kalori / 7716.18));
            }
        }
        databaseReference.child("User").child(GetUID).child(beratModel.getKey()).setValue(beratModel);
        workout = new ArrayList<>();

        Toast.makeText(getApplicationContext(), "You selected Zeus", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(this, Zeus.class);
        startActivity(myIntent);
    }

    public void onButtonClickPoseidon(View view) {
        workout.add("Poseidon");
        beratModel.setExercise(workout);
        for (int i = 0; i < sportsList.length; i++) {
            if (sportsList[i].equals("Poseidon")) {
                double kalori = beratModel.getSelisihKalori() - Double.parseDouble(sportsCalories[i]);
                beratModel.setSelisihKalori(kalori);
                beratModel.setSelisihBerat(Math.round(kalori / 7716.18));
            }
        }
        databaseReference.child("User").child(GetUID).child(beratModel.getKey()).setValue(beratModel);
        workout = new ArrayList<>();

        Toast.makeText(getApplicationContext(), "You selected Poseidon", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(this, Poseidon.class);
        startActivity(myIntent);
    }

    public void onButtonClickPerseus(View view) {
        workout.add("Perseus");
        beratModel.setExercise(workout);
        for (int i = 0; i < sportsList.length; i++) {
            if (sportsList[i].equals("Perseus")) {
                double kalori = beratModel.getSelisihKalori() - Double.parseDouble(sportsCalories[i]);
                beratModel.setSelisihKalori(kalori);
                beratModel.setSelisihBerat(Math.round(kalori / 7716.18));
            }
        }
        databaseReference.child("User").child(GetUID).child(beratModel.getKey()).setValue(beratModel);
        workout = new ArrayList<>();

        Toast.makeText(getApplicationContext(), "You selected Perseus", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(this, Perseus.class);
        startActivity(myIntent);
    }

    public void onButtonClicApollo(View view) {
        workout.add("Apollo");
        beratModel.setExercise(workout);
        for (int i = 0; i < sportsList.length; i++) {
            if (sportsList[i].equals("Apollo")) {
                double kalori = beratModel.getSelisihKalori() - Double.parseDouble(sportsCalories[i]);
                beratModel.setSelisihKalori(kalori);
                beratModel.setSelisihBerat(Math.round(kalori / 7716.18));
            }
        }
        databaseReference.child("User").child(GetUID).child(beratModel.getKey()).setValue(beratModel);
        workout = new ArrayList<>();

        Toast.makeText(getApplicationContext(), "You selected Apollo", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(this, Apollo.class);
        startActivity(myIntent);
    }

    public void loadData() {
        databaseReference.child("User").child(GetUID).addValueEventListener(new ValueEventListener() {
            int i = 0;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    beratModel = data.getValue(BeratModel.class);
                    beratModel.setKey(data.getKey());
                    if (beratModel.getExercise() != null) {
                        for (int i = 0; i < beratModel.getExercise().size(); i++) {
                            workout.add(beratModel.getExercise().get(i));

                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
