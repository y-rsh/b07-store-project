package com.example.b07storeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class owner_login extends AppCompatActivity implements LoginContract.View {


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
        Intent intent = new Intent(this, LoginSuccess.class);
        startActivity(intent);
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