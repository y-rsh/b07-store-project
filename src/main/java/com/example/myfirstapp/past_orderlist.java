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

public class past_orderlist extends AppCompatActivity implements orderhist_RecyclerviewAdapter.hist_OnItemListener{

    private String username;
    private ArrayList<String> orderids = new ArrayList<>();
    private ArrayList<String> prices = new ArrayList<>();
    private ArrayList<String> status_array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_orderlist);

        username = getIntent().getStringExtra("username");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference subref = reference.child("Users").child("Customers").child(username);

        subref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(orderids != null && !orderids.isEmpty()){
                    orderids = new ArrayList<>();
                    prices = new ArrayList<>();
                    status_array = new ArrayList<>();
                }

                if(snapshot.child("Past Orders").exists()){
                    String pastordersnum = (String)snapshot.child("Number of Past Orders").getValue();
                    assert pastordersnum != null;
                    int numpastorders = (int)Double.parseDouble(pastordersnum);
                    for(int num = 1; num < numpastorders+1 ; num++) {
                        String deriveid = username + num;
                        orderids.add((String)snapshot.child("Past Orders").child(deriveid).child("Order ID").getValue());
                        prices.add((String)snapshot.child("Past Orders").child(deriveid).child("Total price").getValue());
                        status_array.add((String)snapshot.child("Past Orders").child(deriveid).child("Status").getValue());
                    }
                }

                RecyclerView pastorder_view = findViewById(R.id.pastorders);
                LinearLayoutManager layoutManager = new LinearLayoutManager(past_orderlist.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                pastorder_view.setLayoutManager(layoutManager);

                DividerItemDecoration itemDecoration = new DividerItemDecoration(past_orderlist.this, layoutManager.getOrientation());
                pastorder_view.addItemDecoration(itemDecoration);

                pastorder_view.setAdapter(createAdapter(orderids, prices, status_array));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button gobackhome = findViewById(R.id.orderhist_gohome);
        gobackhome.setOnClickListener(v -> {
            Intent intent = new Intent(this, customer_home.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

    }

    @Override
    public void onitemclick(int position) {
        String selectedid = orderids.get(position);
        Intent intent = new Intent(this, orderhistory_details.class);
        intent.putExtra("username", username);
        intent.putExtra("orderid", selectedid);
        startActivity(intent);
    }

    public orderhist_RecyclerviewAdapter createAdapter(ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3){
        return new orderhist_RecyclerviewAdapter(list1, list2, list3, this);
    }
}