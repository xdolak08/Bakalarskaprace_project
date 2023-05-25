package com.example.semestralproject.QR;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.semestralproject.firebase.Login;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class Scanner extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scan();
    }

    public void QRPage(View view){
        Intent intent = new Intent(this, QR.class);
        startActivity(intent);
    }



    private void scan() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Tlačítkem pro zvýšení hlasitosti zapnete baterii.");
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

            Intent intent = new Intent(getApplicationContext(), Login.class);
            String rowData = result.getContents();
            intent.putExtra("Data", rowData);

            startActivity(intent);
            finish();

            /*if(klic.equals(klic))
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

             */


        }
    });
}
