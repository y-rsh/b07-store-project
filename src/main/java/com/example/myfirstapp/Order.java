package com.example.b07storeapp;

import java.io.Serializable;

public class Order implements Serializable {
    String Order_id;
    String completed;
    String[] Order_items;
    String totalprice;

    public Order(String Order_id, String completed, String[] Order_items, String totalprice){
        this.Order_id = Order_id;
        this.completed = completed;
        this.Order_items = Order_items;
        this.totalprice = totalprice;
    }

    public String getOrder_id() {
        return Order_id;
    }

    public String getCompleted() {
        return completed;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public String[] getOrder_items() {
        return Order_items;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }
    public void setOrder_id(String order_id) {
        Order_id = order_id;
    }

    public void setOrder_items(String[] order_items) {
        Order_items = order_items;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }
}
