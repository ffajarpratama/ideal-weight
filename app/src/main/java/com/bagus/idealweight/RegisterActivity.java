package com.bagus.idealweight;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bagus.idealweight.Model.BeratModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;
    BeratModel beratModel;
    String GetUID;
    DatabaseReference databaseReference;
    private EditText edtEmail, edtPassword, etNama, etTelp;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = findViewById(R.id.edt_email_register);
        edtPassword = findViewById(R.id.edt_password_register);
        etNama = findViewById(R.id.edt_nama);
        etTelp = findViewById(R.id.edt_nomor);

        btnRegister = findViewById(R.id.btn_sign_up);

        auth = FirebaseAuth.getInstance();

        beratModel = new BeratModel();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        registerUser();
    }

    private void registerUser() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //menampung imputan user
                final String emailUser = edtEmail.getText().toString().trim();
                String passwordUser = edtPassword.getText().toString().trim();

                //validasi email dan password
                // jika email kosong
                if (emailUser.isEmpty()) {
                    edtEmail.setError("Email tidak boleh kosong");
                }
                // jika email not valid
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()) {
                    edtEmail.setError("Email tidak valid");
                }
                // jika password kosong
                else if (passwordUser.isEmpty()) {
                    edtPassword.setError("Password tidak boleh kosong");
                }
                //jika password kurang dari 6 karakter
                else if (passwordUser.length() < 6) {
                    edtPassword.setError("Password minimal terdiri dari 6 karakter");
                } else {
                    //create user dengan firebase auth
                    btnRegister.setVisibility(View.INVISIBLE);
                    auth.createUserWithEmailAndPassword(emailUser, passwordUser).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //jika gagal register do something
                            if (!task.isSuccessful()) {
                                btnRegister.setVisibility(View.VISIBLE);
                                Toast.makeText(RegisterActivity.this, "Register gagal karena " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            } else {
                                //INPUT DATA TO DB
                                beratModel.setEmail(emailUser);
                                beratModel.setNamaUser(etNama.getText().toString());
                                beratModel.setTelp(etTelp.getText().toString());


                                databaseReference.child("User").child(auth.getCurrentUser().getUid()).push().setValue(beratModel);
                                //END INPUT DATA TO DB

                                //jika sukses akan menuju ke login activity
                                startActivity(new Intent(RegisterActivity.this, HomePage.class));
                            }
                        }
                    });
                }
            }
        });
    }
}
