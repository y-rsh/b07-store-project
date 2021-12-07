package com.example.b07storeapp;

import java.util.ArrayList;

public class DataAccess {
    private static ArrayList<Item> dataList;

    public DataAccess(){
        dataList = new ArrayList<Item>();
    }

    static void add(Item item){
        if(dataList == null){
            dataList = new ArrayList<Item>();
        }
        dataList.add(item);
        System.out.println("adding following item:");
        item.printItem();
    }

    static ArrayList<Item> read(){
        System.out.println("current contents of DataAccess:");


        ArrayList<Item> listCopy = new ArrayList<Item>();
        if(dataList != null){
            for(Item i: dataList){
                listCopy.add(i);
                i.printItem();
            }
        }
        dataList = new ArrayList<Item>();
        System.out.println("returning listCopy:");
        for(Item i: listCopy){
            i.printItem();
        }
        return listCopy;
    }
}
