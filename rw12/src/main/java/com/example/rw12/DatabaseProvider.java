package com.example.rw12;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider{
    public static final int BOOK_DIR=0;
    public static final int BOOK_ITEM=1;
    public static final int CATEGORY_DIR=2;
    public static final int CATEGORY_ITEM=3;
    public static final String AUTHORIT="com.example.rw12.provider";

    private static UriMatcher uriMatcher;
    private MyDBOpenHelper dbHelper;
    static {
        //添加要操作的URI
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORIT, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORIT, "book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORIT, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORIT, "category/#", CATEGORY_ITEM);
    }
    @Override
    public boolean onCreate() {
        dbHelper=new MyDBOpenHelper(getContext(), "BookStore.db", null, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                //查询数据
                cursor=db.query("book", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITEM:
                //获取图书id
                String bookId=uri.getPathSegments().get(1);
                cursor=db.query("book", projection, "id=?", new String[] {bookId}, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor=db.query("category", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId=uri.getPathSegments().get(1);
                cursor=db.query("category", projection, "id=?", new String[] {categoryId}, null, null, sortOrder);
                break;

        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.rw12.provider.book";

            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.rw12.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.rw12.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.rw12.provider.category";

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Uri uriReturn=null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("book", null, values);
                uriReturn=Uri.parse("content://"+AUTHORIT+"/book/"+newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long insert = db.insert("category", null, values);
                uriReturn=Uri.parse("content://"+AUTHORIT+"/category/"+insert);
                break;
        }
        return uriReturn;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        int deleteRows=0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                deleteRows = db.delete("book", selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId=uri.getPathSegments().get(1);
                deleteRows=db.delete("book", "id =?", new String[] {bookId});
                break;

            case CATEGORY_DIR:
                deleteRows=db.delete("category", selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId=uri.getPathSegments().get(1);
                deleteRows=db.delete("category", "id=?", new String[] {categoryId});
                break;

        }
        return deleteRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        int updateRows=0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                updateRows = db.update("book", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId=uri.getPathSegments().get(1);
                updateRows=db.update("book",values, "id =?", new String[] {bookId});
                break;

            case CATEGORY_DIR:
                updateRows=db.update("category",values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId=uri.getPathSegments().get(1);
                updateRows=db.update("category",values, "id=?", new String[] {categoryId});
                break;

        }
        return updateRows;
    }

}