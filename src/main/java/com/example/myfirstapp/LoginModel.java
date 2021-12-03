package com.example.b07storeapp;



import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginModel implements LoginContract.Model {


    public void userExists(LoginContract.Presenter p, String username, String type) {
        // see if user exists in database

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = rootRef.child("Users").child(type).child(username);
        userNameRef.addListenerForSingleValueEvent(new ValueEventListener() {




            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    p.usernameNoExist();

                }
                else {

                    if(type.equals("Customers")){
                        p.usernameExistsCustomer();
                    }
                    else{
                        p.usernameExistsOwner();
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });



    }

    public void passwordMatches(LoginContract.Presenter p,String username, String password, String type) {
        //see if password matches the password on the database

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = rootRef.child("Users").child(type).child(username);
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



