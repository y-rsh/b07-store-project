package com.example.b07storeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class owner_ordhist_RecyclerviewAdapter extends RecyclerView.Adapter<owner_ordhist_RecyclerviewAdapter.owner_ordhist_ViewHolder>{

    private final ArrayList<String> productname_Data;
    private final ArrayList<String> unitprice_Data;
    private final ArrayList<String> quantity_Data;


    public owner_ordhist_RecyclerviewAdapter(ArrayList<String> data, ArrayList<String> data_2, ArrayList<String> data_3){
        productname_Data = data;
        unitprice_Data = data_2;
        quantity_Data = data_3;
    }

    public static class owner_ordhist_ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder{
        TextView product_name, unitprice,quantity;

        owner_ordhist_ViewHolder(@NonNull View view){
            super(view);
            product_name = view.findViewById(R.id.cartproduct_name);
            unitprice = view.findViewById(R.id.cart_unitprice);
            quantity = view.findViewById(R.id.cart_quantity);

        }

    }

    @NonNull
    @Override
    public owner_ordhist_RecyclerviewAdapter.owner_ordhist_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewgroup, int viewType){
        View view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.cart_item, viewgroup, false);
        return new owner_ordhist_RecyclerviewAdapter.owner_ordhist_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull owner_ordhist_RecyclerviewAdapter.owner_ordhist_ViewHolder holder, int position){
        String[] productnamelist = new String[productname_Data.size()];
        productnamelist = productname_Data.toArray(productnamelist);
        holder.product_name.setText(productnamelist[position]);

        String[] unitpricelist = new String[unitprice_Data.size()];
        unitpricelist = unitprice_Data.toArray(unitpricelist);
        holder.unitprice.setText(unitpricelist[position]);

        String[] quantitylist = new String[quantity_Data.size()];
        quantitylist = quantity_Data.toArray(quantitylist);
        holder.quantity.setText(quantitylist[position]);


    }

    @Override
    public int getItemCount() {
        return productname_Data.size();
    }


}
