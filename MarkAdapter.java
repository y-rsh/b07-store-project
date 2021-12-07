package b07storeapp.example.owner_ordersactivity;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.owner_ordersactivity.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class MarkAdapter extends RecyclerView.Adapter<MarkAdapter.MarkViewHolder> {
    private static List<Order> orders;
    public List<Integer> check_id;
    public static ArrayList<String> marked;
    public static String test;
    DatabaseReference database;
    String storename;

    public MarkAdapter(List<Order> orders) {
        this.orders = orders;
    }


    public static class MarkViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_price;
        TextView tv_item;
        CheckBox check;


        public MarkViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_price = itemView.findViewById(R.id.price);
            tv_item = itemView.findViewById(R.id.item);
            check = itemView.findViewById(R.id.checkBox);
        }

    }

    @NonNull
    @Override
    public MarkAdapter.MarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout2, parent, false);
        return new MarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarkViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String item_list = "Item Bought: ";
        for (int i = 0; i < orders.get(position).Order_items.length; i++) {
            item_list += orders.get(position).Order_items[i] + " ";
        }
        String id = "ORDER ID:" + orders.get(position).getOrder_id();
        String price = "Total Price: " + orders.get(position).totalprice;

        holder.tv_id.setText(id);
        holder.tv_price.setText(price);
        holder.tv_item.setText(item_list);

        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holder.check.isChecked()){
                    //marked.add(orders.get(position).Order_id);
                    database.child(storename).child("data").child("orders")
                            .child(orders.get(position).Order_id).child("status")
                    .setValue("completed");
                    test = "checked";
                }
                else{
                    test = "NO";
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return orders.size();
    }

}
