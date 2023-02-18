package com.example.semestralproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Signatures extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signatures);

    }

    public void CiphersPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}