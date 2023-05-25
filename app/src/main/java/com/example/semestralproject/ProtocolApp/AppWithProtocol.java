package com.example.semestralproject.ProtocolApp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.semestralproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AppWithProtocol extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> id, username, AT_U, K_U;
    CustomAdapter customAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        recyclerView = findViewById(R.id.recyclerView);



        add_button = findViewById(R.id.addUser_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppWithProtocol.this, AddUserActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(AppWithProtocol.this);
        id = new ArrayList<>();
        username = new ArrayList<>();
        AT_U = new ArrayList<>();
        K_U = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(AppWithProtocol.this, id, username, AT_U);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AppWithProtocol.this));

    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                username.add(cursor.getString(1));
                AT_U.add(cursor.getString(2));
            }
        }
    }


}