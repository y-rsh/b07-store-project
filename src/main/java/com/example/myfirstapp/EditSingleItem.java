package com.example.b07storeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;

public class EditSingleItem extends AppCompatActivity {

    private static boolean uniqueName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_edit_single_item);
        ItemDataBase database = new ItemDataBase();

        //storename = intent.getStringExtra("storename");
        String itemname = intent.getStringExtra("itemname");
        String itembrand = intent.getStringExtra("itembrand");

        //cutting off $ from price
        String itemprice = intent.getStringExtra("itemprice").substring(1);

        //cutting off g from weight
        String itemweight = intent.getStringExtra("itemweight").substring(0, intent.getStringExtra("itemweight").length()-1);

        EditText editText = (EditText) findViewById(R.id.edititemname);
        editText.setText(itemname);

        editText = (EditText) findViewById(R.id.edititembrand);
        editText.setText(itembrand);

        editText = (EditText) findViewById(R.id.edititemprice);
        editText.setText(itemprice);

        editText = (EditText) findViewById(R.id.edititemweight);
        editText.setText(itemweight);
    }

    public void backButton(View view){
        Intent intent = new Intent(this, EditingItems.class);
        intent.putExtra("storename", ItemDataBase.storename);
        startActivity(intent);
    }

    public void doneButton(View view){
        EditText editText = (EditText) findViewById(R.id.edititemname);
        ItemDataBase database = new ItemDataBase();
        Intent intent = getIntent();
        String olditemname = intent.getStringExtra("itemname");
        String newitemname = editText.getText().toString();

        editText = (EditText) findViewById(R.id.edititembrand);
        String newitembrand = editText.getText().toString();

        editText = (EditText) findViewById(R.id.edititemprice);
        String newitemprice = "$" + editText.getText().toString();

        editText = (EditText) findViewById(R.id.edititemweight);
        String newitemweight = editText.getText().toString() + "g";

        System.out.println(olditemname + " " + newitemname + " " + newitemprice + " " + newitemweight + " " + database);

        database.editItem(olditemname, newitemname, newitemprice, newitembrand, newitemweight, database, this);

    }

    public void doneButtonFailure(String cause){
        TextView textView = (TextView) findViewById(R.id.invalidentrynotification);
        if(cause.equals("notunique")){
            textView.setText("Item name not unique- must be unchanged or unique name");
        }
        else if(cause.equals("invalidlength")){
            textView.setText("Item name must be nonempty and no longer than 50 characters");
        }
        else if(cause.equals("invalidprice")){
            textView.setText("Price input must be valid");
        }
    }

    public void doneButtonSuccess(){
        Intent exitintent = new Intent(this, EditingItems.class);
        exitintent.putExtra("storename", ItemDataBase.storename);
        //exitintent.putExtra("updatedata", true);
        startActivity(exitintent);
    }

    public static void invalidItemName(){
        uniqueName = false;
        System.out.println("invalidItemName called");
    }
}