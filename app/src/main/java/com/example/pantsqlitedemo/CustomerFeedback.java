package com.example.jeansshoppingcart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class CustomerFeedback extends AppCompatActivity {

    //Initialize variables
    EditText new_et_feedback, new_et_Name;
    Button new_viewBtn, new_addBtn;
    TextView new_rateCount;
    RatingBar new_ratingBar;
    DBHelper db;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_feedback);

        //Assign variables
        new_et_feedback = findViewById(R.id.new_et_feedback);
        new_et_Name = findViewById(R.id.new_et_Name);
        new_viewBtn = findViewById(R.id.new_viewBtn);
        new_addBtn = findViewById(R.id.new_addBtn);
        new_rateCount = findViewById(R.id.new_rateCount);
        new_ratingBar = findViewById(R.id.new_ratingBar);
        db = new DBHelper(this);

        //Display rating bar value
        new_ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating == 1){
                    new_rateCount.setText("Very Dissatisfied");
                }else if(rating == 2){
                    new_rateCount.setText("Dissatisfied");
                }else if(rating == 3) {
                    new_rateCount.setText("OK");
                }else if(rating == 4) {
                    new_rateCount.setText("Satisfied");
                }else if(rating == 5){
                    new_rateCount.setText("Very Satisfied");
                }
            }
        });

        //Initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation for Name
        awesomeValidation.addValidation(this,R.id.new_et_Name, RegexTemplate.NOT_EMPTY,R.string.empty_name);

        //Add Validation for feedback
        awesomeValidation.addValidation(this,R.id.new_et_feedback, RegexTemplate.NOT_EMPTY,R.string.empty_feedback);


        new_addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = new_et_Name.getText().toString();
                String feedbackText = new_et_feedback.getText().toString();

                Boolean checkingInsertFeedback = db.insertFeedbackData(nameText, feedbackText);

                //check validation
                        //if values validate
                if(checkingInsertFeedback == true && awesomeValidation.validate()){
                    Toast.makeText(CustomerFeedback.this, "New Entry inserted", Toast.LENGTH_SHORT).show();
                }else{//values are not inserted
                    Toast.makeText(CustomerFeedback.this, "New Entry NOT inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        new_viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getFeedback();
                if(res.getCount() ==0 ){
                    Toast.makeText(CustomerFeedback.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Feedback :"+res.getString(1)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerFeedback.this);
                builder.setCancelable(true);
                builder.setTitle("Customer Feedback");
                builder.setMessage(buffer.toString());
                builder.show();

            }


        });

    }
}