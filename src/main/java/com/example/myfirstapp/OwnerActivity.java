package b07storeapp.example.owner_ordersactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import java.io.Serializable;


public class OwnerActivity extends AppCompatActivity {
    private Store store;
    private String store_name = "storename_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void view_order(View view) {
        Intent intent = new Intent(OwnerActivity.this,View_OrderHistory.class);
        intent.putExtra("storekey", (Serializable) store);
        intent.putExtra("store_name_test", store_name);
        startActivity(intent);
    }

    public void mark_orders(View view){
        Intent intent = new Intent(OwnerActivity.this,Mark_OrdersActivity.class);
        intent.putExtra("storekey",(Serializable) store);
        intent.putExtra("sotre_name_test2", store_name);
        startActivity(intent);
    }
}
