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

public class MediumWorkout extends AppCompatActivity {

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
        setContentView(R.layout.activity_medium_workout);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        auth = FirebaseAuth.getInstance();
        GetUID = auth.getCurrentUser().getUid();

        beratModel = new BeratModel();
        workout = new ArrayList<>();
        loadData();

        sportsList = getResources().getStringArray(R.array.track_title);
        sportsCalories = getResources().getStringArray(R.array.track_calories);

    }

    public void onButtonClickMinotaur(View view) {
        workout.add("Minotaur");
        beratModel.setExercise(workout);
        for (int i = 0; i < sportsList.length; i++) {
            if (sportsList[i].equals("Minotaur")) {
                double kalori = beratModel.getSelisihKalori() - Double.parseDouble(sportsCalories[i]);
                beratModel.setSelisihKalori(kalori);
                beratModel.setSelisihBerat(Math.round(kalori / 7716.18));
            }
        }
        databaseReference.child("User").child(GetUID).child(beratModel.getKey()).setValue(beratModel);
        workout = new ArrayList<>();

        Toast.makeText(getApplicationContext(), "You selected Minotaur", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(this, Minotaur.class);
        startActivity(myIntent);
    }

    public void onButtonClickPegasus(View view) {
        workout.add("Pegasus");
        beratModel.setExercise(workout);
        for (int i = 0; i < sportsList.length; i++) {
            if (sportsList[i].equals("Pegasus")) {
                double kalori = beratModel.getSelisihKalori() - Double.parseDouble(sportsCalories[i]);
                beratModel.setSelisihKalori(kalori);
                beratModel.setSelisihBerat(Math.round(kalori / 7716.18));
            }
        }
        databaseReference.child("User").child(GetUID).child(beratModel.getKey()).setValue(beratModel);
        workout = new ArrayList<>();

        Toast.makeText(getApplicationContext(), "You selected Pegasus", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(this, Pegasus.class);
        startActivity(myIntent);
    }

    public void onButtonClickMedusa(View view) {
        workout.add("Medusa");
        beratModel.setExercise(workout);
        for (int i = 0; i < sportsList.length; i++) {
            if (sportsList[i].equals("Medusa")) {
                double kalori = beratModel.getSelisihKalori() - Double.parseDouble(sportsCalories[i]);
                beratModel.setSelisihKalori(kalori);
                beratModel.setSelisihBerat(Math.round(kalori / 7716.18));
            }
        }
        databaseReference.child("User").child(GetUID).child(beratModel.getKey()).setValue(beratModel);
        workout = new ArrayList<>();

        Toast.makeText(getApplicationContext(), "You selected Medusa", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(this, Medusa.class);
        startActivity(myIntent);
    }

    public void onButtonClickPhoenix(View view) {
        workout.add("Phoenix");
        beratModel.setExercise(workout);
        for (int i = 0; i < sportsList.length; i++) {
            if (sportsList[i].equals("Phoenix")) {
                double kalori = beratModel.getSelisihKalori() - Double.parseDouble(sportsCalories[i]);
                beratModel.setSelisihKalori(kalori);
                beratModel.setSelisihBerat(Math.round(kalori / 7716.18));
            }
        }
        databaseReference.child("User").child(GetUID).child(beratModel.getKey()).setValue(beratModel);
        workout = new ArrayList<>();

        Toast.makeText(getApplicationContext(), "You selected Phoenix", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(this, Phoenix.class);
        startActivity(myIntent);
    }

    public void loadData() {
        databaseReference.child("User").child(GetUID).addValueEventListener(new ValueEventListener() {
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
