package com.bagus.idealweight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResultActivity extends AppCompatActivity {

    TextView beratIdeal, selisihBerat, selisihKalori;

    Button btNext;

    String GetUID;

    FirebaseAuth auth;

    DatabaseReference databaseReference;

    public static Intent getActIntent(Activity activity) {
        //buat ambil intent
        return new Intent(activity, ResultActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        beratIdeal = (TextView) findViewById(R.id.TVIdeal);
        selisihBerat = (TextView) findViewById(R.id.TVSelisih);
        selisihKalori = (TextView) findViewById(R.id.TVKalori);

        btNext = (Button) findViewById(R.id.buttonNext);

        auth = FirebaseAuth.getInstance();
        GetUID = auth.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(GetUID);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String bIdeal = String.valueOf(data.child("beratIdeal").getValue());
                    String selisih = String.valueOf(data.child("selisihBerat").getValue());
                    String kalori = String.valueOf(data.child("selisihKalori").getValue());

                    beratIdeal.setText(bIdeal);
                    selisihBerat.setText(selisih);
                    selisihKalori.setText(kalori);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainMenu.getActIntent(ResultActivity.this));
            }
        });
    }

    public void onButtonClick(View view) {
        Intent myIntent = new Intent(this, MainMenu.class);
        startActivity(myIntent);
    }
}
