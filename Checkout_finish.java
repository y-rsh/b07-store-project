package com.example.b07storeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Checkout_finish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_finish);

        Button gohome = findViewById(R.id.ordered_gohome);
        gohome.setOnClickListener(v -> {
            Intent intent = new Intent(Checkout_finish.this, customer_home.class);
            startActivity(intent);
        });

        Button logout = findViewById(R.id.ordered_logout);
        logout.setOnClickListener(v -> {
            Intent intent = new Intent(Checkout_finish.this, logged_out.class);
            startActivity(intent);
        });

    }
}