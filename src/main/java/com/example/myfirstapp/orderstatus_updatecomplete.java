package com.example.b07storeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class orderstatus_updatecomplete extends AppCompatActivity {

    String storename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderstatus_updatecomplete);

        storename = getIntent().getStringExtra("storename");

        Button tohome = findViewById(R.id.updatecomp_tohome);
        tohome.setOnClickListener(v -> {
            Intent intent = new Intent(this, owner_home.class);
            intent.putExtra("storename", storename);
            startActivity(intent);
        });
    }
}