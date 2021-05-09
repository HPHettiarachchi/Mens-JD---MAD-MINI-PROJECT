package com.example.jeansshoppingcart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Jeans.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table FeedBackTable(name TEXT PRIMARY KEY , feedback TEXT NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists FeedBackTable");
    }

    public Boolean insertFeedbackData(String name, String feedback){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("feedback",feedback);
        long result = db.insert("FeedBackTable", null, contentValues);

        if(result ==-1){
            return  false;
        }else{
            return  true;
        }
    }

    public Cursor getFeedback(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from FeedBackTable ", null);
        return cursor;
    }
}
