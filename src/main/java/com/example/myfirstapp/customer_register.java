package com.example.b07storeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class customer_register extends AppCompatActivity implements RegisterContract.View {
    public static final String username = "com.example.B07StoreApp.MESSAGE";
    private RegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);

        presenter = new RegisterPresenter((RegisterContract.Model) new RegisterModel(), this);
    }

    @Override
    public String getUsername() {
        EditText editText = findViewById(R.id.editTextTextPersonName2);
        return editText.getText().toString();
    }

    @Override
    public String getPassword() {
        EditText editText = findViewById(R.id.editTextTextPassword2);
        return editText.getText().toString();
    }

    @Override
    public String getReEnterPassword() {
        EditText editText = findViewById(R.id.editTextTextPassword3);
        return editText.getText().toString();
    }

    @Override
    public void displayMsg(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startSuccessfulRegistrationActivity() {
        Intent intent = new Intent(this, customer_home.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName2);
        String user =editText.getText().toString();
        intent.putExtra(username, user);
        startActivity(intent);
    }

    public void handleClick(View view){
        presenter.checkRegisterCustomer();
    }
}