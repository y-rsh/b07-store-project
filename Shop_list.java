package com.example.b07storeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Shop_list extends AppCompatActivity implements Shopping_RecyclerviewAdapter.Onitemlistener {

    private ArrayList<String> shoplist = new ArrayList<>();
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        username = getIntent().getStringExtra("username");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference shopref = reference.child("StoreList");


        shopref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(shoplist != null && !shoplist.isEmpty()){
                    shoplist = new ArrayList<>();
                }
                for(DataSnapshot subsnap : dataSnapshot.getChildren()){
                    shoplist.add((String)subsnap.child("Storename").getValue());
                }
                RecyclerView shoplistview = findViewById(R.id.shops);
                LinearLayoutManager layoutManager = new LinearLayoutManager(Shop_list.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                shoplistview.setLayoutManager(layoutManager);

                DividerItemDecoration itemDecoration = new DividerItemDecoration(Shop_list.this, layoutManager.getOrientation());
                shoplistview.addItemDecoration(itemDecoration);

                shoplistview.setAdapter(createadapter(shoplist));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        Button tohome = findViewById(R.id.shoplist_tohome);
        tohome.setOnClickListener(v -> {
            Intent intent = new Intent(this, customer_home.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });


    }
    @Override
    public void onitemclick(int position){
        String selectedshop = shoplist.get(position);
        Intent intent = new Intent(this, product_list.class);
        intent.putExtra("username", username);
        intent.putExtra("clicked_shop", selectedshop);
        startActivity(intent);
    }

    public Shopping_RecyclerviewAdapter createadapter(ArrayList<String> list){
         return new Shopping_RecyclerviewAdapter(list, this);
    }

}