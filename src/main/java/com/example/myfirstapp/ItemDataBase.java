package com.example.b07storeapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemDataBase {
    private DatabaseReference mDatabase;
    private ArrayList<Item> itemList;
    public static String storename;


    public ItemDataBase(){
        itemList = new ArrayList<Item>();
        mDatabase = FirebaseDatabase.getInstance("https://grocerytron-9000-default-rtdb.firebaseio.com/").getReference();
    }

    public DatabaseReference getmDatabase() {
        return mDatabase;
    }


    public void fillHelperData(String storename, HelperObject ho, RecyclerView.Adapter adapter){
        this.storename = storename;
        //System.out.println("fetchItems is called");
        //CountDownLatch finished = new CountDownLatch(1);
        //final AtomicReference<ArrayList<Item>> returnreference = new AtomicReference<ArrayList<Item>>();
        DatabaseReference itemList = mDatabase.child("StoreList").child(storename).child("data").child("items");
        //System.out.println("listener being added");
        itemList.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("onDataChange hit");
                if(dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ho.addToDataList(snapshot);
                        System.out.println("adding to data list now");
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("failed to populate");
            }
        });
    }


    /*
    public void readData(DatabaseReference ref, final OnGetDataListener listener){
        listener.onStart();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listener.onSuccess(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onFailure();
            }
        });
    }

    public ArrayList<Item> fetchItems(String storename){
        //verify complete populatedataaccess
        populateDataAccess(storename);

        System.out.println("exited populateDataAccess");

        return DataAccess.read();
    }

     */

    public void addItem(Item item){
        this.itemList.add(item);
    }

    public void printItems(){
        for(Item i : itemList){
            System.out.println("["+ i.getItemName() + ", " + i.getItemPrice() + ", " + i.getItemWeight() + ", " + i.getItemBrand() + " ]");
        }
    }


    public void editExistingItem(String itemname, String newprice, String newbrand, String newweight, EditSingleItem editSingleItem){
        DatabaseReference item = mDatabase.child("StoreList").child(storename).child("data").child("items").child(itemname);
        item.child("price").setValue(newprice);
        item.child("weight").setValue(newweight);
        item.child("brand").setValue(newbrand);
        editSingleItem.doneButtonSuccess();
    }

    public void editItem(String olditemname, String newitemname, String newprice, String newbrand, String newweight, ItemDataBase database, EditSingleItem editSingleItem){
        if(olditemname.equals(newitemname)){
            editExistingItem(newitemname, newprice, newbrand, newweight, editSingleItem);
        }
        else if(isValidName(newitemname)){
            Item item = new Item();
            item.setItemName(newitemname);
            item.setItemBrand(newbrand);
            item.setItemWeight(newweight);
            item.setItemPrice(newprice);
            checkUniqueName(item, olditemname, database, editSingleItem);
        }
        else editSingleItem.doneButtonFailure("invalidlength");
    }

    public void addItem(String newitemname, String newprice, String newbrand, String newweight, ItemDataBase database, AddItem editSingleItem){
        if(isValidName(newitemname)){
            Item item = new Item();
            item.setItemName(newitemname);
            item.setItemBrand(newbrand);
            item.setItemWeight(newweight);
            item.setItemPrice(newprice);
            UniqueNameAddCheck(item, database, editSingleItem);
        }
    }

    public void UniqueNameAddCheck(Item newitemname, ItemDataBase database, AddItem editSingleItem){
        DatabaseReference itemList = mDatabase.child("StoreList").child(storename).child("data").child("items");
        itemList.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(newitemname.getItemName())){
                    editSingleItem.AddButtonFailure("notunique");
                }
                else if(!checkPriceInput(newitemname.getItemPrice())){
                    editSingleItem.AddButtonFailure("invalidprice");
                }
                else{
                    database.makeItem(newitemname);
                    editSingleItem.AddButtonSuccess();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void checkUniqueName(Item newitemname, String olditemname, ItemDataBase database, EditSingleItem editSingleItem){
        DatabaseReference itemList = mDatabase.child("StoreList").child(storename).child("data").child("items");
        itemList.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(newitemname.getItemName())){
                    editSingleItem.doneButtonFailure("notunique");
                }
                else if(!checkPriceInput(newitemname.getItemPrice())){
                    editSingleItem.doneButtonFailure("invalidprice");
                }
                else{
                    database.makeItem(newitemname);
                    database.removeItem(olditemname);
                    editSingleItem.doneButtonSuccess();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public void makeItem(Item itemname){
        DatabaseReference itemList = mDatabase.child("StoreList").child(storename).child("data").child("items");
        //itemList.child(itemname.getItemName()).setValue(itemname);
        itemList.child(itemname.getItemName()).child("name").setValue(itemname.getItemName());
        itemList.child(itemname.getItemName()).child("brand").setValue(itemname.getItemBrand());
        itemList.child(itemname.getItemName()).child("price").setValue(itemname.getItemPrice());
        itemList.child(itemname.getItemName()).child("weight").setValue(itemname.getItemWeight());
    }

    public void removeItem(String itemname){
        DatabaseReference itemList = mDatabase.child("StoreList").child(storename).child("data").child("items");
        itemList.child(itemname).child("name").setValue(null);
        itemList.child(itemname).child("brand").setValue(null);
        itemList.child(itemname).child("price").setValue(null);
        itemList.child(itemname).child("weight").setValue(null);
    }

    public static boolean isValidName(String itemname){
        boolean good = true;
        if(itemname.length() == 0) good = false;
        if(itemname.length() > 50) good = false;
        if(itemname == null) good = false;
        return good;
    }

    public boolean checkPriceInput(String priceinput){
        return(priceinput.matches("(\\$\\d+\\.\\d{0,2})"));
    }




}
