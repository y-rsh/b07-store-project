package com.example.b07storeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class owner_login extends AppCompatActivity implements LoginContract.View {
    //public static final String username = "com.example.B07StoreApp.MESSAGE";

    private LoginContract.Presenter presenter;

    public String getUsername(){
        EditText editText = findViewById(R.id.editTextTextPersonName3);
        return editText.getText().toString();
    }

    public String getPassword(){
        EditText editText = findViewById(R.id.editTextTextPassword4);
        return editText.getText().toString();
    }

    public void displayMsg(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }


    public void startSuccessfulLoginActivity() {
        Intent intent = new Intent(this, owner_home.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName3);
        String user =editText.getText().toString();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child("Owners").child(user);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String storename = Objects.requireNonNull(snapshot.child("Store Name").getValue()).toString();
                intent.putExtra("storename", storename);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void handleClick(View view){
        presenter.checkOwnerLogin();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);

        presenter = new LoginPresenter((LoginContract.Model) new LoginModel(), this);
    }

    public void toRegister(View view){
        Intent intent = new Intent(this, owner_register.class);
        startActivity(intent);
    }
}