package com.example.b07storeapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;


public class EditingRecyclerViewAdapter extends RecyclerView.Adapter<EditingRecyclerViewAdapter.ViewHolder>{
    private static ArrayList<Item> dataset;
    private static View.OnClickListener clickListener;

    //providing reference to type of view listed in recyclerview (custom ViewHolder)
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView editableName;
        private final TextView editableWeight;
        private final TextView editableBrand;
        private final ImageButton deleteButton;
        private final Context context;
        private WeakReference<View.OnClickListener> listenerRef;

        public ViewHolder(View view, View.OnClickListener listener){
            super(view);
            context = view.getContext();
            view.setOnClickListener(this);
            listenerRef = new WeakReference<View.OnClickListener>(listener);
            deleteButton = (ImageButton) view.findViewById(R.id.deleteimagebutton);
            editableName = (TextView) view.findViewById(R.id.editingitemname);
            editableWeight = (TextView) view.findViewById(R.id.editingitemweight);
            editableBrand = (TextView) view.findViewById(R.id.editingitembrand);
            deleteButton.setOnClickListener(this);
        }

        public TextView getEditableName(){
            return editableName;
        }
        public TextView getEditableBrand(){
            return editableBrand;
        }
        public TextView getEditableWeight(){
            return editableWeight;
        }
        //public ImageButton getDeleteButton(){return deleteButton;}

        @Override
        public void onClick(View v) {
            final Intent intent;
            if(v.getId() == deleteButton.getId()) {
                //deletebutton hit specifically
                for (int i = 0; i < dataset.size(); i++) {
                    if (getLayoutPosition() == i) {
                        AlertDialog prompt = EditingItems.deleteItemPrompt(dataset.get(i), (EditingItems) context);
                        prompt.show();
                        break;
                    }
                }
            }
            else{
                //any other part of view hit
                for (int i = 0; i < dataset.size(); i++) {
                    if (getLayoutPosition() == i) {
                        intent = new Intent(context, EditSingleItem.class);
                        intent.putExtra("itemname", dataset.get(i).getItemName());
                        intent.putExtra("itemweight", dataset.get(i).getItemWeight());
                        intent.putExtra("itemprice", dataset.get(i).getItemPrice());
                        intent.putExtra("itembrand", dataset.get(i).getItemBrand());
                        //intent.putExtra("storename", EditSingleItem.storename);
                        context.startActivity(intent);
                        //call "onclick" function in editingitems.java
                        //pass dataset.get(i) as input to pass into next activity
                        break;
                    }
                }
            }
        }
    }
    //initialise data of adapter
    public EditingRecyclerViewAdapter(ArrayList<Item> dataset, View.OnClickListener listener){
        this.dataset = dataset;
        this.clickListener = listener;
    }


    //create new views (used by layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler2_element, viewGroup, false);
        return new ViewHolder(view, clickListener);
    }

    //replace contents of a view
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        viewHolder.getEditableName().setText(dataset.get(position).getItemName());
        viewHolder.getEditableWeight().setText(dataset.get(position).getItemWeight());
        viewHolder.getEditableBrand().setText(dataset.get(position).getItemBrand());
    }


    //return size of dataset
    @Override
    public int getItemCount(){
        return dataset.size();
    }

}
