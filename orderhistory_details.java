package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class orderhistory_details extends AppCompatActivity {

    private String username;
    private String status;
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> price = new ArrayList<>();
    private ArrayList<String> quantity = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory_details);

        String selectedid = getIntent().getStringExtra("orderid");
        username = getIntent().getStringExtra("username");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference subref = reference.child("Users").child("Customers").child(username)
                .child("Past Orders").child(selectedid);

        subref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(names != null && !names.isEmpty()){
                    names = new ArrayList<>();
                    price = new ArrayList<>();
                    quantity = new ArrayList<>();
                }

                status = (String)snapshot.child("Status").getValue();
                TextView statusview = findViewById(R.id.hist_details_status);
                statusview.setText(status);

                for(DataSnapshot subsnap : snapshot.child("Items").getChildren()){
                    names.add((String)subsnap.child("name").getValue());
                    price.add((String)subsnap.child("price").getValue());
                    quantity.add((String)subsnap.child("quantity").getValue());

                }

                RecyclerView prodlist_view = findViewById(R.id.orderhist_prodlist);
                LinearLayoutManager layoutManager = new LinearLayoutManager(orderhistory_details.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                prodlist_view.setLayoutManager(layoutManager);

                DividerItemDecoration itemDecoration = new DividerItemDecoration(orderhistory_details.this, layoutManager.getOrientation());
                prodlist_view.addItemDecoration(itemDecoration);

                prodlist_view.setAdapter(createAdapter(names, price, quantity));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        Button uporderhist = findViewById(R.id.detail_uptoorderhist);
        uporderhist.setOnClickListener(v -> {
            Intent intent = new Intent(this, past_orderlist.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

    }
    public hist_prod_RecyclerviewAdapter createAdapter(ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3){
        return new hist_prod_RecyclerviewAdapter(list1, list2, list3);
    }
}