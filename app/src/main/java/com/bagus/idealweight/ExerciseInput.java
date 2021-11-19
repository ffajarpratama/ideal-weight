package com.bagus.idealweight;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bagus.idealweight.Model.Exercise;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExerciseInput extends AppCompatActivity {

    EditText easy1;
    EditText easy2;
    EditText easy3;
    EditText easy4;
    EditText med1;
    EditText med2;
    EditText med3;
    EditText med4;
    EditText hard1;
    EditText hard2;
    EditText hard3;
    EditText hard4;

    Button submit;

    Exercise exercise;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_input);

        easy1 = findViewById(R.id.Hephaestus);
        easy2 = findViewById(R.id.Gryphon);
        easy3 = findViewById(R.id.Achilles);
        easy4 = findViewById(R.id.Centaur);

        med1 = findViewById(R.id.Minotaur);
        med2 = findViewById(R.id.Pegasus);
        med3 = findViewById(R.id.Medusa);
        med4 = findViewById(R.id.Phoenix);

        hard1 = findViewById(R.id.Zeus);
        hard2 = findViewById(R.id.Poseidon);
        hard3 = findViewById(R.id.Apollo);
        hard4 = findViewById(R.id.Perseus);

        submit = findViewById(R.id.submit);

        exercise = new Exercise();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Exercise");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(easy1.getText().toString()) &&
                        !isEmpty(easy2.getText().toString()) &&
                        !isEmpty(easy3.getText().toString()) &&
                        !isEmpty(easy4.getText().toString()) &&
                        !isEmpty(med1.getText().toString()) &&
                        !isEmpty(med2.getText().toString()) &&
                        !isEmpty(med3.getText().toString()) &&
                        !isEmpty(med4.getText().toString()) &&
                        !isEmpty(hard1.getText().toString()) &&
                        !isEmpty(hard2.getText().toString()) &&
                        !isEmpty(hard3.getText().toString()) &&
                        !isEmpty(hard4.getText().toString())) {

                    exercise.setHephaestus(easy1.getText().toString());
                    exercise.setGryphon(easy2.getText().toString());
                    exercise.setAchilles(easy3.getText().toString());
                    exercise.setCentaur(easy4.getText().toString());
                    exercise.setMinotaur(med1.getText().toString());
                    exercise.setPegasus(med2.getText().toString());
                    exercise.setMedusa(med3.getText().toString());
                    exercise.setPhoenix(med4.getText().toString());
                    exercise.setZeus(hard1.getText().toString());
                    exercise.setPoseidon(hard2.getText().toString());
                    exercise.setApollo(hard3.getText().toString());
                    exercise.setPerseus(hard4.getText().toString());

                    successListener();
                }
            }
        });
    }

    private boolean isEmpty(String s) {
        //cek apa ada field yang kosong sebelum submit
        return TextUtils.isEmpty(s);
    }

    private void successListener() {
        databaseReference.push().setValue(exercise).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
