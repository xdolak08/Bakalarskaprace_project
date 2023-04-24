package com.example.semestralproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class Scanner extends AppCompatActivity {

    String klic = "ahoj";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scan();
    }

    public void QRPage(View view){
        Intent intent = new Intent(this, QRGenerator.class);
        startActivity(intent);
    }

    private void scan() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Zvyšte hlasitost pro zapnutí baterie.");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result ->
    {
        if(result.getContents() != null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(Scanner.this);
            builder.setTitle("Výsledek scannování: " + result.getContents());
            builder.setMessage("\nKlíč je: " + klic);
            String vysledek = result.getContents();
            System.out.println(vysledek);
            if(klic.equals(vysledek))
            {
                builder.setPositiveButton("Potvrzuji, klíče jsou stejné.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }
            else {
                builder.setNegativeButton("Odmítám, klíče nejsou stejné.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }

        }
    });
}
