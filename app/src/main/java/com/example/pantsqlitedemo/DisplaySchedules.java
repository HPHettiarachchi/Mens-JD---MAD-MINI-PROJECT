package com.example.schedulegenix1;

import android.os.Bundle;
import android.app.Activity;
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

public class DisplaySchedules extends AppCompatActivity {
    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb ;
    TextView Title ;
    TextView Date;
    TextView StartTime;
    TextView EndTime;
    TextView Venue;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_schedules);
        Title = (TextView) findViewById(R.id.editTextTitle);
        Date = (TextView) findViewById(R.id.editTextDate);
        StartTime = (TextView) findViewById(R.id.editTextStartTime);
        EndTime = (TextView) findViewById(R.id.editTextEndTime);
        Venue = (TextView) findViewById(R.id.editTextVenue);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getScheduleData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String Titl = rs.getString(rs.getColumnIndex(DBHelper.SCHEDULES_COLUMN_TITLE));
                String Dat = rs.getString(rs.getColumnIndex(DBHelper.SCHEDULES_COLUMN_DATE));
                String StartTim = rs.getString(rs.getColumnIndex(DBHelper.SCHEDULES_COLUMN_STARTTIME));
                String EndTim = rs.getString(rs.getColumnIndex(DBHelper.SCHEDULES_COLUMN_ENDTIME));
                String Venu = rs.getString(rs.getColumnIndex(DBHelper.SCHEDULES_COLUMN_VENUE));

                if (!rs.isClosed())  {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);

                Title.setText((CharSequence)Titl);
                Title.setFocusable(false);
                Title.setClickable(false);

                Date.setText((CharSequence)Dat);
                Date.setFocusable(false);
                Date.setClickable(false);

                StartTime.setText((CharSequence)StartTim);
                StartTime.setFocusable(false);
                StartTime.setClickable(false);

                EndTime.setText((CharSequence)EndTim);
                EndTime.setFocusable(false);
                EndTime.setClickable(false);

                Venue.setText((CharSequence)Venu);
                Venue.setFocusable(false);
                Venue.setClickable(false);
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
                getMenuInflater().inflate(R.menu.display_schedule, menu);
            } else{
                getMenuInflater().inflate(R.menu.main_menu, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.Edit_Schedule:
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);
                Title.setEnabled(true);
                Title.setFocusableInTouchMode(true);
                Title.setClickable(true);

                Date.setEnabled(true);
                Date.setFocusableInTouchMode(true);
                Date.setClickable(true);

                StartTime.setEnabled(true);
                StartTime.setFocusableInTouchMode(true);
                StartTime.setClickable(true);

                EndTime.setEnabled(true);
                EndTime.setFocusableInTouchMode(true);
                EndTime.setClickable(true);

                Venue.setEnabled(true);
                Venue.setFocusableInTouchMode(true);
                Venue.setClickable(true);

                return true;
            case R.id.Delete_Schedule:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteSchedule)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteSchedule(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Schedule.class);
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
                if(mydb.updateSchedule(id_To_Update,Title.getText().toString(),
                        Date.getText().toString(), StartTime.getText().toString(),
                        EndTime.getText().toString(), Venue.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Schedule.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            } else{
                if(mydb.insertSchedule(Title.getText().toString(), Date.getText().toString(),
                        StartTime.getText().toString(), EndTime.getText().toString(),
                        Venue.getText().toString())){
                    Toast.makeText(getApplicationContext(), "done",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "not done",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),Schedule.class);
                startActivity(intent);
            }
        }
    }


}
