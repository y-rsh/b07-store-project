package com.example.b07storeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class customer_register extends AppCompatActivity implements RegisterContract.View {

    private RegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);

        presenter = new customerRegisterPresenter((RegisterContract.Model) new customerRegisterModel(), this);
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
        Intent intent = new Intent(this, RegisterSuccess.class);
        startActivity(intent);
    }

    public void handleClick(View view){
        presenter.checkRegister();
    }
}