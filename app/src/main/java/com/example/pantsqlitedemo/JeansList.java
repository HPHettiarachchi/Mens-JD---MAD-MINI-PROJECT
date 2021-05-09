package com.example.pantsqlitedemo;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class JeansList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Jeans> list;
    JeansViewListAdapter adapter = null;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeans_view_list);

        gridView = (GridView) findViewById(R.id.graidViewType);
        list = new ArrayList<>();
        adapter = new JeansViewListAdapter(this, R.layout.jeans_view_items, list);
        gridView.setAdapter(adapter);

        //get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM JeansItems");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String size = cursor.getString(3);
            String color = cursor.getString(4);
            byte[] image = cursor.getBlob(5);

            list.add(new Jeans(id, name, price, size, color, image));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,  final int position, long id) {

                CharSequence[] items = {"Add To Card","CheckOut"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(JeansList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int items) {
                        if (items == 0){
                            //update
                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT id FROM JeansItems");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            //show dialog update  at here
                            showDialogUpdate(JeansList.this, arrID.get(position));
                        }else{
                            //delete

                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT id FROM JeansItems");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
    ImageView imageViewPants;
    private void showDialogUpdate(Activity activity, final int position){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.clicked_item_activity);
        dialog.setTitle("Add To Card");

        imageViewPants = (ImageView) dialog.findViewById(R.id.imageViewPants);
        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
        final EditText edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
        final EditText edtSize = (EditText) dialog.findViewById(R.id.edtSize);
        final EditText edtColor = (EditText) dialog.findViewById(R.id.edtColor);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnAddCard);

        //set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        //set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imageViewPants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //request photo library
                ActivityCompat.requestPermissions(
                        JeansList.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity.sqLiteHelper.updateData(
                            position,
                            edtName.getText().toString().trim(),
                            edtPrice.getText().toString().trim(),
                            edtSize.getText().toString().trim(),
                            edtColor.getText().toString().trim(),
                            MainActivity.imageViewToByte(imageViewPants)
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Add successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error){
                    Log.e("Add error:", error.getMessage());
                }
                updateJeansList();
            }
        });
    }

    private void showDialogDelete(final int idPant) {
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(JeansList.this);
        dialogDelete.setTitle("Warning!!") ;
        dialogDelete.setMessage("Are you sure you want to this?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    MainActivity.sqLiteHelper.deleteData(idPant);
                    Toast.makeText(getApplicationContext(),"Order successfully!!!",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateJeansList();
            }
        });
        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }
    private void updateJeansList() {
        //get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM JeansItems");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String size = cursor.getString(3);
            String color = cursor.getString(4);
            byte[] image = cursor.getBlob(5);

            list.add(new Jeans(id, name, price, size, color, image));
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 888){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            }
            else{
                Toast.makeText(getApplicationContext(),"You don't have permission to access file location!",Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 888 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewPants.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}

