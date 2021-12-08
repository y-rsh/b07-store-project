package com.example.b07storeapp;

import java.util.ArrayList;

public class Data {
    ArrayList<Item> items;
    ArrayList<Order> orders;

    public Data(ArrayList<Item> items, ArrayList<Order> orders){
        this.orders = orders;
        this.items = items;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void setItems(ArrayList<Item> store_items) {
        items = items;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
