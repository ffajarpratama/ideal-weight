package com.bagus.idealweight;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bagus.idealweight.Model.BeratModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class HomePage extends AppCompatActivity {
    TextInputEditText ETberat, ETtinggi;

    TextView beratIdeal, selisihBerat, selisihKalori;

    boolean compute;
    double inputBerat, inputTinggi, computeIdeal, computeSelisih, computeKalori;

    Spinner spinnerGender, spinnerUsia, spinnerActivity;

    MaterialButton Bcalculate;

    String gender, usia, aktivitas, GetUID, emailUser;

    FirebaseAuth auth;

    DatabaseReference databaseReference;

    BeratModel beratModel;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        spinnerUsia = findViewById(R.id.spinnerUsia);
        spinnerActivity = findViewById(R.id.spinnerActivity);

        beratIdeal = (TextView) findViewById(R.id.idealWeight);
        selisihBerat = (TextView) findViewById(R.id.selisihBerat);
        selisihKalori = (TextView) findViewById(R.id.selisihKalori);

        ETtinggi = (TextInputEditText) findViewById(R.id.tinggi);
        ETberat = (TextInputEditText) findViewById(R.id.berat);

        Bcalculate = (MaterialButton) findViewById(R.id.calculate);

        //DEKLARASI FIREBASE AUTH
        auth = FirebaseAuth.getInstance();
        GetUID = auth.getUid();
        //END OF FIREBASE AUTH DECLARATION

        //VARIABEL MODEL
        databaseReference = FirebaseDatabase.getInstance().getReference();

        beratModel = new BeratModel();

        databaseReference.child("User").child(GetUID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    beratModel = data.getValue(BeratModel.class);
                    beratModel.setKey(data.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //DATABASE REFERENCE


        //HANDLER SPINNER GENDER
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), gender, Toast.LENGTH_SHORT).show();
                        DecimalFormat format = new DecimalFormat("###,###");
                        if (ETberat.getText().length() != 0 && ETtinggi.getText().length() != 0) {
                            inputBerat = Float.parseFloat(ETberat.getText() + " ");
                            inputTinggi = Float.parseFloat(ETtinggi.getText() + " ");
                            compute = true;
                        }

                        if (compute) {
                            computeIdeal = (inputTinggi - 100) - (10 % (inputTinggi - 100));
                            computeSelisih = (inputBerat - computeIdeal);

                            //CONDITION IF WEIGHT IS IDEAL/UNDERWEIGHT
                            if (computeSelisih <= 0){
                                computeSelisih = 0;
                            }
                            //END OF CONDITION

                            computeKalori = Math.round(computeSelisih * 7716.18);

                            String formatKalori = Double.toString(computeKalori);

                            String saveIdeal = Double.toString(computeIdeal);
                            beratIdeal.setText(saveIdeal);

                            String saveSelisih = Double.toString(computeSelisih);
                            selisihBerat.setText(saveSelisih);
//                            String saveKalori = Double.toString(computeKalori);
                            selisihKalori.setText(formatKalori);

                            compute = false;
                        }
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), gender, Toast.LENGTH_SHORT).show();
                        if (ETberat.getText().length() != 0 && ETtinggi.getText().length() != 0) {
                            inputBerat = Float.parseFloat(ETberat.getText() + " ");
                            inputTinggi = Float.parseFloat(ETtinggi.getText() + " ");
                            compute = true;
                        }

                        if (compute) {
                            computeIdeal = (inputTinggi - 100) - (15 % (inputTinggi - 100));
                            computeSelisih = (inputBerat - computeIdeal);

                            //CONDITION IF WEIGHT IS IDEAL/UNDERWEIGHT
                            if (computeSelisih <= 0){
                                computeSelisih = 0;
                            }
                            //END OF CONDITION

                            computeKalori = Math.round(computeSelisih * 7716.18);

                            //for decimalFormat


                            String formatKalori = Double.toString(computeKalori);

                            String saveIdeal = Double.toString(computeIdeal);
                            beratIdeal.setText(saveIdeal);

                            String saveSelisih = Double.toString(computeSelisih);
                            selisihBerat.setText(saveSelisih);
//                            String saveKalori = Double.toString(computeKalori);
                            selisihKalori.setText(formatKalori);

                            compute = false;
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //END OF SPINNER GENDER

        //HANDLER SPINNER USIA
        spinnerUsia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                usia = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //END OF SPINNER USIA

        //HANDLER OF SPINNER ACTIVITY
        spinnerActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aktivitas = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //END OF SPINNER ACTIVITY


        Bcalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isEmpty(ETberat.getText().toString()) && !isEmpty(ETtinggi.getText().toString())) {

                    beratModel.setTinggi(ETtinggi.getText().toString());
                    beratModel.setBerat(ETberat.getText().toString());
                    beratModel.setBeratIdeal(beratIdeal.getText().toString());
                    beratModel.setSelisihBerat(Double.parseDouble(selisihBerat.getText().toString()));
                    beratModel.setSelisihKalori(Double.parseDouble(selisihKalori.getText().toString()));
                    beratModel.setGender(gender);
                    beratModel.setUsia(usia);
                    beratModel.setAktivitas(aktivitas);

                    successListener();

                    startActivity(ResultActivity.getActIntent(HomePage.this));
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill your data", Toast.LENGTH_SHORT).show();
                }

//                databaseReference.push().setValue(beratModel);
//                Toast.makeText(HomePage.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isEmpty(String s) {
        //cek apa ada field yang kosong sebelum submit
        return TextUtils.isEmpty(s);
    }

    private void successListener() {
        databaseReference.child("User").child(GetUID).child(beratModel.getKey()).setValue(beratModel).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                ETberat.setText("");
                ETtinggi.setText("");
                Toast.makeText(HomePage.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
