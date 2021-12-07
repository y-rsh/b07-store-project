package com.example.b07storeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Checkout_RecyclerviewAdapter extends RecyclerView.Adapter<Checkout_RecyclerviewAdapter.Checkout_ViewHolder> {

    private final ArrayList<String> productname_Data;
    private final ArrayList<String> unitprice_Data;
    private final ArrayList<String> quantity_Data;

    private final Checkout_Onitemlistener checkoutOnitemlistener;

    public Checkout_RecyclerviewAdapter(ArrayList<String> data, ArrayList<String> data_2, ArrayList<String> data_3, Checkout_Onitemlistener listener){
        productname_Data = data;
        unitprice_Data = data_2;
        quantity_Data = data_3;
        checkoutOnitemlistener = listener;
    }

    public static class Checkout_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView product_name, unitprice,quantity;
        Checkout_Onitemlistener listener;

        Checkout_ViewHolder(@NonNull View view, Checkout_Onitemlistener listener){
            super(view);
            product_name = view.findViewById(R.id.cartproduct_name);
            unitprice = view.findViewById(R.id.cart_unitprice);
            quantity = view.findViewById(R.id.cart_quantity);
            this.listener = listener;
            view.setOnClickListener(this);

        }
        @Override
        public void onClick(View v){listener.onitemclick(getAbsoluteAdapterPosition());}

    }

    @NonNull
    @Override
    public Checkout_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewgroup, int viewType){
        View view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.cart_item, viewgroup, false);
        return new Checkout_ViewHolder(view, checkoutOnitemlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull Checkout_ViewHolder holder, int position){
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
    public int getItemCount(){ return productname_Data.size();}



    public interface Checkout_Onitemlistener{
        void onitemclick(int position);
    }


}
