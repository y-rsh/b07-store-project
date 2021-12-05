package com.example.myfirstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MainViewHolder>{

    private final ArrayList<String> itemData;
    private final Onitemlistener mOnitemlistener;

    public RecyclerviewAdapter(ArrayList<String> itemData, Onitemlistener onitemlistener) {

        this.itemData = itemData;
        this.mOnitemlistener = onitemlistener;
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;
        Onitemlistener onitemlistener;

        MainViewHolder(@NonNull View itemView, Onitemlistener onitemlistener) {
            super(itemView);
            text = itemView.findViewById(R.id.shopname_text);
            this.onitemlistener = onitemlistener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onitemlistener.onitemclick(getAbsoluteAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewgroup, int viewType) {
        View view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.shop_item, viewgroup, false);
        return new MainViewHolder(view, mOnitemlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        String[] list = new String[itemData.size()];
        list = itemData.toArray(list);
        holder.text.setText(list[position]);

    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    public interface Onitemlistener{
        void onitemclick(int position);
    }

}



