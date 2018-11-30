package com.example.rw12;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    private MyDBOpenHelper dbHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        dbHelper=new MyDBOpenHelper(this,"BookStore.db", null, 1);
        findViewById(R.id.add_data).setOnClickListener(this);
        findViewById(R.id.delete_data).setOnClickListener(this);
        findViewById(R.id.update_data).setOnClickListener(this);
        findViewById(R.id.select_data).setOnClickListener(this);
        findViewById(R.id.create_database).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_database:
                db=dbHelper.getWritableDatabase();
                break;
            case R.id.add_data:
                add();
                break;
            case R.id.delete_data:
                delete();
                break;
            case R.id.update_data:
                update();
                break;
            case R.id.select_data:
                select();
                break;
        }
    }

    private void select() {
        // 查询Book表中所有的数据
        Cursor cursor = db.query("book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象，取出数据并打印
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                Log.i("AABBCC", "书名 " + name);
                Log.i("AABBCC", "作者 " + author);
                Log.i("AABBCC", "页数 " + pages);
                Log.i("AABBCC", "价格 " + price);
                Toast.makeText(this, "书名 " + name+"作者 " + author+"页数 " + pages+"价格 " + price, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void update() {
        ContentValues values = new ContentValues();
        values.put("price", 10.99);
        db.update("book", values, "name = ?", new String[] { "Android应用开发" });
    }

    private void delete() {
        db.delete("book", "pages > ?", new String[] { "500" });
    }

    private void add() {
        ContentValues values = new ContentValues();
        // 开始组装第一条数据
        values.put("name", "Android应用开发");
        values.put("author", "史浩岚");
        values.put("pages", 346);
        values.put("price", 65);
        db.insert("book", null, values); // 插入第一条数据
        values.clear();
        // 开始组装第二条数据
        values.put("name", "Java应用开发");
        values.put("author", "龙");
        values.put("pages", 300);
        values.put("price", 19.95);
        db.insert("Book", null, values); // 插入第二条数据
    }



}