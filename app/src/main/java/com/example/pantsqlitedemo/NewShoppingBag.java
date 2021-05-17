package com.example.jeansshoppingcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jeansshoppingcart.Adapter.NewShoppingBagAdapter;
import com.example.jeansshoppingcart.Model.CartModel;
import com.example.jeansshoppingcart.Model.MainModel;
import com.example.jeansshoppingcart.databinding.ActivityNewShoppingBagBinding;

import java.util.ArrayList;

public class NewShoppingBag extends AppCompatActivity {

    ActivityNewShoppingBagBinding binding;
    RecyclerView recyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewShoppingBagBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //initialize
        recyclerview = findViewById(R.id.recyclerview);

        //create arrayList for add to new jeans
        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.j1 , "28" ,"Blue Jean" , "1200", "blue"));
        list.add(new MainModel(R.drawable.j2 , "28" ,"Patch Dark Blue Jean" , "1200", "Blue"));
        list.add(new MainModel(R.drawable.j4 , "30" ,"Patch Light Blue Jean" , "1200", "Blue"));
        list.add(new MainModel(R.drawable.j5 , "28" ,"Brown Jean" , "1200", "Brown"));
        list.add(new MainModel(R.drawable.darkbluejean , "28" ,"Dark Blue Jean" , "1200", "Blue"));

        //adapter
        NewShoppingBagAdapter adapter = new NewShoppingBagAdapter(list, this);
        binding.recyclerview.setAdapter(adapter);

        //recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);


    }

    //get menu.xml file to this page
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //set shopping bag icon to denim page
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.bag:
                    startActivity(new Intent(NewShoppingBag.this,AddBagItem.class));
        }
        return super.onOptionsItemSelected(item);
    }
}