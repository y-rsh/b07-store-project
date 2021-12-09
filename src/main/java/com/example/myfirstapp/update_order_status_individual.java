package com.example.b07storeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Objects;

public class update_order_status_individual extends AppCompatActivity {

    private String storename, orderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order_status_individual);

        storename = getIntent().getStringExtra("storename");
        orderid = getIntent().getStringExtra("clicked_id");

        Spinner newstats = findViewById(R.id.newstats_spinner);
        TextView idview = findViewById(R.id.orderid_indiv_view);
        idview.setText(orderid);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("StoreList")
                .child(storename).child("data").child("orders").child(orderid);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String current_status = Objects.requireNonNull(snapshot.child("status").getValue()).toString();
                TextView statusview = findViewById(R.id.currentstatusview);
                statusview.setText(current_status);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button confirmupdate = findViewById(R.id.updateconfirm);
        confirmupdate.setOnClickListener(v -> {
            String chosenstatus = newstats.getSelectedItem().toString();
            DatabaseReference ref_2 = FirebaseDatabase.getInstance().getReference();

            ref_2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ref_2.child("StoreList").child(storename).child("data").child("orders").child(orderid).child("status").setValue(chosenstatus);
                    String orderuser = Objects.requireNonNull(snapshot.child("StoreList").child(storename)
                            .child("data").child("orders").child(orderid).child("ordered user").getValue()).toString();
                    ref_2.child("Users").child("Customers").child(orderuser).child("Past Orders").child(orderid).child("Status").setValue(chosenstatus);
                    Intent intent = new Intent(update_order_status_individual.this, orderstatus_updatecomplete.class);
                    intent.putExtra("storename", storename);
                    startActivity(intent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });


        Button backtoorders = findViewById(R.id.indiv_toall_orderupdate);
        backtoorders.setOnClickListener(v -> {
            Intent intent = new Intent(this, update_order_status.class);
            intent.putExtra("storename", storename);
            startActivity(intent);
        });

    }
}