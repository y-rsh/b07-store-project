package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Item_deleted extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_deleted);

        username = getIntent().getStringExtra("username");

        Button uptocart = findViewById(R.id.deletioncomplete_tocart);
        uptocart.setOnClickListener(v -> {
            Intent intent = new Intent(this, Checkout.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });
    }
}