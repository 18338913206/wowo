package com.example.rw12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBOpenHelper extends SQLiteOpenHelper{
    public static final String CREATE_BOOK = "create table book (" + "id integer primary key autoincrement, "
            + "author text, " + "price real, " + "pages integer, " + "name text)";

    public static final String CREATE_CATEGORY = "create table category (" + "id integer primary key autoincrement, "
            + "category_name text, " + "category_code integer)";
    private Context mContext;

    public MyDBOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists book");
        db.execSQL("drop table if exists category");
        onCreate(db);

    }

}