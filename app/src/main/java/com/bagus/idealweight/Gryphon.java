package com.bagus.idealweight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Gryphon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gryphon);
    }

    public void onButtonClick(View view) {
        Intent myIntent = new Intent(getBaseContext(), MountainClimbersTimer.class);
        startActivity(myIntent);
    }
}
