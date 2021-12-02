package com.example.b07storeapp;



import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class customerLoginModel implements LoginContract.Model {


    public void userExists(LoginContract.Presenter p, String username) {
        // see if user exists in database

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = rootRef.child("Users").child("Customers").child(username);
        userNameRef.addListenerForSingleValueEvent(new ValueEventListener() {




            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    p.usernameNoExist();

                }
                else {

                    p.usernameExists();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });



    }

    public void passwordMatches(LoginContract.Presenter p,String username, String password) {
        //see if password matches the password on the database

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = rootRef.child("Users").child("Customers").child(username);
        userNameRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String databasePass = (String) snapshot.child("password").getValue(String.class);
                if(databasePass.equals(password)){
                    p.correctPass();
                }
                else{
                    p.incorrectPass();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}



