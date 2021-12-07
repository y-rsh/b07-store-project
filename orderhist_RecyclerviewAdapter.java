package com.example.b07storeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class orderhist_RecyclerviewAdapter extends RecyclerView.Adapter<orderhist_RecyclerviewAdapter.hist_ViewHolder> {

    private final ArrayList<String> id_array, price_array, statusarray;
    private final hist_OnItemListener listener;

    public orderhist_RecyclerviewAdapter(ArrayList<String> array1, ArrayList<String> array2, ArrayList<String> array3, hist_OnItemListener listener){
        id_array = array1;
        price_array = array2;
        statusarray = array3;
        this.listener = listener;
    }

    public static class hist_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView id, price, status;
        hist_OnItemListener listener;

        hist_ViewHolder(@NonNull View itemView, hist_OnItemListener listener){
            super(itemView);
            id = itemView.findViewById(R.id.hist_orderid);
            price = itemView.findViewById(R.id.hist_totalprice);
            status = itemView.findViewById(R.id.hist_stats);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onitemclick(getAbsoluteAdapterPosition());
        }
    }


    @NonNull
    @Override
    public orderhist_RecyclerviewAdapter.hist_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewgroup, int viewType) {
        View view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.pastorder_item, viewgroup, false);
        return new hist_ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull orderhist_RecyclerviewAdapter.hist_ViewHolder holder, int position) {
        String[] idlist = new String[id_array.size()];
        idlist = id_array.toArray(idlist);
        String[] pricelist = new String[price_array.size()];
        pricelist = price_array.toArray(pricelist);
        String[] statuslist = new String[statusarray.size()];
        statuslist = statusarray.toArray(statuslist);
        holder.id.setText(idlist[position]);
        holder.price.setText(pricelist[position]);
        holder.status.setText(statuslist[position]);
    }

    @Override
    public int getItemCount() {
        return id_array.size();
    }

    public interface hist_OnItemListener{
        void onitemclick(int position);
    }

}
