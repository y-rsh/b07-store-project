package b07storeapp.example.owner_ordersactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import com.example.owner_ordersactivity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class View_OrderHistory extends AppCompatActivity {
    private TextView Order_id;
    private TextView Order_completed;
    private TextView Order_items;
    private TextView Order_time;
    private TextView Order_price;
    Store store;
    final String storename = "storename_1";
    List<Order> orders;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_history);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance().getReference("StoreList");

        orders = new ArrayList<>();
        //test things since cannot connect to firebase

        //String[] EXMAMPLE_item = {"Apple","Pencil","Switch"};
        //orders.add(new Order("A001", "true", EXMAMPLE_item, "3"));
        //orders.add(new Order("A002","false",EXMAMPLE_item,"300"));
        //orders.add(new Order("A003","true",EXMAMPLE_item,"99"));
        //orders.add(new Order("B001","true",EXMAMPLE_item,"241"));
        RecyclerView.Adapter mAdapter = new MainAdapter(orders);
        recyclerView.setAdapter(mAdapter);
        //data read
        database.child(storename).child("data").child("orders")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            Order order = dataSnapshot.getValue(Order.class);
                            orders.add(order);
                        }
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        store = (Store) getIntent().getSerializableExtra("storekey");
        String store_name = getIntent().getStringExtra("store_name");
    }
}