package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class customer_home extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);


        username = getIntent().getStringExtra(username);


        Button goshop = findViewById(R.id.goshop);
        goshop.setOnClickListener(v -> {
            Intent intent = new Intent(customer_home.this, Shop_list.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        Button gocart = findViewById(R.id.gocart);
        gocart.setOnClickListener(v -> {
            Intent intent = new Intent(customer_home.this, Checkout.class);
            intent.putExtra("username", "bob1234");
            startActivity(intent);
        });

        Button pastorders = findViewById(R.id.past_order);
        pastorders.setOnClickListener(v -> {
            Intent intent = new Intent(customer_home.this, past_orderlist.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        Button gologout = findViewById(R.id.logout);
        gologout.setOnClickListener(v -> {
            Intent intent = new Intent(customer_home.this, logged_out.class);
            startActivity(intent);
        });


    }


}