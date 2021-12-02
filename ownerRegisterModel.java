package com.example.b07storeapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ownerRegisterModel implements RegisterContract.Model{
    @Override
    public void userExists(RegisterContract.Presenter p, String username) {

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = rootRef.child("Users").child("Owners").child(username);
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

    @Override
    public void register(String username, String password) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        User user = new User (username,password);
        ref.child("Users").child("Owners").child(username).setValue(user);

    }
}
