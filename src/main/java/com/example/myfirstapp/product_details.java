package com.example.b07storeapp;

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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class product_details extends AppCompatActivity {

    private String shopname, productname;
    private String brandname, weight, price;
    private String selectedquantity;
    private String username;

    private TextView brand_view, productname_view, weight_view, price_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        username = getIntent().getStringExtra("username");
        shopname = getIntent().getStringExtra("clicked_shop");
        productname = getIntent().getStringExtra("clicked_product");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference productref = reference.child("StoreList").child(shopname).child("data").child("items").child(productname);

        productref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                brandname = (String)snapshot.child("brand").getValue();
                weight = (String)snapshot.child("weight").getValue();
                price = (String)snapshot.child("price").getValue();

                brand_view = findViewById(R.id.Brand_Name);
                productname_view = findViewById(R.id.Product_Name);
                weight_view = findViewById(R.id.Weight_Num);
                price_view = findViewById(R.id.Price_Num);

                brand_view.setText(brandname);
                productname_view.setText(productname);
                weight_view.setText(weight);
                price_view.setText(price);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Spinner spinner = findViewById(R.id.productnum_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                selectedquantity = (String)spinner.getSelectedItem();;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Button add_cart = findViewById(R.id.add_to_cart);
        add_cart.setOnClickListener(v -> {
            DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference();
            DatabaseReference subref2 = ref2.child("Users").child("Customers").child(username);
            subref2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(!snapshot.child("Cart").exists()){
                        subref2.child("Cart").child("Storename").setValue(shopname);
                        subref2.child("Cart").child("Number of items").setValue("1");
                        subref2.child("Cart").child("Items").child(productname).child("name").setValue(productname);
                        subref2.child("Cart").child("Items").child(productname).child("unit price").setValue(price);
                        subref2.child("Cart").child("Items").child(productname).child("quantity").setValue(selectedquantity);
                        Intent intent = new Intent(product_details.this, product_added.class);
                        intent.putExtra("username", username);
                        startActivity(intent);

                    }
                    else{
                        if(!shopname.equals((String) snapshot.child("Cart").child("Storename").getValue())){
                            //make popup saying only items from same shop can be added to cart
                            Toast.makeText(getApplicationContext(),"Only products from the same store can be added to one cart. " +
                                    "To purchase, make a separate order.", Toast.LENGTH_SHORT).show();
                        }
                        else if(snapshot.child("Cart").child("Items").child(productname).exists()){
                            //make popup saying the item is already in cart, go to cart to change quantity
                            Toast.makeText(getApplicationContext(), "Product is already in cart. Visit cart to make changes.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            String olditem_num = (String)snapshot.child("Cart").child("Number of items").getValue();
                            assert olditem_num != null;
                            int item_num_toint = Integer.parseInt(olditem_num);
                            String new_item_num = String.valueOf(item_num_toint+1);

                            subref2.child("Cart").child("Number of items").setValue(new_item_num);
                            subref2.child("Cart").child("Items").child(productname).child("name").setValue(productname);
                            subref2.child("Cart").child("Items").child(productname).child("unit price").setValue(price);
                            subref2.child("Cart").child("Items").child(productname).child("quantity").setValue(selectedquantity);
                            Intent intent = new Intent(product_details.this, product_added.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        });

        Button back_to_prodlist = findViewById(R.id.prod_detail_to_prodlist);
        back_to_prodlist.setOnClickListener(v -> {
            Intent intent = new Intent(this, product_list.class);
            intent.putExtra("clicked_shop", shopname);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        Button back_to_shoplist = findViewById(R.id.prod_detail_to_shoplist);
        back_to_shoplist.setOnClickListener(v -> {
            Intent intent = new Intent(this, Shop_list.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });


    }

}