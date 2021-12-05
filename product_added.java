package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class product_added extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_added);

        username = getIntent().getStringExtra("username");

        Button contshop = findViewById(R.id.shop_cont);
        contshop.setOnClickListener(v -> {
            Intent intent = new Intent(product_added.this, Shop_list.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        Button gocheckout = findViewById(R.id.to_checkout);
        gocheckout.setOnClickListener(v -> {
            Intent intent = new Intent(product_added.this, Checkout.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });
    }
}