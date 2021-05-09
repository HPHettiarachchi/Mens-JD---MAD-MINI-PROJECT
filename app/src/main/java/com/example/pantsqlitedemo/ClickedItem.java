package com.example.pantsqlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ClickedItem extends AppCompatActivity {
    ImageView imageView;
    TextView textName,textPrice,textSize,textColor;
    EditText editPrice,editQyt,editTotal;
    Button AddToCard,CheckOut,Multiple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clicked_item_activity);

        imageView = findViewById(R.id.imgPant);
        textName = findViewById(R.id.txtName);
        textPrice = findViewById(R.id.txtPrice);
        textSize = findViewById(R.id.txtSize);
        textColor = findViewById(R.id.txtColor);
        editPrice = findViewById(R.id.editPrice);
        editQyt = findViewById(R.id.editQuantity);
        editTotal = findViewById(R.id.editTotalPrice);
        AddToCard = findViewById(R.id.btnAddCard);
        CheckOut = findViewById(R.id.btnCheckOut);
        Multiple = findViewById(R.id.btnMultiple);

        Multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = editPrice.getText().toString();
                String temp1 = editQyt.getText().toString();

                int total = Integer.parseInt(temp) * Integer.parseInt(temp1);

                editTotal.setText("Total Price = " +total);
            }
        });

        //set value
        Intent intent = getIntent();
        if(intent.getExtras() != null){
            String selectedName = intent.getStringExtra("name");
            String selectedPrice = intent.getStringExtra("price");
            String selectedSize = intent.getStringExtra("size");
            String selectedColor = intent.getStringExtra("color");

            int selectedImage = intent.getIntExtra("image", 0);

            textName.setText(selectedName);
            textPrice.setText(selectedPrice);
            textSize.setText(selectedSize);
            textColor.setText(selectedColor);
            imageView.setImageResource(selectedImage);


        }
    }
}

