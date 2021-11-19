package com.bagus.idealweight;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bagus.idealweight.Model.BeratModel;
import com.bagus.idealweight.Model.Rules;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExercisePlans extends AppCompatActivity {

    private static final String LOG_TAG = ExercisePlans.class.getSimpleName();

    TextView easy, medium, hard;

    ArrayList<Rules> rulesArray;
    ArrayList<BeratModel> beratModelsArray;

    DatabaseReference databaseReference;

    FirebaseAuth auth;

    String GetUID;

    Rules rules;
    BeratModel beratModel;

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_plans);

        easy = findViewById(R.id.tvEasy);
        medium = findViewById(R.id.tvMedium);
        hard = findViewById(R.id.tvHard);

        auth = FirebaseAuth.getInstance();
        GetUID = auth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference();


        beratModelsArray = new ArrayList<>();
        rules = new Rules();
        beratModel = new BeratModel();
        databaseReference.child("User").child(GetUID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    beratModel = data.getValue(BeratModel.class);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Rules").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rulesArray = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Rules rules = data.getValue(Rules.class);
                    rulesArray.add(rules);
                }
                ruleBase();
                String durasiEasy = rulesArray.get(index).getDurasiEasy();
                String durasiMedium = rulesArray.get(index).getDurasiMedium();
                String durasiHard = rulesArray.get(index).getDurasiHard();

                easy.setText("Estimation time: " + durasiEasy + " days.");
                medium.setText("Estimation time: " + durasiMedium + " days.");
                hard.setText("Estimation time: " + durasiHard + " days.");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void onButtonClick(View view) {
        Intent myIntent = new Intent(this, EasyWorkout.class);
        startActivity(myIntent);

    }


    public void onButtonClickMedium(View view) {
        Intent myIntent = new Intent(this, MediumWorkout.class);
        startActivity(myIntent);
    }

    public void onButtonClickHard(View view) {
        Intent myIntent = new Intent(this, HardWorkout.class);
        startActivity(myIntent);
    }

    public void ruleBase() {
        for (int i = 0; i < rulesArray.size(); i++) {
            if ((Double.parseDouble(rulesArray.get(i).getBawah()) <= beratModel.getSelisihBerat()) && (Double.parseDouble(rulesArray.get(i).getAtas()) >= beratModel.getSelisihBerat())
                    && rulesArray.get(i).getGender().equals(beratModel.getGender())
                    && rulesArray.get(i).getUmur().equals(beratModel.getUsia())
                    && (rulesArray.get(i).getAktivitas().equals(beratModel.getAktivitas()))) {
                index = i;
            }
            Log.d(LOG_TAG, "jjjjj" + index);
        }
    }
}
