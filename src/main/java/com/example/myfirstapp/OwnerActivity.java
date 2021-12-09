package com.example.b07storeapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;


public class OwnerActivity extends AppCompatActivity {
    private Store store;
    private String store_name;
    DatabaseReference database;
    private String user;
    private ArrayList<Store> list;
    private TextView tvName;
    private TextView tvStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
        list = new ArrayList<>();

        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        Log.d("retrieved username", user);
        database = FirebaseDatabase.getInstance().getReference("StoreList");

        tvName = (TextView) findViewById(R.id.Owner_acticity_onerName) ;
        tvStore = (TextView) findViewById(R.id.welcom_storename);
        tvName.setText(user);

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Store store_temp = dataSnapshot.getValue(Store.class);
                    assert store_temp != null;
                    if (store_temp.storename == user){
                        store = store_temp;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        tvStore.setText(store.storename);

    }

    public void view_order(View view) {
        Intent intent = new Intent(OwnerActivity.this, View_OrderHistory.class);
        intent.putExtra("storekey", (Serializable) store);
        intent.putExtra("store_name_test", store.storename);
        startActivity(intent);
    }

    public void mark_orders(View view) {
        Intent intent = new Intent(OwnerActivity.this, Mark_OrdersActivity.class);
        intent.putExtra("storekey", (Serializable) store);
        intent.putExtra("store_name", store.storename);
        startActivity(intent);
    }}