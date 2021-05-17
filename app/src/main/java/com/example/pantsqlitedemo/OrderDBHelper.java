package com.example.jeansshoppingcart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jeansshoppingcart.Model.CartModel;

import java.util.ArrayList;


public class OrderDBHelper extends SQLiteOpenHelper {

    public OrderDBHelper(Context context) {
        super(context, "CJeans.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ShoppingCartt (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                               "Name TEXT," +
                                               "Quantity TEXT," +
                                               "Price TEXT," +
                                               "Color TEXT," +
                                               "Size TEXT," +
                                               "Image INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists ShoppingCartt");
        onCreate(db);
    }

    public Boolean AddToShoppingCart(String Name, String Quantity, String Price, String Color, String Size, Integer Image){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Name", Name);
        cv.put("Quantity", Quantity);
        cv.put("Price", Price);
        cv.put("Color", Color);
        cv.put("Size", Size);
        cv.put("Image", Image);
        long res = db.insert("ShoppingCart",null, cv);

        if(res <= 0){
            return  false;
        }else{
            return  true;
        }
    }

    public ArrayList<CartModel> getOrders(){
        ArrayList<CartModel> orders = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from ShoppingCartt", null);

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()) {
                CartModel model = new CartModel();
                model.setName(cursor.getString(1));
                model.setQuantity(cursor.getString(2));
                model.setPrice(cursor.getString(3));
                model.setColor(cursor.getString(4));
                model.setSize(cursor.getString(5));
                model.setImage(cursor.getInt(6));
                orders.add(model);
            }
        }
        cursor.close();
        db.close();
        return orders;
    }

    public int deleteOrder(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("ShoppingCart", "id="+id, null);
    }



}
