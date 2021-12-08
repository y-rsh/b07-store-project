package com.example.b07storeapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class MarkAdapter extends RecyclerView.Adapter<MarkAdapter.MarkViewHolder>{
    private static List<Order> orders;
    public List<Integer> check_id;
    public static ArrayList<String> marked = new ArrayList<>();
    public static String test;
    private Store store;
    DatabaseReference database;
    String storename;

    public MarkAdapter(List<Order> orders) {
        MarkAdapter.orders = orders;
    }


    public static class MarkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_id;
        TextView tv_price;
        TextView tv_item;
        CheckBox check;
        String store_name;

        public MarkViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_price = itemView.findViewById(R.id.yining_price);
            tv_item = itemView.findViewById(R.id.item);

        }

        @Override
        public void onClick(View v) {
            final Intent intent;
            for (int i = 0; i < orders.size();i++) {
                if (getLayoutPosition() == i) {
                    Order Order_chosen = orders.get(i);
                    intent = new Intent(itemView.getContext(), Mark_DetailActivity.class);
                    intent.putExtra("orderchosen", Order_chosen);
                    intent.putExtra("store_name", store_name);
                    itemView.getContext().startActivity(intent);
                    break;
                }
            }
        }
    }

    @NonNull
    @Override
    public MarkAdapter.MarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout2, parent, false);
        return new MarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarkViewHolder holder,int position) {
        String item_list = "Item Bought: ";
        for (int i = 0; i < orders.get(position).Order_items.length; i++) {
            item_list += orders.get(position).Order_items[i] + " ";
        }
        String id = "ORDER ID:" + orders.get(position).getOrder_id();
        String price = "Total Price: " + orders.get(position).totalprice;

        holder.tv_id.setText(id);
        holder.tv_price.setText(price);
        holder.tv_item.setText(item_list);

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

}
