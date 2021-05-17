package com.example.schedulegenix1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class DisplayCardInformation extends AppCompatActivity {
    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb ;

    TextView name ;
    TextView type;
    TextView number;
    TextView date;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_card_information);
        name = (TextView) findViewById(R.id.editTextName);
        type = (TextView) findViewById(R.id.editTextType);
        number = (TextView) findViewById(R.id.editTextNumber);
        date = (TextView) findViewById(R.id.editTextDate);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getCardData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String nam = rs.getString(rs.getColumnIndex(DBHelper.CARD_INFORMATION_COLUMN_NAME));
                String typ = rs.getString(rs.getColumnIndex(DBHelper.CARD_INFORMATION_COLUMN_TYPE));
                String numbe = rs.getString(rs.getColumnIndex(DBHelper.CARD_INFORMATION_COLUMN_NUMBER));
                String dat = rs.getString(rs.getColumnIndex(DBHelper.CARD_INFORMATION_COLUMN_DATE));


                if (!rs.isClosed())  {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);

                name.setText((CharSequence)nam);
                name.setFocusable(false);
                name.setClickable(false);

                type.setText((CharSequence)typ);
                type.setFocusable(false);
                type.setClickable(false);

                number.setText((CharSequence)numbe);
                number.setFocusable(false);
                number.setClickable(false);

                date.setText((CharSequence)dat);
                date.setFocusable(false);
                date.setClickable(false);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                getMenuInflater().inflate(R.menu.display_card_information, menu);
            } else{
                getMenuInflater().inflate(R.menu.main_menu, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.Edit_Information:
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);

                type.setEnabled(true);
                type.setFocusableInTouchMode(true);
                type.setClickable(true);

                number.setEnabled(true);
                number.setFocusableInTouchMode(true);
                number.setClickable(true);

                date.setEnabled(true);
                date.setFocusableInTouchMode(true);
                date.setClickable(true);

                return true;
            case R.id.Delete_Information:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.delete_Information)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteInformation(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivityCard.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                if(mydb.updateinformation(id_To_Update,name.getText().toString(),
                        type.getText().toString(), number.getText().toString(),
                        date.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivityCard.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            } else{
                if(mydb.insertinformation(name.getText().toString(), type.getText().toString(),
                        number.getText().toString(), date.getText().toString())){
                    Toast.makeText(getApplicationContext(), "done",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "not done",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivityCard.class);
                startActivity(intent);
            }
        }
    }
}

