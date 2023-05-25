package com.example.semestralproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.semestralproject.ProtocolApp.AppWithProtocol;

public class TypeOfApp extends AppCompatActivity implements View.OnClickListener {

    CardView firebaseapp;
    CardView appv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_app);

        firebaseapp = findViewById(R.id.fireBaseApp);
        appv2 = findViewById(R.id.protocolApp);


        firebaseapp.setOnClickListener(this);
        appv2.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.fireBaseApp: i = new Intent(this, MainActivity.class); startActivity(i); break;
            case R.id.protocolApp: i = new Intent(this, AppWithProtocol.class); startActivity(i);break;
        }
    }


}