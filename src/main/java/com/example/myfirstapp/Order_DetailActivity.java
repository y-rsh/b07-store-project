package com.example.b07storeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Order_DetailActivity extends AppCompatActivity {
    private Store store;
    private List<Order> orders;
    private TextView Order_id;
    private TextView Order_completed;
    private TextView Order_items;
    private TextView Order_price;
    private String order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Intent intent = getIntent();
        Order order = (Order) intent.getSerializableExtra("orderkey");

        String item_list ="Item Bought: ";
        for(int i = 0; i < order.Order_items.length; i++){
            item_list += order.Order_items[i] + " ";
        }
        String id = "Order ID: " + order.Order_id;
        String price = "Total Price: " + order.totalprice;
        String status = "Order Status: " + order.completed;

        Order_id = (TextView) findViewById(R.id.view_order_id);
        Order_completed = (TextView) findViewById(R.id.view_order_completed);
        Order_items = (TextView) findViewById(R.id.view_order_items);
        Order_price = (TextView) findViewById(R.id.view_order_totalprice);

        Order_id.setText(id);
        Order_completed.setText(status);
        Order_items.setText(item_list);
        Order_price.setText(price);


    }
}