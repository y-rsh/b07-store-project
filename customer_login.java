package com.example.b07storeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class customer_login extends AppCompatActivity implements LoginContract.View{

    private LoginContract.Presenter presenter;

    public String getUsername(){
        EditText editText = findViewById(R.id.editTextTextPersonName);
        return editText.getText().toString();
    }

    public String getPassword(){
        EditText editText = findViewById(R.id.editTextTextPassword);
        return editText.getText().toString();
    }

    public void displayMsg(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }


    public void startSuccessfulLoginActivity() {
        Intent intent = new Intent(this, LoginSuccess.class);
        startActivity(intent);
    }

    public void handleClick(View view){
        presenter.checkLogin();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);


        presenter = new customerLoginPresenter((LoginContract.Model) new customerLoginModel(), this);

    }

    public void toRegister(View view){
        Intent intent = new Intent(this, customer_register.class);
        startActivity(intent);
    }



}