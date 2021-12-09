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
import java.util.Objects;

public class update_order_status extends AppCompatActivity implements Shopping_RecyclerviewAdapter.Onitemlistener{

    private String storename;
    private final ArrayList<String> orderid_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order_status);

        storename = getIntent().getStringExtra("storename");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("StoreList").child(storename).child("data");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("orders").exists()){
                    for(DataSnapshot subsnap : snapshot.child("orders").getChildren()){
                        if(!Objects.requireNonNull(subsnap.child("status").getValue()).toString().equals("Pickup Complete")){
                            String id = subsnap.getKey();
                            orderid_list.add(id);
                        }
                    }
                    RecyclerView shoplistview = findViewById(R.id.updatable_orders);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(update_order_status.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    shoplistview.setLayoutManager(layoutManager);

                    DividerItemDecoration itemDecoration = new DividerItemDecoration(update_order_status.this, layoutManager.getOrientation());
                    shoplistview.addItemDecoration(itemDecoration);

                    shoplistview.setAdapter(createadapter(orderid_list));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        Button back_to_ownerhome = findViewById(R.id.backto_ownerhome);
        back_to_ownerhome.setOnClickListener(v -> {
            Intent intent = new Intent(this, owner_home.class);
            intent.putExtra("storename", storename);
            startActivity(intent);
        });



    }
    public Shopping_RecyclerviewAdapter createadapter(ArrayList<String> list){
        return new Shopping_RecyclerviewAdapter(list, this);
    }

    @Override
    public void onitemclick(int position) {
        String selected_id = orderid_list.get(position);
        Intent intent = new Intent(this, update_order_status_individual.class);
        intent.putExtra("storename", storename);
        intent.putExtra("clicked_id", selected_id);
        startActivity(intent);
    }
}