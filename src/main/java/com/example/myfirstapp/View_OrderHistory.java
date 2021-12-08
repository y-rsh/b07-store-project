package com.example.b07storeapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class View_OrderHistory extends AppCompatActivity {
    private TextView Order_id;
    private TextView Order_completed;
    private TextView Order_items;
    private TextView Order_time;
    private TextView Order_price;
    Store store;
    final String storename = "storename_1";
    List<Order> orders;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_history);

        store = (Store) getIntent().getSerializableExtra("storekey");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance().getReference("StoreList");

        orders = new ArrayList<>();
        //test things since cannot connect to firebase

        RecyclerView.Adapter mAdapter = new MainAdapter(orders);
        recyclerView.setAdapter(mAdapter);


        //data read
        database.child(store.storename).child("data").child("orders")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            Order order = dataSnapshot.getValue(Order.class);
                            orders.add(order);
                        }
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}