package com.example.b07storeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class owner_home extends AppCompatActivity {

    String storename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);

        storename = getIntent().getStringExtra("storename");

        Button edit_products = findViewById(R.id.goupdate_products);
        edit_products.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditItems.class);
            intent.putExtra("storename", storename);
            startActivity(intent);
        });


        Button goorderupdates = findViewById(R.id.update_orderstats);
        goorderupdates.setOnClickListener(v -> {
            Intent intent = new Intent(this, update_order_status.class);
            intent.putExtra("storename", storename);
            startActivity(intent);
        });

        Button goallpastorders = findViewById(R.id.view_allpastorder);
        goallpastorders.setOnClickListener(v -> {
            Intent intent = new Intent(this, order_full_list.class);
            intent.putExtra("storename", storename);
            startActivity(intent);
        });

    }
}