package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Cart_itemconfigure extends AppCompatActivity {

    private String username;
    private String productname;
    private String quantity;
    private String updatequantity;
    public String olditem_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_itemconfigure);

        username = getIntent().getStringExtra("username");
        productname = getIntent().getStringExtra("productname");
        String unitprice = getIntent().getStringExtra("unitprice");
        quantity = getIntent().getStringExtra("quantity");

        TextView productnameview = findViewById(R.id.config_productname);
        productnameview.setText(productname);
        TextView unitpriceview = findViewById(R.id.config_unitprice);
        unitpriceview.setText(unitprice);
        TextView quantityview = findViewById(R.id.config_quantity);
        quantityview.setText(quantity);

        Spinner spinner = findViewById(R.id.quantityconfig_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                updatequantity = (String)spinner.getSelectedItem();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updatequantity = quantity;
            }
        });

        Button quantityupdate = findViewById(R.id.update_quantity);
        quantityupdate.setOnClickListener(v -> {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference subref = reference.child("Users").child("Customers").child(username).child("Cart").child("Items");
            subref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    subref.child(productname).child("quantity").setValue(updatequantity);
                    Intent intent = new Intent(Cart_itemconfigure.this, Cart_quantity_updated.class);
                    intent.putExtra("username", username);
                    startActivity(intent);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        });

        Button deletion = findViewById(R.id.confirming_deletion);
        deletion.setOnClickListener(v -> {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference subref = reference.child("Users").child("Customers").child(username).child("Cart");

            subref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    subref.child("Items").child(productname).removeValue();
                    olditem_num = (String)snapshot.child("Number of items").getValue();
                    assert olditem_num != null;
                    int item_num_toint = Integer.parseInt(olditem_num);
                    String new_item_num = String.valueOf(item_num_toint - 1);
                    subref.child("Number of items").setValue(new_item_num);
                    if ((item_num_toint - 1) == 0) {
                        subref.removeValue();
                    }
                    Intent intent = new Intent(Cart_itemconfigure.this, Item_deleted.class);
                    intent.putExtra("username", username);
                    startActivity(intent);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });





        });





        Button backtocart = findViewById(R.id.itemconfig_backto_cart);
        backtocart.setOnClickListener(v -> {
            Intent intent = new Intent(this, Checkout.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

    }
}