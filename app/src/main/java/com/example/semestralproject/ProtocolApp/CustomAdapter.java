package com.example.semestralproject.ProtocolApp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semestralproject.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList id, username, AT_U;
    int position;

    CustomAdapter(Context context, ArrayList id, ArrayList username, ArrayList AT_U){
        this.context = context;
        this.id = id;
        this.username = username;
        this.AT_U = AT_U;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position = position;
        holder.textViewID.setText(String.valueOf(id.get(position)));
        holder.textViewUserName.setText(String.valueOf(username.get(position)));
        holder.textViewAT_U.setText(String.valueOf(AT_U.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DeleteElement.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUserName, textViewAT_U, textViewID;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(R.id.textview_user_id);
            textViewUserName = itemView.findViewById(R.id.textView_username);
            textViewAT_U = itemView.findViewById(R.id.textView_AT_U);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }




    }


}
