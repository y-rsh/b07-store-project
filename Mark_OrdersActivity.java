package b07storeapp.example.owner_ordersactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owner_ordersactivity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Mark_OrdersActivity extends AppCompatActivity {
    private TextView incomplete;
    Store store;
    String storename;
    List<Order> orders;
    DatabaseReference database;
    private TextView show;
    Button btn;
    List<Order> marklist;

    public Mark_OrdersActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_orders);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        orders = new ArrayList<>();
        List<Order> test;
        //test things since cannot connect to firebase

        String nums = "4";
        incomplete = (TextView) findViewById(R.id.incomplete);
        incomplete.setText(nums);

        String[] EXMAMPLE_item = {"Apple","Pencil","Switch"};
        orders.add(new Order("A001", "true", EXMAMPLE_item, "3"));
        orders.add(new Order("A002","false",EXMAMPLE_item,"300"));
        orders.add(new Order("A003","true",EXMAMPLE_item,"99"));
        orders.add(new Order("B001","true",EXMAMPLE_item,"241"));
        orders.add(new Order("B002","true",EXMAMPLE_item,"241"));
        orders.remove(orders.get(1));
        RecyclerView.Adapter mAdapter = new MarkAdapter(orders);
        recyclerView.setAdapter(mAdapter);

        //data read
         database.child(storename).child("data").child("orders")
               .addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {

                       for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                           //if statement here
                           Order order = dataSnapshot.getValue(Order.class);
                           if (order.completed != "completed") {
                               orders.add(order);
                           }
                       }
                       mAdapter.notifyDataSetChanged();
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });


        btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Mark_OrdersActivity.this);
                builder.setTitle("Mark Orders As Completed");
                builder.setMessage("Are you sure to mark the order(s) completed?");
                builder.setPositiveButton("Mark", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(Mark_OrdersActivity.this,"Orders Marked", Toast.LENGTH_LONG).show();
                        if (MarkAdapter.marked != null){
                            for (int i = 0; i< orders.size(); i++ ){
                                for(int j = 0; j < orders.size();j++){
                                    if ((MarkAdapter.marked.get(j) != null) && (orders.get(i).Order_id == MarkAdapter.marked.get(j))){
                                        database.child(storename).child("data").child("orders")
                                                .child(orders.get(i).Order_id).child("status").setValue("completed");
                                    }
                                }
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create();
                builder.show();
            }
        });




        store = (Store) getIntent().getSerializableExtra("storekey");
        String store_name = getIntent().getStringExtra("store_name");
    }
}