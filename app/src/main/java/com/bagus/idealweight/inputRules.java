package com.bagus.idealweight;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bagus.idealweight.Model.Rules;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class inputRules extends AppCompatActivity {

    EditText aktivitas, umur, gender, atas, bawah, easy, medium, hard;
    Button submit;

    Rules rules;

    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_rules);

        aktivitas = findViewById(R.id.aktivitas);
        umur = findViewById(R.id.umur);
        gender = findViewById(R.id.gender);
        atas = findViewById(R.id.atas);
        bawah = findViewById(R.id.bawah);
        easy = findViewById(R.id.easy);
        medium = findViewById(R.id.medium);
        hard = findViewById(R.id.hard);
        submit = findViewById(R.id.submit);

        rules = new Rules();

        database = FirebaseDatabase.getInstance().getReference().child("Rules");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(aktivitas.getText().toString()) &&
                        !isEmpty(gender.getText().toString()) &&
                        !isEmpty(umur.getText().toString()) &&
                        !isEmpty(bawah.getText().toString()) &&
                        !isEmpty(atas.getText().toString()) &&
                        !isEmpty(easy.getText().toString()) &&
                        !isEmpty(medium.getText().toString()) &&
                        !isEmpty(hard.getText().toString()))
                {
                    rules.setAktivitas(aktivitas.getText().toString());
                    rules.setGender(gender.getText().toString());
                    rules.setUmur(umur.getText().toString());
                    rules.setBawah(bawah.getText().toString());
                    rules.setAtas(atas.getText().toString());
                    rules.setDurasiEasy(easy.getText().toString());
                    rules.setDurasiMedium(medium.getText().toString());
                    rules.setDurasiHard(hard.getText().toString());

                    successListener();
                }
            }
        });
    }

    private boolean isEmpty(String s) {
        //cek apa ada field yang kosong sebelum submit
        return TextUtils.isEmpty(s);
    }

    private void successListener(){
        database.push().setValue(rules).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                aktivitas.setText("");
                gender.setText("");
                umur.setText("");
                atas.setText("");
                bawah.setText("");
                easy.setText("");
                medium.setText("");
                hard.setText("");

                Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
