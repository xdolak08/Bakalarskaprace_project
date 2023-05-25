package com.example.semestralproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.view.MenuItem;

import android.view.View;
import android.widget.Toast;

import com.example.semestralproject.CryptoPrimitives.CryptoPrimitives;
import com.example.semestralproject.ProtocolApp.AppWithProtocol;
import com.example.semestralproject.QR.QR;
import com.example.semestralproject.firebase.FireBasePage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.lang.reflect.Type;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    BottomNavigationView bottomNavigationViewFireBaseApp;
    BottomNavigationView bottomNavigationViewProtocolApp;

    CardView firebaseApp;
    CardView protocolApp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_app);

        firebaseApp = findViewById(R.id.fireBaseApp);
        protocolApp = findViewById(R.id.protocolApp);

        firebaseApp.setOnClickListener(this);
        protocolApp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fireBaseApp:
                setContentView(R.layout.activity_main_firebaseapp);


                bottomNavigationViewFireBaseApp = findViewById(R.id.bottomNavigationView);


                bottomNavigationViewFireBaseApp.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent i;
                        switch (item.getItemId()){
                            case R.id.home:
                                i = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(i);
                                break;
                            case R.id.crypto:
                                i = new Intent(MainActivity.this, CryptoPrimitives.class);
                                startActivity(i);
                                break;
                            case R.id.qr:
                                i = new Intent(MainActivity.this, QR.class);
                                startActivity(i);
                                break;
                        }
                        return false;
                    }
                });
                break;

            case R.id.protocolApp:
                setContentView(R.layout.activity_main_protocolapp);

                bottomNavigationViewProtocolApp = findViewById(R.id.bottomNavigationViewProtocolApp);


                bottomNavigationViewProtocolApp.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent i;
                        switch (item.getItemId()){

                            case R.id.home:
                                i = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(i);
                                break;
                            case R.id.server:
                                Toast.makeText(MainActivity.this, "Zde bude stránka se serverem.", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.addUser:
                                i = new Intent(MainActivity.this, AppWithProtocol.class);
                                startActivity(i);
                                break;
                        }
                        return false;
                    }
                });
                break;

        }
    }


}
