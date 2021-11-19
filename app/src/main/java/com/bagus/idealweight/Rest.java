package com.bagus.idealweight;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Rest extends AppCompatActivity {
    public TextView timerValue1;


    public Button btnNext;
    public int[] layouts;

    public static final long START_TIME_IN_MILLIS = 60000;
    public CountDownTimer countDownTimer;
    public boolean mTimerRunning;
    public long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);


        btnNext = (Button) findViewById(R.id.btn_next);
        timerValue1 = (TextView) findViewById(R.id.timerValue1);

        if (timerValue1 == null) Log.d("~", "Choice1 is null");

        startTimer();


    }



    private void launchHomeScreen() {
//        prefManager.setFirstTimeLaunch(false);
        Intent myIntent = new Intent(getBaseContext(), Hephaestus.class);
        startActivity(myIntent);
//        finish();
    }


    private void startTimer(){
        countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDowntText();
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Congratulations!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(getBaseContext(), EasyWorkout.class);
                startActivity(myIntent);
            }
        }.start();
        mTimerRunning = true;
    }

    private void updateCountDowntText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerValue1.setText(timeLeft);
    }

    public void onButtonClickNext(View view) {
        Intent myIntent = new Intent(getBaseContext(), EasyWorkout.class);
        startActivity(myIntent);
    }
}
