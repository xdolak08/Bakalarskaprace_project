package com.example.semestralproject.QR;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.semestralproject.CryptoPrimitives.CryptoPrimitives;
import com.example.semestralproject.MainActivity;
import com.example.semestralproject.R;
import com.example.semestralproject.firebase.Login;
import com.example.semestralproject.firebase.LoginLogin;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QR extends AppCompatActivity {

    ImageView QRdisplay;
    Button scanQR, generateQR;
    EditText QRvalue;
    BottomNavigationView bottomNavigationView;


    public void Selected(View view)
    {
        String button_text;
        button_text =((Button)view).getText().toString();
        if(button_text.equals("SCAN"))
        {
            Intent ganesh = new Intent(this, Scanner.class);
            startActivity(ganesh);

        }
        else if (button_text.equals("GENERATE"))
        {
            generate();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerator);

        QRdisplay = findViewById(R.id.QRimage);
        scanQR = findViewById(R.id.scanButton);
        generateQR = findViewById(R.id.generateButton);
        QRvalue = findViewById(R.id.QRvalue);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId()){
                    case R.id.home:
                        i = new Intent(QR.this, MainActivity.class);
                        startActivity(i);
                        break;

                    case R.id.crypto:
                        i = new Intent(QR.this, CryptoPrimitives.class);
                        startActivity(i);
                        break;

                    case R.id.qr:
                        Toast.makeText(QR.this, "Jste zde.", Toast.LENGTH_SHORT).show();
                        break;


                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                Intent intent = new Intent(QR.this, LoginLogin.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void generate() {


        String data = QRvalue.getText().toString();

        if(!TextUtils.isEmpty(data))
        {
            MultiFormatWriter writer = new MultiFormatWriter();
            try {
                BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, 600, 600);
                BarcodeEncoder encoder = new BarcodeEncoder();
                Bitmap bitmap = encoder.createBitmap(matrix);
                QRdisplay.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(this, "Vložte data.", Toast.LENGTH_SHORT).show();
            return;
        }

    }





    public void CiphersPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }







}
