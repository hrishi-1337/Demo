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
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SHOPS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
        + KEY_SH_ADDR + " TEXT," + KEY_SH_URL + " TEXT," +LAT + " TEXT," + LNG + " TEXT" + ")";
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
        values.put(LAT, shop.getLat());
        values.put(LNG, shop.getLng());
// Inserting Row
        db.insert(TABLE_SHOPS, null, values);
        db.close(); // Closing database connection
    }
    // Getting one shop
    public Shop getShop(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SHOPS, new String[] { KEY_ID,
                        KEY_NAME, KEY_SH_ADDR,KEY_SH_URL,LAT,LNG }, KEY_NAME + "=?",new String[] { String.valueOf(name) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Shop contact = new Shop(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getDouble(4),cursor.getDouble(5));
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
                shop.setLat(cursor.getDouble(4));
                shop.setLng(cursor.getDouble(5));
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
    public List<Shop> getShops(double lat, double lng) {
        List<Shop> shopList = new ArrayList<Shop>();
        double distanceConstraint = 50.00;
// Select All Query
        //String selectQuery = "SELECT * FROM " + TABLE_SHOPS;
       /* String selectQuery = "SELECT id, name, shop_address, shop_url, lat, lng,(3959 " +
                "* acos( cos( radians(lat) )* cos( radians(" + lat + ") ) * cos( radians(" + lng + ") " +
                "- radians(lng) ) + sin( radians(lat) ) * sin(radians(" + lat + ")) ) ) " +
                "AS distance FROM " + TABLE_SHOPS + " HAVING distance < " + distanceConstraint +
                " ORDER BY distance LIMIT 0, 50"; */
        //String selectQuery ="SELECT id, name, shop_address, shop_url,( ("+lng+" - lng)*("+lng+" - lng) + ("+lat+" - lat)*("+lat+" - lat_val) ) FROM" + TABLE_SHOPS;
        //Location.distanceBetween(lat, lng,shop.setLat(cursor.getDouble(4)),shop.setLng(cursor.getDouble(5)), results);
        String selectQuery = "SELECT * FROM " + TABLE_SHOPS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Shop shop = new Shop();
                if(meterDistanceBetweenPoints(lat,lng,cursor.getDouble(4),cursor.getDouble(5)) <= 5000){
                    shop.setId(Integer.parseInt(cursor.getString(0)));
                    shop.setName(cursor.getString(1));
                    shop.setAddress(cursor.getString(2));
                    shop.setUrl(cursor.getString(3));
                    shop.setLat(cursor.getDouble(4));
                    shop.setLng(cursor.getDouble(5));
                    // Adding contact to list
                    shopList.add(shop);
                }
            } while (cursor.moveToNext());
        }
// return contact list
        return shopList;
    }

    private double meterDistanceBetweenPoints(double lat_a, double lng_a, double lat_b, double lng_b) {
        double pk = (double) (180.f/Math.PI);

        double a1 = lat_a / pk;
        double a2 = lng_a / pk;
        double b1 = lat_b / pk;
        double b2 = lng_b / pk;

        double t1 = Math.cos(a1)*Math.cos(a2)*Math.cos(b1)*Math.cos(b2);
        double t2 = Math.cos(a1)*Math.sin(a2)*Math.cos(b1)*Math.sin(b2);
        double t3 = Math.sin(a1)*Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);
        return 6366000*tt;
    }
    public List<Shop> getSearch(String name) {
        List<Shop> shopList = new ArrayList<Shop>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_SHOPS +" WHERE "+KEY_NAME+" LIKE '%"+name+"%' ORDER BY CASE WHEN "+KEY_NAME+" LIKE '"+name+"%' THEN 1 WHEN "+KEY_NAME+" LIKE '%"+name+"' THEN 3 ELSE 2 END";
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
                shop.setLat(cursor.getDouble(4));
                shop.setLng(cursor.getDouble(5));
// Adding contact to list
                shopList.add(shop);
            } while (cursor.moveToNext());
        }
// return contact list
        return shopList;
    }
}
