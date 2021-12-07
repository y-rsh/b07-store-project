package com.example.b07storeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        ItemDataBase itemDataBase = new ItemDataBase();
    }

    public void AddButton(View view){
        EditText editText = (EditText) findViewById(R.id.edititemname);
        ItemDataBase database = new ItemDataBase();
        //Intent intent = getIntent();
        //String olditemname = intent.getStringExtra("itemname");
        String newitemname = editText.getText().toString();

        editText = (EditText) findViewById(R.id.edititembrand);
        String newitembrand = editText.getText().toString();

        editText = (EditText) findViewById(R.id.edititemprice);
        String newitemprice = "$" + editText.getText().toString();

        editText = (EditText) findViewById(R.id.edititemweight);
        String newitemweight = editText.getText().toString() + "g";

        //System.out.println(olditemname + " " + newitemname + " " + newitemprice + " " + newitemweight + " " + database);

        database.addItem( newitemname, newitemprice, newitembrand, newitemweight, database, this);

        //check validitiy
        //add data
        //return to previous activity
    }

    public void BackButton(View view){
        Intent intent = new Intent(this, EditingItems.class);
        intent.putExtra("storename", ItemDataBase.storename);
        startActivity(intent);
    }

    public void AddButtonFailure(String cause){
        TextView textView = (TextView) findViewById(R.id.invalidentrynotification);
        if(cause.equals("notunique")){
            textView.setText("Item name is not unique- must be unchanged or unique name");
        }
        else if(cause.equals("invalidlength")){
            textView.setText("Item name must be nonempty and no longer than 50 characters");
        }
        else if(cause.equals("invalidprice")){
            textView.setText("Price input must be valid");
        }
    }

    public void AddButtonSuccess(){
        Intent exitintent = new Intent(this, EditingItems.class);
        exitintent.putExtra("storename", ItemDataBase.storename);
        //exitintent.putExtra("updatedata", true);
        startActivity(exitintent);
    }

}