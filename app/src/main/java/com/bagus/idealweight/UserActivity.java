package com.bagus.idealweight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bagus.idealweight.Model.BeratModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserActivity extends AppCompatActivity {

    public static Intent getActIntent(Activity activity) {
        //buat ambil intent
        return new Intent(activity, UserActivity.class);
    }

    TextView nama, email, telp;
    Button edit, delete;

    BeratModel beratModel;

    String GetUID, key;

    FirebaseAuth auth;

    FirebaseUser user;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        nama = (TextView) findViewById(R.id.tvNama);
        email = (TextView) findViewById(R.id.tvEmail);
        telp = (TextView) findViewById(R.id.tvTelp);

        edit = (Button) findViewById(R.id.editprofil);
        delete = (Button) findViewById(R.id.deleteaccount);

        auth = FirebaseAuth.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();

        GetUID = auth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        beratModel = new BeratModel();

        databaseReference.child("User").child(GetUID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    beratModel = data.getValue(BeratModel.class);
                    beratModel.setKey(data.getKey());

                    String snama = String.valueOf(data.child("namaUser").getValue());
                    String semail = String.valueOf(data.child("email").getValue());
                    String stelp = String.valueOf(data.child("telp").getValue());

                    nama.setText(snama);
                    email.setText(semail);
                    telp.setText(stelp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onClickEditProfile(View view) {
        Intent myIntent = new Intent(this, EditProfilActivity.class);
        startActivity(myIntent);
    }

    public void onClickDeleteAccount(View view){
        user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                databaseReference.child("User").child(GetUID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()){
                            data.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(getApplicationContext(), "Account successfully deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserActivity.this, LoginActivity.class));
            }
        });
    }
}
