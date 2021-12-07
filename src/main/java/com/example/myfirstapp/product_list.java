package com.example.b07storeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class product_list extends AppCompatActivity implements Shopping_RecyclerviewAdapter.Onitemlistener{

    private String shopname;
    private String username;
    private ArrayList<String> productlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


        shopname = getIntent().getStringExtra("clicked_shop");
        username = getIntent().getStringExtra("username");

        TextView name_of_shop = findViewById(R.id.Shopname);
        name_of_shop.setText(shopname);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference productref = reference.child("StoreList").child(shopname).child("data").child("items");

        productref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!productlist.isEmpty()) {
                    productlist = new ArrayList<>();
                }
                for (DataSnapshot subsnap : snapshot.getChildren()) {
                    productlist.add((String) subsnap.child("name").getValue());
                }

                RecyclerView productlistview = findViewById(R.id.Products);
                LinearLayoutManager layoutManager = new LinearLayoutManager(product_list.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                productlistview.setLayoutManager(layoutManager);

                DividerItemDecoration itemDecoration = new DividerItemDecoration(product_list.this, layoutManager.getOrientation());
                productlistview.addItemDecoration(itemDecoration);

                productlistview.setAdapter(createadapter(productlist));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button prodlist_toshoplist = findViewById(R.id.prod_list_backtoshops);
        prodlist_toshoplist.setOnClickListener(v -> {
            Intent intent = new Intent(product_list.this, Shop_list.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        Button prodlist_tohome = findViewById(R.id.prod_list_backhome);
        prodlist_tohome.setOnClickListener(v -> {
            Intent intent = new Intent(product_list.this, customer_home.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

    }


    public Shopping_RecyclerviewAdapter createadapter(ArrayList<String> list){
        return new Shopping_RecyclerviewAdapter(list, this);

    }


    @Override
    public void onitemclick(int position) {
        String selectedproduct = productlist.get(position);
        Intent intent = new Intent(this, product_details.class);
        intent.putExtra("username", username);
        intent.putExtra("clicked_product", selectedproduct);
        intent.putExtra("clicked_shop", shopname);
        startActivity(intent);
    }
}