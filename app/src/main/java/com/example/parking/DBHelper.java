package com.example.parking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String CREATE_TABLE_pricePanel="CREATE TABLE "+Constants.pricePanel_table+" ("+
            Constants.ID_column+" INTEGER PRIMARY KEY ,"+
            Constants.pricePanel_price_column+ " INTEGER ,"+
            Constants.pricePanel_hour_column+ " INTEGER )";


    public static final String CREATE_TABLE_ATT="CREATE TABLE "+Constants.ATT_table+" ("+
            Constants.ID_column+" INTEGER PRIMARY KEY ,"+
            Constants.ATT_plate_column+ " TEXT ,"+
            Constants.ATT_arrivalDateAndTime_column+ " TEXT ,"+
            Constants.ATT_ExitDateAndTime_column+ " TEXT ,"+
            Constants.ATT_price_column+ " INTEGER )";

    public DBHelper(@Nullable Context context) {
        super(context,Constants.DB_name,null,Constants.DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_pricePanel);
        sqLiteDatabase.execSQL(CREATE_TABLE_ATT);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.pricePanel_table);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.ATT_table);
        onCreate(sqLiteDatabase);
    }

    public void insertPrice(int price,int hour){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Constants.pricePanel_price_column,price);
        contentValues.put(Constants.pricePanel_hour_column,hour);

        database.insert(Constants.pricePanel_table,null,contentValues);
        database.close();
    }

    public PricePanel getPriceAndHour(){
        SQLiteDatabase database=this.getReadableDatabase();
        PricePanel pricePanel=new PricePanel();
        String query="SELECT * FROM "+Constants.pricePanel_table;
        Cursor cursor=database.rawQuery(query,null);
        if (cursor.getCount()!=0) {
            cursor.moveToFirst();
            pricePanel.setPrice(cursor.getInt(1));
            pricePanel.setHour(cursor.getInt(2));
        }
        return pricePanel;
    }


    public void updatePriceAndHour(int updatePrice,int updateHour,int searchPrice,int searchHour){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Constants.pricePanel_price_column,updatePrice);
        contentValues.put(Constants.pricePanel_hour_column,updateHour);
        database.update(Constants.pricePanel_table,contentValues,Constants.pricePanel_price_column+" = ? AND "+Constants.pricePanel_hour_column+" = ?",
                new String[]{String.valueOf(searchPrice),String.valueOf(searchHour)});
    }

    public void deletePriceAndHour(int price,int hour){
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete(Constants.pricePanel_table,Constants.pricePanel_price_column+" = ? AND "+Constants.pricePanel_hour_column+" = ?",
                new String[]{String.valueOf(price),String.valueOf(hour)});
        database.close();
    }
    public void deleteAllRows(String table_name){
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL("delete from "+ table_name);
        database.close();
    }
    public boolean isPriceTableEmpty(){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT count(*) FROM "+Constants.pricePanel_table;
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if(count>0){
            return false;
        }
        return true;
    }
    public void insertExitInfo(String plate,String dateAndTime){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Constants.ATT_ExitDateAndTime_column,dateAndTime);
        database.update(Constants.ATT_table,contentValues,Constants.ATT_plate_column+" = ? ",
                new String[]{plate});
    }
    public void insertArrivalInfo(String plate,String dateAndTime){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Constants.ATT_plate_column,plate);
        contentValues.put(Constants.ATT_arrivalDateAndTime_column,dateAndTime);
        database.insert(Constants.ATT_table,null,contentValues);
        database.close();
    }
    public String getArrivalDateAndTime(String plate){
        String arrivalDate="";
        SQLiteDatabase database=this.getReadableDatabase();
        String query="SELECT "+Constants.ATT_arrivalDateAndTime_column+ " FROM "+Constants.ATT_table + " where "+Constants.ATT_plate_column+" = '" +plate + "' ";

        Cursor cursor=database.rawQuery(query,null);
        if (cursor.getCount()!=0) {
            cursor.moveToFirst();
            arrivalDate=(cursor.getString(0));

        }
        return arrivalDate;
    }

}
