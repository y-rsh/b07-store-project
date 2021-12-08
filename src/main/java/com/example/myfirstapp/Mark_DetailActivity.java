package com.example.b07storeapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Mark_DetailActivity extends AppCompatActivity {
    private Store store;
    private String storename;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_detail);

    }
    public void completed(View view){
        Intent intent = getIntent();
        storename = (String) intent.getStringExtra("store_name");
        database = FirebaseDatabase.getInstance().getReference("StoreList");
        Order order = (Order) intent.getSerializableExtra("orderchosen");
        AlertDialog.Builder builder = new AlertDialog.Builder(Mark_DetailActivity.this);
        builder.setTitle("Mark Orders As Completed");
        builder.setMessage("Are you sure to mark the order(s) completed?");
        builder.setPositiveButton("Mark", (dialog, which) -> {
            Toast.makeText(Mark_DetailActivity.this, "Orders Marked", Toast.LENGTH_LONG).show();
            database.child(storename).child("data").child("orders")
                    .child(order.Order_id).child("status").setValue("completed");
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
    }
}