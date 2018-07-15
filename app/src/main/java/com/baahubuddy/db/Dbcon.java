package com.baahubuddy.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Dbcon {


    String TAG = Dbcon.class.getName();
    public static Context context;
    public static SQLiteDatabase sqLiteDatabase = null;

    public Dbcon(Context context) {
        this.context = context;
    }

    public static void init() {
        try {
            sqLiteDatabase = context.openOrCreateDatabase("baahubuddy",
                    SQLiteDatabase.OPEN_READWRITE, null);
            createTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTables(){
        String sqlLogin = "create table if not exists login(username varchar(100),password varchar(100),pin varchar(100),name varchar(100),pic varchar(100))";
        sqLiteDatabase.execSQL(sqlLogin);
        String sqlCustomer = "create table if not exists customer(id varchar(100),name varchar(100),phone varchar(100),photo varchar(100),address varchar(100))";
        sqLiteDatabase.execSQL(sqlCustomer);
    }

    public static int putData(String sql) {
        int status = 0;
        try {
            sqLiteDatabase.execSQL(sql);
            status++;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static Cursor getData(String sql) {
        Cursor cu = null;
        try {
            cu = sqLiteDatabase.rawQuery(sql, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cu;
    }

    public void close() {
        if (sqLiteDatabase != null) sqLiteDatabase.close();
    }

}
