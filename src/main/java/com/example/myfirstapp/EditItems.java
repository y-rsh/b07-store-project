package com.example.b07storeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

public class EditItems extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public String storeName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_edit);
        this.storeName = "storename_1";

        //new DataAccess();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.itemrecycler1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ItemDataBase dataBase = new ItemDataBase();
        Intent intent = getIntent();


        //FOR IMPLEMENTATION WITH GROUPS STUFF
        ItemDataBase.storename = intent.getStringExtra("storename");
        this.storeName = intent.getStringExtra("storename");


        //TEMPORARY
        //this.storeName = "storename_1";


        HelperObject ho = new HelperObject();


        //dataBase.printItems();
        RecyclerView.Adapter rAdapter = new RecyclerViewAdapter(ho.datalist);

        dataBase.fillHelperData(storeName, ho, rAdapter);

        System.out.println("recyclerview initialized");
        recyclerView.setAdapter(rAdapter);
    }


    //called when we hit the EDIT button
    public void editItems(View view){
        Intent intent = new Intent(this, EditingItems.class);
        intent.putExtra("storename", this.storeName);
        startActivity(intent);
        //send to edit activity
    }

    /** Called when the user taps the Send button */

}