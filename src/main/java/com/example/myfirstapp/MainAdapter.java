package com.example.b07storeapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{

    private static List<Order> orders;


    public MainAdapter(List<Order> orders) {
        MainAdapter.orders = orders;
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView text;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            text = (TextView) itemView.findViewById(R.id.tvOrder);
        }


        @Override
        public void onClick(View v) {
            final Intent intent;
            for (int i = 0; i < orders.size();i++){
                if(getLayoutPosition() == i){
                    Order Order_chosen = orders.get(i);
                    intent = new Intent(itemView.getContext(), Order_DetailActivity.class);

                    intent.putExtra("ORDER_ID",Order_chosen.Order_id);
                    intent.putExtra("ORDER_Price",Order_chosen.Order_items);
                    intent.putExtra("orderkey",Order_chosen);
                    itemView.getContext().startActivity(intent);
                    break;
                }
            }
        }
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup viewgroup, int viewType) {
        View view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.layout, viewgroup, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        String id = "ORDER ID:" + orders.get(position).getOrder_id();
        holder.text.setText(id);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}

