package com.example.jeansshoppingcart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class OrderDBHelper extends SQLiteOpenHelper {

    public OrderDBHelper(Context context) {
        super(context, "Jeans.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ShoppingCart (id TEXT PRIMARY KEY, Name TEXT, Quantity NUMERIC, Price NUMERIC, Total NUMERIC, Color TEXT, Size NUMERIC, Image Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public Boolean AddToShoppingCart(String id, String Name, String Quantity, String Price, String Total, String Color, String Size, String Image){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("id", id);
        cv.put("Name", Name);
        cv.put("Quantity", Quantity);
        cv.put("Price", Price);
        cv.put("Total", Total);
        cv.put("Color", Color);
        cv.put("Size", Size);
        cv.put("Image", Image);
        long res = db.insert("ShoppingCart",null, cv);

        if(res ==-1){
            return  false;
        }else{
            return  true;
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM ShoppingCart";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
           cursor = db.rawQuery(query, null);
        }
        return cursor;
    }



}
