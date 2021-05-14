package com.example.pantsqlitedemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ManagerMainActivity extends AppCompatActivity {

        EditText edtName, edtPrice, edtSize, edtColor;
        Button btnChoose, btnAdd, btnList, btnView;
        ImageView imageView;

        final int REQUEST_CODE_GALLERY = 999;

        public static SQLiteHelper sqLiteHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_manager_main);

            init();

            sqLiteHelper = new SQLiteHelper(this,"JeansDB.sqlite", null, 1);
            sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS JeansItems (Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, price VARCHAR, size VARCHAR, color VARCHAR, image BLOG)");

            btnChoose.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ActivityCompat.requestPermissions(ManagerMainActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_GALLERY
                    );
                }
            });

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        sqLiteHelper.insertData(
                                edtName.getText().toString().trim(),
                                edtPrice.getText().toString().trim(),
                                edtSize.getText().toString().trim(),
                                edtColor.getText().toString().trim(),
                                imageViewToByte(imageView)
                        );
                        Toast.makeText(getApplicationContext(),"Added Successfully!", Toast.LENGTH_SHORT).show();
                        edtName.setText("");
                        edtPrice.setText("");
                        edtSize.setText("");
                        edtColor.setText("");
                        imageView.setImageResource(R.mipmap.ic_launcher);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            btnList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ManagerMainActivity.this, PantsList.class);
                    startActivity(intent);
                }
            });

            btnList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ManagerMainActivity.this, PantsList.class);
                    startActivity(intent);
                }
            });

            btnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ManagerMainActivity.this, JeansList.class);
                    startActivity(intent);
                }
            });
        }

        public static byte[] imageViewToByte(ImageView image) {
            Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            if(requestCode == REQUEST_CODE_GALLERY){
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,REQUEST_CODE_GALLERY);
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
            if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
                Uri uri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);

                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        }

        private void init(){
            edtName = (EditText) findViewById(R.id.edtName);
            edtPrice = (EditText) findViewById(R.id.edtPrice);
            edtSize = (EditText) findViewById(R.id.edtSize);
            edtColor = (EditText) findViewById(R.id.edtColor);
            btnChoose = (Button) findViewById(R.id.btnChoose);
            btnAdd = (Button) findViewById(R.id.btnAdd);
            btnList = (Button) findViewById(R.id.btnList);
            btnView = (Button) findViewById(R.id.btnView);
            imageView = (ImageView) findViewById(R.id.imageView);
        }
    }
