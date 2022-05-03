package com.example.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper
{

    // Polubenskiy Lab06 - Database SQLite

    public Database(@Nullable Context context, @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE my_test (my_key Text, my_value Text);";
        db.execSQL(sql);    // run query
    }

    public void do_insert(String key, String value)
    {
        // insert new row into table
        String sql = "INSERT INTO my_test VALUES('" + key + "', '" + value + "');";
        SQLiteDatabase db = getWritableDatabase();  // get ready to write into database
        db.execSQL(sql);    // run query
    }

    public String do_select(String key)
    {
        String sql = "SELECT my_value FROM my_test WHERE my_key = '" + key + "';";
        // get ready to read from database
        SQLiteDatabase db = getReadableDatabase();
        // run query and acquiry results in a new "table" with only one column
        Cursor cur = db.rawQuery(sql, null);
        // jump to the first (and the only one) matching record, if possible
        if (cur.moveToFirst() == true)
            // return value from the first column (my_value)
            return cur.getString(0);
        return "Unable to find the key to view!";
    }

    public String do_update(String key, String value)
    {
        String sql = "UPDATE my_test SET my_value = ' " + value + "' WHERE my_key = '"+ key +"';"; 
        SQLiteDatabase db = getWritableDatabase();//get ready to read from database
        db.execSQL(sql);
        String checkRecord = "SELECT my_value FROM my_test WHERE my_key = '" + key + "';";
        db = getReadableDatabase();  // get ready to read from database
        Cursor cur = db.rawQuery(checkRecord, null);
        if (cur.getCount() <= 0)
             return "Unable to find the key to update!";
        return "The record was successfully update";
    }

    public String do_delete(String key)
    {
        SQLiteDatabase db = getWritableDatabase();//get ready to read from database
        int delCount = db.delete("my_test", key, null);    // delete по id
        if (delCount == 1)
            return "The record was successfully deleted";
        else
           return "Unable to find the key to delete!";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
