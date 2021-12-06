package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Checkout extends AppCompatActivity implements Checkout_RecyclerviewAdapter.Checkout_Onitemlistener{

    private ArrayList<String> productnames = new ArrayList<>();
    private ArrayList<String> unitprices = new ArrayList<>();
    private ArrayList<String> quantities = new ArrayList<>();
    private String username;
    private double parsedcost, quantity_tonum;
    private double netcost = 0;
    private String[] productname_list, unitprices_list, quantities_list;
    private String netcost_string, displaycost;
    private final String dollarsign = "$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        username = getIntent().getStringExtra("username");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference subref = reference.child("Users").child("Customers").child(username);


        subref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(productnames != null && !productnames.isEmpty()){
                    productnames = new ArrayList<>();
                    unitprices = new ArrayList<>();
                    quantities = new ArrayList<>();
                }
                if(snapshot.child("Cart").exists()){
                    for(DataSnapshot subsnap : snapshot.child("Cart").child("Items").getChildren()){
                        productnames.add((String)subsnap.child("name").getValue());
                        unitprices.add((String)subsnap.child("unit price").getValue());
                        quantities.add((String)subsnap.child("quantity").getValue());
                    }

                    RecyclerView cartitemview = findViewById(R.id.cart_itemlist);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(Checkout.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    cartitemview.setLayoutManager(layoutManager);

                    DividerItemDecoration itemDecoration = new DividerItemDecoration(Checkout.this, layoutManager.getOrientation());
                    cartitemview.addItemDecoration(itemDecoration);

                    cartitemview.setAdapter(createAdapter(productnames, unitprices, quantities));

                }

                unitprices_list = new String[unitprices.size()];
                unitprices_list = unitprices.toArray(unitprices_list);
                quantities_list = new String[quantities.size()];
                quantities_list = quantities.toArray(quantities_list);

                for(int i = 0; i < unitprices_list.length; i++){
                    parsedcost = Double.parseDouble(unitprices_list[i].substring(1));
                    quantity_tonum = (int)Double.parseDouble(quantities_list[i]);
                    netcost += (parsedcost*quantity_tonum);
                }
                TextView totalcost = findViewById(R.id.cart_netcost);
                netcost_string = String.valueOf(netcost);
                displaycost = dollarsign + netcost_string;
                totalcost.setText(displaycost);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        Button doorder = findViewById(R.id.finishorder);
        doorder.setOnClickListener(v -> {


            DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference();
            reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(!snapshot.child("Users").child("Customers").child(username).child("Cart").exists()){
                        Toast.makeText(getApplicationContext(), "Cart is empty. Add products to cart before sending an order.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String storename = (String)snapshot.child("Users").child("Customers").child(username)
                                .child("Cart").child("Storename").getValue();
                        String ordernum = (String)snapshot.child("Users").child("Customers").child(username).child("Number of Past Orders").getValue();
                        assert ordernum != null;
                        int ordernum_toint = (int)Double.parseDouble(ordernum);
                        String newordernum = String.valueOf(ordernum_toint+1);
                        String orderid = username + newordernum;
                        assert storename != null;

                        if(!snapshot.child("StoreList").child(storename).child("data").child("orders").exists()){
                            reference2.child("StoreList").child(storename).child("data").child("orders").child("Number of incomplete orders").setValue("1");
                        }
                        else{
                            String incomplete_orders = (String)snapshot.child("StoreList").child(storename).child("data").child("orders")
                                    .child("Number of incomplete orders").getValue();
                            assert incomplete_orders != null;
                            int incomp_ordernum = (int)Double.parseDouble(incomplete_orders);
                            String new_incompordernum = String.valueOf(incomp_ordernum+1);
                            reference2.child("StoreList").child(storename).child("data").child("orders").child("Number of incomplete orders").setValue(new_incompordernum);

                        }
                        
                        DatabaseReference subref1 = reference2.child("StoreList").child(storename).child("data");


                        subref1.child("orders").child(orderid).child("status").setValue("Preparing");
                        subref1.child("orders").child(orderid).child("order id").setValue(orderid);
                        subref1.child("orders").child(orderid).child("total price").setValue(displaycost);

                        productname_list = new String[productnames.size()];
                        productname_list = productnames.toArray(productname_list);

                        for(int j = 0; j < unitprices_list.length; j++){
                            subref1.child("orders").child(orderid).child("items").child(productname_list[j]).child("name").setValue(productname_list[j]);
                            subref1.child("orders").child(orderid).child("items").child(productname_list[j]).child("quantity").setValue(quantities_list[j]);
                        }
                        DatabaseReference subref2 = reference2.child("Users").child("Customers").child(username);

                        String cur_pordernum = (String)snapshot.child("Users").child("Customers").child(username).child("Number of Past Orders").getValue();
                        assert cur_pordernum != null;
                        int cur_porderint = (int)Double.parseDouble(cur_pordernum);
                        String new_porder = String.valueOf(cur_porderint+1);

                        subref2.child("Number of Past Orders").setValue(new_porder);

                        subref2.child("Past Orders").child(orderid).child("Order ID").setValue(orderid);
                        subref2.child("Past Orders").child(orderid).child("Status").setValue("Preparing");
                        subref2.child("Past Orders").child(orderid).child("Total price").setValue(displaycost);


                        for(int k = 0; k < productname_list.length; k++){
                            subref2.child("Past Orders").child(orderid).child("Items").child(productname_list[k]).child("name").setValue(productname_list[k]);
                            subref2.child("Past Orders").child(orderid).child("Items").child(productname_list[k]).child("price").setValue(unitprices_list[k]);
                            subref2.child("Past Orders").child(orderid).child("Items").child(productname_list[k]).child("quantity").setValue(quantities_list[k]);
                        }

                        subref2.child("Cart").removeValue();

                        Intent intent = new Intent(Checkout.this, Checkout_finish.class);
                        startActivity(intent);

                    }



                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        });

        Button gohome = findViewById(R.id.ordertohome);
        gohome.setOnClickListener(v -> {
            Intent intent = new Intent(Checkout.this, customer_home.class);
            startActivity(intent);
        });


    }

    public Checkout_RecyclerviewAdapter createAdapter(ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3){
        return new Checkout_RecyclerviewAdapter(list1, list2, list3, this);
    }


    @Override
    public void onitemclick(int position) {
        String selecteditem = productnames.get(position);
        String selectedunitprice = unitprices.get(position);
        String selectedquantity = quantities.get(position);
        Intent intent = new Intent(this, Cart_itemconfigure.class);
        intent.putExtra("username", username);
        intent.putExtra("productname", selecteditem);
        intent.putExtra("unitprice", selectedunitprice);
        intent.putExtra("quantity", selectedquantity);
        startActivity(intent);


    }
}