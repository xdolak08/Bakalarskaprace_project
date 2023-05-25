package com.example.semestralproject.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.semestralproject.MainActivity;
import com.example.semestralproject.R;
import com.example.semestralproject.QR.Scanner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword;
    Button buttonLogin;
    FirebaseAuth mAuth;
    TextView textView;





    @Override
    public void onStart() {
        super.onStart();
       FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), FireBasePage.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.button_login);
        textView = findViewById(R.id.registerNow);

        String email = " ", password = " ";

        try {
            Intent intent = getIntent();
            String data = intent.getStringExtra("Data");
            String[] rowData = data.split(" ");

            email = rowData[0];
            editTextEmail.setText(email);
            password = rowData[1];
            editTextPassword.setText(password);
        }
        catch (Exception e) {
            Toast.makeText(Login.this, "Vyplnit údaje!", Toast.LENGTH_SHORT).show();
        }





        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalEmail = editTextEmail.getText().toString();
                String finalPassword = editTextPassword.getText().toString();

                if(TextUtils.isEmpty(finalEmail))
                {
                    Toast.makeText(Login.this, "Vložte email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(finalPassword))
                {
                    Toast.makeText(Login.this, "Vložte heslo!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(finalEmail, finalPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Přihlášení bylo úspěšné.", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), FireBasePage.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "Ověření selhalo.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    public void CiphersPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}