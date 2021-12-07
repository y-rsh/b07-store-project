package com.example.b07storeapp;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class HelperObject {
    public ArrayList<Item> datalist;

    public HelperObject(){
        datalist = new ArrayList<Item>();
    }

    public void addToDataList(DataSnapshot snapshot){
        Item item = new Item();
        //System.out.println("creating item");
        item.setItemBrand((String) snapshot.child("brand").getValue());
        //System.out.println(item.getItemBrand());
        item.setItemName((String) snapshot.child("name").getValue());
        item.setItemPrice((String) snapshot.child("price").getValue());
        item.setItemWeight((String) snapshot.child("weight").getValue());
        datalist.add(item);
    }

    public ArrayList<Item> getDatalist(){
        return datalist;
    }

}
