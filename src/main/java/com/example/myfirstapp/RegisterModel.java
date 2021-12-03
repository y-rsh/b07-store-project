package com.example.b07storeapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterModel implements RegisterContract.Model{
    @Override
    public void userExists(RegisterContract.Presenter p, String username, String type) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = rootRef.child("Users").child(type).child(username);
        userNameRef.addListenerForSingleValueEvent(new ValueEventListener() {




            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    if(type.equals("Customers")) {
                        p.usernameNoExistCustomer();
                    }
                    else{
                        p.usernameNoExistOwner();
                    }

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

    @Override
    public void register(String username, String password, String type) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        User user = new User (username,password);
        ref.child("Users").child(type).child(username).setValue(user);

    }
}
