package com.hrishikesh.Demo;

/**
 * Created by j on 31-08-2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "shopsInfo";
    // Contacts table name
    private static final String TABLE_SHOPS = "shops";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SH_ADDR = "shop_address";
    private static final String KEY_SH_URL = "shop_url";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SHOPS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
        + KEY_SH_ADDR + " TEXT," + KEY_SH_URL + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPS);
// Creating tables again
        onCreate(db);
    }
    // Adding new shop
    public void addShop(Shop shop) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, shop.getName()); // Shop Name
        values.put(KEY_SH_ADDR, shop.getAddress());
        values.put(KEY_SH_URL, shop.getUrl());
// Inserting Row
        db.insert(TABLE_SHOPS, null, values);
        db.close(); // Closing database connection
    }
    // Getting one shop
    public Shop getShop(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SHOPS, new String[] { KEY_ID,
                        KEY_NAME, KEY_SH_ADDR,KEY_SH_URL }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Shop contact = new Shop(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3));
// return shop
        return contact;
    }
    // Getting All Shops
    public List<Shop> getAllShops() {
        List<Shop> shopList = new ArrayList<Shop>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_SHOPS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Shop shop = new Shop();
                shop.setId(Integer.parseInt(cursor.getString(0)));
                shop.setName(cursor.getString(1));
                shop.setAddress(cursor.getString(2));
                shop.setUrl(cursor.getString(3));
// Adding contact to list
                shopList.add(shop);
            } while (cursor.moveToNext());
        }
// return contact list
        return shopList;
    }
    // Getting shops Count
    public int getShopsCount() {
        String countQuery = "SELECT * FROM " + TABLE_SHOPS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
// return count
        return cursor.getCount();
    }
}
