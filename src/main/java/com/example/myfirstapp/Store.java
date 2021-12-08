package com.example.b07storeapp;

import java.io.Serializable;

public class Store implements Serializable {
    public String storename;
    public Data data;
    public String password;
    public String username;


    public Data getData() {
        return data;
    }

    public String getPassword() {
        return password;
    }

    public String getStorename() {
        return storename;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
