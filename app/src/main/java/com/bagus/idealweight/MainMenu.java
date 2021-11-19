package com.bagus.idealweight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainMenu extends AppCompatActivity {

    TextView logout;
    private FirebaseAuth auth;

    public static Intent getActIntent(Activity activity) {
        //buat ambil intent
        return new Intent(activity, MainMenu.class);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        auth = FirebaseAuth.getInstance();


        logout = (TextView) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(MainMenu.this, LoginActivity.class));
            }
        });
    }

    public void onButtonClickExercise(View view) {
        Intent myIntent = new Intent(this, ExercisePlans.class);
        startActivity(myIntent);
    }

    public void onButtonClickResult(View view) {
        Intent myIntent = new Intent(this, ResultActivity.class);
        startActivity(myIntent);
    }

    public void onButtonClickProfile(View view){
        Intent myIntent = new Intent(this, UserActivity.class);
        startActivity(myIntent);
    }

    public void onButtonClickTrack(View view) {
        Intent myIntent = new Intent(this, TrackProgress.class);
        startActivity(myIntent);
    }


}

