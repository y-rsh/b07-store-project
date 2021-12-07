package com.example.b07storeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class customer_login extends AppCompatActivity implements LoginContract.View{
    public static final String username = "com.example.B07StoreApp.MESSAGE";
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
        Intent intent = new Intent(this, customer_home.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        String user =editText.getText().toString();
        intent.putExtra(username, user);
        startActivity(intent);
    }

    public void handleClick(View view){
        presenter.checkCustomerLogin();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);


        presenter = new LoginPresenter((LoginContract.Model) new LoginModel(), this);

    }

    public void toRegister(View view){
        Intent intent = new Intent(this, customer_register.class);
        startActivity(intent);
    }



}