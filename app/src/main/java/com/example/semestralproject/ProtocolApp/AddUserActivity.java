package com.example.semestralproject.ProtocolApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.semestralproject.R;

public class AddUserActivity extends AppCompatActivity {

    EditText editTextAT_U;
    EditText editText_username;
    Button addUserbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        editText_username = findViewById(R.id.editTextUserName);
        editTextAT_U = findViewById(R.id.editTextAT_U);
        addUserbutton = findViewById(R.id.buttonAddUser);
        addUserbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddUserActivity.this);
                myDB.addUser(editTextAT_U.getText().toString().trim(),
                editText_username.getText().toString().trim());
            }
        });
    }
}