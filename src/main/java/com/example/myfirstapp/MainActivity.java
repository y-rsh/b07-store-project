package com.example.b07storeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btn = (ImageButton)findViewById(R.id.imageButton);
        btn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, customer_login.class)));

        ImageButton btn1 = (ImageButton)findViewById(R.id.imageButton2);
        btn1.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, owner_login.class)));

        Button btn2 = (Button)findViewById(R.id.CustomerButton);
        btn2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, customer_login.class)));

        Button btn3 = (Button)findViewById(R.id.OwnerButton);
        btn3.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, owner_login.class)));


    }
}