package com.example.b07storeapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Mark_OrdersActivity extends AppCompatActivity {
    private TextView incomplete;
    Store store;
    String storename;
    List<Order> orders;
    DatabaseReference database;
    Button btn;

    public Mark_OrdersActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_orders);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        orders = new ArrayList<>();
        List<Order> test;
        //String name = store.storename;
        String nums = "4";
        incomplete = (TextView) findViewById(R.id.incomplete);
        incomplete.setText(nums);

        RecyclerView.Adapter mAdapter = new MarkAdapter(orders);
        recyclerView.setAdapter(mAdapter);

        //data read
        database.child(storename).child("data").child("orders")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            //if statement here
                           Order order = dataSnapshot.getValue(Order.class);
                          if (order.completed != "completed") {
                            orders.add(order);
                            mAdapter.notifyDataSetChanged();
                      }
              }
                        //mAdapter.notifyDataSetChanged();
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
        });

        store = (Store) getIntent().getSerializableExtra("storekey");
        String store_name = getIntent().getStringExtra("store_name");
    }
}