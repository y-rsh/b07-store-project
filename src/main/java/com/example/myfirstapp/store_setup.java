package com.example.b07storeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class store_setup extends AppCompatActivity {

    private String username;
    private ArrayList<String> storelist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_setup);

        username = getIntent().getStringExtra("username");

        Button confirmbutton = findViewById(R.id.storename_confirm);
        confirmbutton.setOnClickListener(v -> {
            EditText editText = findViewById(R.id.storename_entered);
            String enteredname = editText.getText().toString();
            if(enteredname.equals("")){
                Toast.makeText(getApplicationContext(),"Store name cannot be blank.", Toast.LENGTH_SHORT).show();
            }

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot subsnap: snapshot.child("StoreList").getChildren()){
                        String store_name = subsnap.getKey();
                        if(!storelist.isEmpty()){
                            storelist = new ArrayList<>();
                        }
                        storelist.add(store_name);
                    }

                    boolean result = true;
                    for(String one_name : storelist){
                        if (enteredname.equals(one_name)) {
                            result = false;
                            break;
                        }
                    }
                    if(!result){
                        Toast.makeText(getApplicationContext(),"The store name already exists.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        reference.child("Users").child("Owners").child(username).child("Store Name").setValue(enteredname);
                        reference.child("StoreList").child(enteredname).child("Storename").setValue(enteredname);
                        Intent intent = new Intent(store_setup.this, owner_home.class);
                        intent.putExtra("storename", enteredname);
                        startActivity(intent);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });

    }
}