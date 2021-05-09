package com.example.pantsqlitedemo;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class AboutUs extends AppCompatActivity {
    TextView txtVision, txtMission;
    EditText editVision,editMission;
    ImageView fashion1,fashion2,fashion3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        txtVision = findViewById(R.id.txtVision);
        txtMission = findViewById(R.id.txtMission);
        editVision = findViewById(R.id.editVision);
        editMission = findViewById(R.id.editMission);
        fashion1 = findViewById(R.id.imageFashion1);
        fashion2 = findViewById(R.id.imageFashion2);
        fashion3 = findViewById(R.id.imageFashion3);

    }
}