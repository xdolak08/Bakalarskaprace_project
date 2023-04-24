package com.example.semestralproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.Intents;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanOptions;

public class QRGenerator extends AppCompatActivity {

    ImageView QRdisplay;
    Button scanQR, generateQR;
    EditText QRvalue;

    public void Selected(View view)
    {
        String button_text;
        button_text =((Button)view).getText().toString();
        if(button_text.equals("SCAN"))
        {
            Intent ganesh = new Intent(this,Scanner.class);
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





    }


    private void generate() {
        String data = QRvalue.getText().toString();
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





    public void CiphersPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }







}
