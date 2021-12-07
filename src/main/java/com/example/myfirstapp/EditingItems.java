package com.example.b07storeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class EditingItems extends AppCompatActivity {

    private String storename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_editing_items);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.itemrecycler2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        storename = intent.getStringExtra("storename");
        ItemDataBase database = new ItemDataBase();
        HelperObject ho = new HelperObject();
        RecyclerView.Adapter rAdapter = new EditingRecyclerViewAdapter(ho.datalist, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        database.fillHelperData(storename, ho, rAdapter);

        recyclerView.setAdapter(rAdapter);

    }

    protected void onResume(){
        super.onResume();
        this.updateRecyclerView();
    }

    protected void onStart(){
        super.onStart();
        this.updateRecyclerView();
    }

    public void doneButton(View view){
        Intent intent = new Intent(this, EditItems.class);
        intent.putExtra("storename", this.storename);
        startActivity(intent);
    }

    public void addButton(View view){
        Intent intent = new Intent(this, AddItem.class);
        intent.putExtra("storename", this.storename);
        startActivity(intent);
    }

    public static AlertDialog deleteItemPrompt(Item item, EditingItems editingItems){
        System.out.println("deleteItemPrompt hit");
        ItemDataBase database = new ItemDataBase();
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(editingItems)
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Deleting: Are You Sure?")

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        database.removeItem(item.getItemName());

                        //your deleting code
                        dialog.dismiss();
                        editingItems.updateRecyclerView();
                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        return myQuittingDialogBox;
    }

    public void updateRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.itemrecycler2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ItemDataBase database = new ItemDataBase();
        HelperObject ho = new HelperObject();
        RecyclerView.Adapter rAdapter = new EditingRecyclerViewAdapter(ho.datalist, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        database.fillHelperData(storename, ho, rAdapter);

        recyclerView.setAdapter(rAdapter);
    }

    public void clickedItem(Item item){

        //Intent intent = new Intent(this, NewActivityName)
        //intent.putExtra("itemname", item.getItemName());
        //  .
        //  .
        //  .
        //
        //intent.putExtra("itembrand", item.getItemBrand());
        //startActivity(intent);
    }

}