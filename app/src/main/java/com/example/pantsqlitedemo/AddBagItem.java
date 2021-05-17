package com.example.jeansshoppingcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeansshoppingcart.Adapter.NewCartAdapter;
import com.example.jeansshoppingcart.Adapter.NewShoppingBagAdapter;
import com.example.jeansshoppingcart.Model.CartModel;
import com.example.jeansshoppingcart.databinding.ActivityAddBagItemBinding;

import java.util.ArrayList;


public class AddBagItem extends AppCompatActivity {

    ActivityAddBagItemBinding binding;
    RecyclerView recycler_cart;
    TextView txt_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBagItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recycler_cart = findViewById(R.id.recycler_cart);
        txt_price = findViewById(R.id.txt_price);

        OrderDBHelper odb = new OrderDBHelper(this);
        ArrayList<CartModel> list = odb.getOrders();


        //adapter
        NewCartAdapter adapter = new NewCartAdapter(list, this);
        binding.recyclerCart.setAdapter(adapter);

        //recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerCart.setLayoutManager(layoutManager);

    }




}

