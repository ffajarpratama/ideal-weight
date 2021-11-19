package com.bagus.idealweight;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bagus.idealweight.Model.BeratModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfilActivity extends AppCompatActivity {

    EditText nama, telp;

    Button edit;

    DatabaseReference databaseReference;

    FirebaseAuth auth;

    String GetUID, cekNama, cekTelp;

    BeratModel beratModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        nama = findViewById(R.id.edt_nama);
        telp = findViewById(R.id.edt_nomor);

        edit = findViewById(R.id.btn_edit);

        beratModel = new BeratModel();

        auth = FirebaseAuth.getInstance();

        GetUID = auth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference();

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

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekNama = nama.getText().toString();
                cekTelp = telp.getText().toString();

                if (isEmpty(cekNama) || isEmpty(cekTelp)) {
                    Toast.makeText(getApplicationContext(), "Please fill your data completely", Toast.LENGTH_SHORT).show();
                } else {
                    beratModel.setNamaUser(nama.getText().toString());
                    beratModel.setTelp(telp.getText().toString());

                    updateProfile();
                }
            }
        });
    }

    private boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }

    public void onClickCancel(View view) {
        Intent myIntent = new Intent(this, UserActivity.class);
        startActivity(myIntent);
    }

    private void updateProfile() {
        databaseReference.child("User").child(GetUID).child(beratModel.getKey()).setValue(beratModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Profile successfully changed", Toast.LENGTH_SHORT).show();
                startActivity(UserActivity.getActIntent(EditProfilActivity.this));
            }
        });
    }
}
