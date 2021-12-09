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
import java.util.Objects;

public class owner_order_details extends AppCompatActivity {

    private String storename;

    private ArrayList<String> productnames = new ArrayList<>();
    private ArrayList<String> unitprices = new ArrayList<>();
    private ArrayList<String> quantities = new ArrayList<>();
    private String totalcost;
    private double parsedcost, quantity_tonum;
    private double netcost = 0;
    private String[] unitprices_list, quantities_list;
    private String netcost_string, displaycost;
    private final String dollarsign = "$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_order_details);

        storename = getIntent().getStringExtra("storename");
        String orderid = getIntent().getStringExtra("clicked_id");

        TextView idview = findViewById(R.id.orderdetail_id);
        idview.setText(orderid);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("StoreList")
                .child(storename).child("data").child("orders").child(orderid);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(productnames != null && !productnames.isEmpty()){
                    productnames = new ArrayList<>();
                    unitprices = new ArrayList<>();
                    quantities = new ArrayList<>();
                }

                for(DataSnapshot subsnap : snapshot.child("items").getChildren()){
                    productnames.add((String)subsnap.child("name").getValue());
                    unitprices.add((String)subsnap.child("unit price").getValue());
                    quantities.add((String)subsnap.child("quantity").getValue());
                }


                totalcost = Objects.requireNonNull(snapshot.child("total price").getValue()).toString();
                TextView costview = findViewById(R.id.ow_orderdetail_pricetot);
                costview.setText(totalcost);

                RecyclerView cartitemview = findViewById(R.id.orderitem_view);
                LinearLayoutManager layoutManager = new LinearLayoutManager(owner_order_details.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                cartitemview.setLayoutManager(layoutManager);

                DividerItemDecoration itemDecoration = new DividerItemDecoration(owner_order_details.this, layoutManager.getOrientation());
                cartitemview.addItemDecoration(itemDecoration);

                cartitemview.setAdapter(createAdapter(productnames, unitprices, quantities));





                /*unitprices_list = new String[unitprices.size()];
                unitprices_list = unitprices.toArray(unitprices_list);
                quantities_list = new String[quantities.size()];
                quantities_list = quantities.toArray(quantities_list);

                for(int i = 0; i < unitprices_list.length; i++){
                    parsedcost = Double.parseDouble(unitprices_list[i].substring(1));
                    quantity_tonum = (int)Double.parseDouble(quantities_list[i]);
                    netcost += (parsedcost*quantity_tonum);
                }
                TextView totalcost = findViewById(R.id.ow_orderdetail_pricetot);
                netcost_string = String.valueOf(netcost);
                displaycost = dollarsign + netcost_string;
                totalcost.setText(displaycost);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button back_orderlist = findViewById(R.id.orderdetail_backlist);
        back_orderlist.setOnClickListener(v -> {
            Intent intent = new Intent(this, order_full_list.class);
            intent.putExtra("storename", storename);
            startActivity(intent);
        });

        Button back_home = findViewById(R.id.orderdetail_tohome);
        back_home.setOnClickListener(v -> {
            Intent intent = new Intent(this, owner_home.class);
            intent.putExtra("storename", storename);
            startActivity(intent);
        });





    }
    public owner_ordhist_RecyclerviewAdapter createAdapter(ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3){
        return new owner_ordhist_RecyclerviewAdapter(list1, list2, list3);
    }
}