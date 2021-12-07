package com.example.b07storeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class logged_out extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_out);


        Button gologin = findViewById(R.id.relogin);
        gologin.setOnClickListener(v -> {
            Intent intent = new Intent(logged_out.this, MainActivity.class);
            startActivity(intent);
        });

    }
}