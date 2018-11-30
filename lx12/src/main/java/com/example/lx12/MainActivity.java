package com.example.lx12;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    private String newId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                addData();
                break;
            case R.id.btn_2:
                deleteData();
                break;
            case R.id.btn_3:
                updateData();
                break;
            case R.id.btn_4:
                queryData();
                break;

        }
    }


    private void deleteData() {
        Uri uri=Uri.parse("content://com.example.rw12.provider/book/"+newId);
        getContentResolver().delete(uri, null, null);
    }

    private void updateData() {
        Uri uri=Uri.parse("content://com.example.rw12.provider/book/"+newId);
        ContentValues cv=new ContentValues();
        cv.put("name", "罗马人的故事");
        cv.put("pages", 29876);
        cv.put("price", 300);
        getContentResolver().update(uri, cv, null, null);
    }

    private void queryData() {
        Uri uri= Uri.parse("content://com.example.rw12.provider/book");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if(cursor!=null) {
            while(cursor.moveToNext()) {
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String author=cursor.getString(cursor.getColumnIndex("author"));
                int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                double price=cursor.getDouble(cursor.getColumnIndex("price"));
                Log.i("AABBCC", "book name is"+name);
                Log.i("AABBCC","book author is "+author);
                Log.i("AABBCC","book pages is "+pages);
                Log.i("AABBCC","book price is "+price);
                Toast.makeText(this, "book name is"+name+"book author is "+author+"book pages is "+pages+"book price is "+price, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addData() {
        Uri uri=Uri.parse("content://com.example.rw12.provider/book");
        ContentValues cv=new ContentValues();
        cv.put("name", "活着");
        cv.put("author", "余华");
        cv.put("pages", 55);
        cv.put("price", 6);
        Uri newUri=getContentResolver().insert(uri, cv);
        newId=newUri.getPathSegments().get(1);
    }



}