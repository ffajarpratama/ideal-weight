package com.bagus.idealweight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Hephaestus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hephaestus);
    }

    public void onButtonClickStart(View view) {
        Intent myIntent = new Intent(getBaseContext(), HephaestusTimer.class);
        startActivity(myIntent);
    }
}
