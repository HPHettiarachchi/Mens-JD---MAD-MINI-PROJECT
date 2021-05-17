package com.example.schedulegenix1;
import android.os.Bundle;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayContactStore extends AppCompatActivity {
    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb;

    TextView name;
    TextView phone;
    TextView address;
    TextView email;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact_store);
        name = (TextView) findViewById(R.id.editTextName);
        phone = (TextView) findViewById(R.id.editTextPhone);
        address = (TextView) findViewById(R.id.editTextAddress);
        email = (TextView) findViewById(R.id.editTextEmail);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");

            if (Value > 0) {
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData1(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String nam = rs.getString(rs.getColumnIndex(DBHelper.CONTACT_STORE_COLUMN_NAME));
                String phon = rs.getString(rs.getColumnIndex(DBHelper.CONTACT_STORE_COLUMN_PHONE));
                String addres = rs.getString(rs.getColumnIndex(DBHelper.CONTACT_STORE_COLUMN_ADDRESS));
                String emai = rs.getString(rs.getColumnIndex(DBHelper.CONTACT_STORE_COLUMN_EMAIL));


                if (!rs.isClosed()) {
                    rs.close();
                }
                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);

                name.setText((CharSequence) nam);
                name.setFocusable(false);
                name.setClickable(false);

                phone.setText((CharSequence) phon);
                phone.setFocusable(false);
                phone.setClickable(false);

                address.setText((CharSequence) addres);
                address.setFocusable(false);
                address.setClickable(false);

                email.setText((CharSequence) emai);
                email.setFocusable(false);
                email.setClickable(false);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                getMenuInflater().inflate(R.menu.display_contact_store, menu);
            } else {
                getMenuInflater().inflate(R.menu.main_menu, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.Edit_Contact:
                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);

                phone.setEnabled(true);
                phone.setFocusableInTouchMode(true);
                phone.setClickable(true);

                address.setEnabled(true);
                address.setFocusableInTouchMode(true);
                address.setClickable(true);

                email.setEnabled(true);
                email.setFocusableInTouchMode(true);
                email.setClickable(true);

                return true;
            case R.id.Delete_User:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.delete)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteContact(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Contact.class);
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
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (mydb.updateContact(id_To_Update, name.getText().toString(),
                        phone.getText().toString(), address.getText().toString(),
                        email.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Contact.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (mydb.insertContact(name.getText().toString(), phone.getText().toString(),
                        address.getText().toString(), email.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "done",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "not done",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), Contact.class);
                startActivity(intent);
            }
        }
    }
}

