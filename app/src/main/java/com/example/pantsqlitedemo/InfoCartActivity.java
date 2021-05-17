package com.example.jeansshoppingcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InfoCartActivity extends AppCompatActivity {

    //get all the views are in layout
    TextView jeanNameinInfo, jeanPrice, jeanColor, jeanSize, quantitynum;
    ImageView imageViewInfo;
    ImageButton addquantity,subquantity;
    Button addtocart;
    int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_cart);


        jeanNameinInfo = (TextView) findViewById(R.id.jeanNameinInfo);
        jeanPrice = (TextView)findViewById(R.id.jeanPrice);
        jeanColor = (TextView)findViewById(R.id.jeanColor);
        jeanSize =(TextView) findViewById(R.id.jeanSize);
        quantitynum = (TextView)findViewById(R.id.quantitynum);
        imageViewInfo = (ImageView) findViewById(R.id.imageViewInfo);
        addquantity =(ImageButton) findViewById(R.id.addquantity);
        subquantity =(ImageButton) findViewById(R.id.subquantity);
        addtocart = (Button) findViewById(R.id.addtocart);

        OrderDBHelper odb = new OrderDBHelper(this);


        //add to cart button navigate to cart item Activity
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = jeanNameinInfo.getText().toString();
                String quantity = quantitynum.getText().toString();
                String price = jeanPrice.getText().toString();
                String color = jeanColor.getText().toString();
                String size = jeanSize.getText().toString();
               Integer image = imageViewInfo.getImageAlpha();

                //pass data to database
                boolean inserted = odb.AddToShoppingCart(name,quantity,price,color,size,image);

                if(inserted)
                    Toast.makeText(InfoCartActivity.this,"Data Inserted success.",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(InfoCartActivity.this,"Data not Inserted.",Toast.LENGTH_SHORT).show();
            }
        });



        //getting data and set InfoCart view
        imageViewInfo.setImageResource(getIntent().getIntExtra("jeanimage",0));
        jeanNameinInfo.setText(getIntent().getStringExtra("mainName"));
        jeanPrice.setText(getIntent().getStringExtra("price"));
        jeanColor.setText(getIntent().getStringExtra("color"));
        jeanSize.setText(getIntent().getStringExtra("size"));




        //set add quantity button
        addquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jean price change in give quantity
               int basePrice = 1200;
                quantity++;
                displayQuantity();
                int jeanprice = basePrice * quantity;
                String setNewPrice = String.valueOf(jeanprice);
                jeanPrice.setText(setNewPrice);


            }
        });

        //set
        subquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int basePrice = 1200;
                //cannot go to quantity less than zero
                if(quantity == 0){
                    Toast.makeText(InfoCartActivity.this,"order cannot less than zero", Toast.LENGTH_SHORT).show();
                }else{
                    quantity--; //quantity get down
                    displayQuantity();
                    int jeanprice = basePrice * quantity;
                    String setNewPrice = String.valueOf(jeanprice);
                    jeanPrice.setText(setNewPrice);
                }
            }
        });

    }

    private void displayQuantity(){
        quantitynum.setText(String.valueOf(quantity));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.bag:
                startActivity(new Intent(InfoCartActivity.this,AddBagItem.class));
        }
        return super.onOptionsItemSelected(item);
    }
}