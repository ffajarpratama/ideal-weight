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

public class EasyWorkout extends AppCompatActivity {

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
        setContentView(R.layout.activity_easy_workout);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        auth = FirebaseAuth.getInstance();
        GetUID = auth.getCurrentUser().getUid();

        beratModel = new BeratModel();
        workout = new ArrayList<>();
        loadData();


        sportsList = getResources().getStringArray(R.array.track_title);
        sportsCalories = getResources().getStringArray(R.array.track_calories);


    }

    public void onButtonClickHephaestus(View view) {
        workout.add("Hephaestus");
        beratModel.setExercise(workout);
        for (int i = 0; i < sportsList.length; i++) {
            if (sportsList[i].equals("Hephaestus")) {
                double kalori = beratModel.getSelisihKalori() - Double.parseDouble(sportsCalories[i]);
                beratModel.setSelisihKalori(kalori);
                beratModel.setSelisihBerat(Math.round(kalori / 7716.18));
            }
        }
        databaseReference.child("User").child(GetUID).child(beratModel.getKey()).setValue(beratModel);
        workout = new ArrayList<>();

        Toast.makeText(getApplicationContext(), "You selected Hephaestus", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(this, Hephaestus.class);
        startActivity(myIntent);
    }

    public void onButtonClickGryphon(View view) {
        workout.add("Gryphon");
        beratModel.setExercise(workout);
        for (int i = 0; i < sportsList.length; i++) {
            if (sportsList[i].equals("Gryphon")) {
                double kalori = beratModel.getSelisihKalori() - Double.parseDouble(sportsCalories[i]);
                beratModel.setSelisihKalori(kalori);
                beratModel.setSelisihBerat(Math.round(kalori / 7716.18));
            }
        }
        databaseReference.child("User").child(GetUID).child(beratModel.getKey()).setValue(beratModel);
        workout = new ArrayList<>();

        Toast.makeText(getApplicationContext(), "You selected Gryphon", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(this, Gryphon.class);
        startActivity(myIntent);
    }

    public void onButtonClickAchilles(View view) {
        workout.add("Achilles");
        beratModel.setExercise(workout);
        for (int i = 0; i < sportsList.length; i++) {
            if (sportsList[i].equals("Achilles")) {
                double kalori = beratModel.getSelisihKalori() - Double.parseDouble(sportsCalories[i]);
                beratModel.setSelisihKalori(kalori);
                beratModel.setSelisihBerat(Math.round(kalori / 7716.18));
            }
        }
        databaseReference.child("User").child(GetUID).child(beratModel.getKey()).setValue(beratModel);
        workout = new ArrayList<>();

        Toast.makeText(getApplicationContext(), "You selected Achilles", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(this, Achilles.class);
        startActivity(myIntent);
    }

    public void onButtonClickCentaur(View view) {
        workout.add("Centaur");
        beratModel.setExercise(workout);
        for (int i = 0; i < sportsList.length; i++) {
            if (sportsList[i].equals("Centaur")) {
                double kalori = beratModel.getSelisihKalori() - Double.parseDouble(sportsCalories[i]);
                beratModel.setSelisihKalori(kalori);
                beratModel.setSelisihBerat(Math.round(kalori / 7716.18));
            }
        }
        databaseReference.child("User").child(GetUID).child(beratModel.getKey()).setValue(beratModel);
        workout = new ArrayList<>();

        Toast.makeText(getApplicationContext(), "You selected Centaur", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(this, Centaur.class);
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
