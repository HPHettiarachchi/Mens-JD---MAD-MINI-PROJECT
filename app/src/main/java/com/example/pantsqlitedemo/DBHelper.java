package com.example.schedulegenix1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Schedulegenix1.db";
    public static final String SCHEDULES_TABLE_NAME = "schedules";
    public static final String SCHEDULES_COLUMN_ID = "id";
    public static final String SCHEDULES_COLUMN_TITLE = "title";
    public static final String SCHEDULES_COLUMN_DATE = "date";
    public static final String SCHEDULES_COLUMN_STARTTIME = "starttime";
    public static final String SCHEDULES_COLUMN_ENDTIME = "endtime";
    public static final String SCHEDULES_COLUMN_VENUE = "venue";

    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACT_STORE_COLUMN_ID = "id";
    public static final String CONTACT_STORE_COLUMN_NAME = "name";
    public static final String CONTACT_STORE_COLUMN_PHONE = "phone";
    public static final String CONTACT_STORE_COLUMN_ADDRESS = "address";
    public static final String CONTACT_STORE_COLUMN_EMAIL = "email";

    public static final String PERSONAL_TABLE_NAME = "personal";
    public static final String PERSONAL_STORE_COLUMN_ID = "id";
    public static final String PERSONAL_STORE_COLUMN_NAME= "name";
    public static final String PERSONAL_STORE_COLUMN_BIRTHDAY = "birthday";
    public static final String PERSONAL_STORE_COLUMN_NIC = "nic";
    public static final String PERSONAL_STORE_COLUMN_BUSINESS_ADDRESS = "bAddress";
    public static final String PERSONAL_STORE_COLUMN_BUSINESS_PHONE_NUM = "bPhoneNum";
    public static final String PERSONAL_STORE_COLUMN_MOBILE_PASSPORT_NUM = "mPassportNum";
    public static final String PERSONAL_STORE_COLUMN_TAX_FILE_NUM = "tFileNum";
    public static final String PERSONAL_STORE_COLUMN_CAR_REG_NUM = "carRegNum";
    public static final String PERSONAL_STORE_COLUMN_BLOOD_GROUP = "bloodGroup";

    public static final String CARD_INFORMATION_TABLE_NAME = "card_information";
    public static final String CARD_INFORMATION_COLUMN_ID = "id";
    public static final String CARD_INFORMATION_COLUMN_NAME = "name";
    public static final String CARD_INFORMATION_COLUMN_TYPE = "type";
    public static final String CARD_INFORMATION_COLUMN_NUMBER = "number";
    public static final String CARD_INFORMATION_COLUMN_DATE = "date";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table contacts " +
                        "(id integer primary key, name text,phone text,address text, email text)"
        );
        db.execSQL(
                "create table schedules " +
                        "(id integer primary key, title text,date text,starttime text, endtime text,venue text)"
        );
        db.execSQL(
                "create table personal " +
                        "(id integer primary key, name text,birthday text,nic text, bAddress text,  bPhoneNum text, mPassportNum text, bloodGroup text)"
        );
        db.execSQL(
                "create table card_information " +
                        "(id integer primary key, name text,type text, number text, date text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        db.execSQL("DROP TABLE IF EXISTS schedules");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS personal");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS card_information");
        onCreate(db);
    }

    //IT19123882
    public boolean insertSchedule (String title, String date, String starttime, String endtime,String venue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("date", date);
        contentValues.put("starttime", starttime);
        contentValues.put("endtime", endtime);
        contentValues.put("venue", venue);
        db.insert("schedules", null, contentValues);
        return true;

    }
    public Cursor getScheduleData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from schedules where id="+id+"", null );
        return res;
    }
    public boolean updateSchedule (Integer id, String title, String date, String starttime, String endtime,String venue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("date", date);
        contentValues.put("starttime", starttime);
        contentValues.put("endtime", endtime);
        contentValues.put("venue", venue);
        db.update("schedules", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public Integer deleteSchedule (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("schedules","id = ? ",
                new String[] { Integer.toString(id) });
    }
    public ArrayList<String> getAllSchedules() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from schedules", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(SCHEDULES_COLUMN_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }


    //IT19127774
    public boolean insertContact (String name, String phone, String address, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        contentValues.put("email",email);
        db.insert("contacts", null, contentValues);
        return true;
    }
    public Cursor getData1(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }
    public boolean updateContact (Integer id, String name, String phone, String address , String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        contentValues.put("email", email);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    public ArrayList<String> getAllContacts() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACT_STORE_COLUMN_NAME)));
            res.moveToNext();
    }
        return array_list;
}

    //Dushi
    public boolean insertPersonal (String name, String birthday, String nic, String bAddress, String bPhoneNum, String mPassportNum, String bloodGroup) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("birthday", birthday);
        contentValues.put("nic", nic);
        contentValues.put("bAddress", bAddress);
        contentValues.put("bPhoneNum",bPhoneNum);
        contentValues.put("mPassportNum",mPassportNum);
        contentValues.put("bloodGroup", bloodGroup);
        db.insert("personal", null, contentValues);
        return true;
    }
    public Cursor getPersonalData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from personal where id="+id+"", null );
        return res;
    }
    public boolean updatePersonal (Integer id,String name, String birthday , String nic , String bAddress,  String bPhoneNum, String mPassportNum, String bloodGroup) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("birthday", birthday);
        contentValues.put("nic", nic);
        contentValues.put("bAddress", bAddress);
        contentValues.put("bPhoneNum", bPhoneNum);
        contentValues.put("mPassportNum",mPassportNum);
        contentValues.put("bloodGroup", bloodGroup);
        db.update("personal", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deletePersonal (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("personal",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    public ArrayList<String> getAllData() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from personal", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(PERSONAL_STORE_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }


    //Piyumi
    public boolean insertinformation( String name, String type, String number, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("type", type);
        contentValues.put("number", number);
        contentValues.put("date", date);
        db.insert("card_information", null, contentValues);
        return true;
    }
    public Cursor getCardData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from card_information where id="+id+"", null );
        return res;
    }
    public boolean updateinformation(Integer id, String name, String type, String number, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("type", type);
        contentValues.put("number", number);
        contentValues.put("date", date);
        db.update("card_information", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public Integer deleteInformation (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("card_information",

                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    public ArrayList<String> getAllInformation() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from card_information", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CARD_INFORMATION_COLUMN_NAME)));

            res.moveToNext();
        }
        return array_list;
    }

}

