package com.example.b07storeapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private ArrayList<Item> dataset;

    //providing reference to type of view listed in recyclerview (custom ViewHolder)
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView editableName;
        public final TextView editablePrice;
        public final TextView editableWeight;
        public final TextView editableBrand;

        public ViewHolder(View view){
            super(view);
            editableName = (TextView) view.findViewById(R.id.editableitemname);
            editablePrice = (TextView) view.findViewById(R.id.editableitemprice);
            editableWeight = (TextView) view.findViewById(R.id.editableitemweight);
            editableBrand = (TextView) view.findViewById(R.id.editableitembrand);
        }

        public TextView getEditableName(){
            return editableName;
        }
        public TextView getEditableBrand(){
            return editableBrand;
        }
        public TextView getEditablePrice(){
            return editablePrice;
        }
        public TextView getEditableWeight(){
            return editableWeight;
        }

    }
    //initialise data of adapter
    public RecyclerViewAdapter(ArrayList<Item> dataset){
        this.dataset = dataset;
    }


    //create new views (used by layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_element, viewGroup, false);
        return new ViewHolder(view);
    }

    //replace contents of a view
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        viewHolder.editableName.setText(dataset.get(position).getItemName());
        viewHolder.editableBrand.setText(dataset.get(position).getItemBrand());
        viewHolder.editablePrice.setText(dataset.get(position).getItemPrice());
        viewHolder.editableWeight.setText(dataset.get(position).getItemWeight());
        /*
        viewHolder.getEditableName().setText(dataset.get(position).getItemName());
        viewHolder.getEditablePrice().setText(dataset.get(position).getItemPrice());
        viewHolder.getEditableWeight().setText(dataset.get(position).getItemWeight());
        viewHolder.getEditableBrand().setText(dataset.get(position).getItemBrand());
         */
    }


    //return size of dataset
    @Override
    public int getItemCount(){
        return dataset.size();
    }

    @Override
    public int getItemViewType(int position){
        return ConstraintLayout.generateViewId();
    }
}
