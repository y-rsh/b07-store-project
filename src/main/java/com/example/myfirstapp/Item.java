package com.example.b07storeapp;

public class Item {
    public String name;
    public String weight;
    public String price;
    public String brand;

    public Item(){}

    public void setItemBrand(String itemBrand) {
        this.brand = itemBrand;
    }
    public void setItemName(String itemName){
        this.name = itemName;
    }
    public void setItemWeight(String itemWeight) {
        this.weight = itemWeight;
    }
    public void setItemPrice(String itemPrice) {
        this.price = itemPrice;
    }

    public String getItemBrand() {
        return brand;
    }

    public String getItemName() {
        return name;
    }

    public String getItemPrice() {
        return price;
    }

    public String getItemWeight() {
        return weight;
    }

    public void printItem(){
        System.out.println("["+ this.getItemName() + ", " + this.getItemPrice() + ", " + this.getItemWeight() + ", " + this.getItemBrand() + " ]");
    }

}
