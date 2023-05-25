package com.example.semestralproject.ProtocolApp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.semestralproject.R;

public class DeleteElement extends AppCompatActivity {

    String id;
    Button buttonDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_element);
        id = getIntent().getStringExtra("id");
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DeleteElement.this, "Delete", Toast.LENGTH_SHORT).show();
                confirmDialog();
            }
        });

    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + id + " ?");
        builder.setMessage("Are you sure you want to delete " + id + " ?" );
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(DeleteElement.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.create().show();
    }


}