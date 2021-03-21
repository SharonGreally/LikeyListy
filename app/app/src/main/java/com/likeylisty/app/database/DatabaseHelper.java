package com.likeylisty.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private ContentValues cv;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseDetails.Database.DATABASE_NAME, null, DatabaseDetails.Database.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ITEM_TABLE = "CREATE TABLE IF NOT EXISTS " +
                DatabaseDetails.Item.TABLE_NAME + " (" +
                DatabaseDetails.Item._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseDetails.Item.COLUMN_ITEM_TEXT + " TEXT NOT NULL, " +
                DatabaseDetails.Item.COLUMN_ITEM_STATUS + " INT," +
                DatabaseDetails.Item.COLUMN_ITEM_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + DatabaseDetails.Item.TABLE_NAME;

        db.execSQL(SQL_DROP_TABLE);
    }

    public void createItem(String itemText) {
        System.out.println("----------dbhelper " + itemText);
        if (itemText.trim().length() == 0) {
            return;
        }

        db = this.getWritableDatabase();
        cv = new ContentValues();
        cv.put(DatabaseDetails.Item.COLUMN_ITEM_TEXT, itemText);
        cv.put(DatabaseDetails.Item.COLUMN_ITEM_STATUS, 0);

        db.insert(DatabaseDetails.Item.TABLE_NAME, null, cv);
    }

    public void updateItem(int itemId, String newItemText) {
        db = this.getWritableDatabase();
        cv = new ContentValues();
        cv.put(DatabaseDetails.Item.COLUMN_ITEM_TEXT, newItemText);
        db.update(DatabaseDetails.Item.TABLE_NAME, cv, DatabaseDetails.Item.COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemId)});
    }

    public void deleteItem(int itemId) {
        db = this.getWritableDatabase();
        db.delete(DatabaseDetails.Item.TABLE_NAME, DatabaseDetails.Item.COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemId)});
    }

    public void updateItemStatus(int itemId, int itemStatus) {
        int newItemStatus;
        if (itemStatus == 0) {
            newItemStatus = 1;
        } else {
            newItemStatus = 0;
        }

        db = this.getWritableDatabase();
        cv = new ContentValues();
        cv.put(DatabaseDetails.Item.COLUMN_ITEM_STATUS, newItemStatus);
        db.update(DatabaseDetails.Item.TABLE_NAME, cv, DatabaseDetails.Item.COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemId)});
    }

//    public List<Item> getAllItems() {
//        db = this.getWritableDatabase();
//
//        List<Item> itemList = new ArrayList<>();
//        db.beginTransaction();
//        try (Cursor cursor = db.query(false, TABLE_NAME, null, null, null, null, null, null, null, null)) {
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    do {
//                        Item item = new Item();
//                        item.setListItemId(cursor.getInt(cursor.getColumnIndex(COL_1)));
//                        item.setListItemText(cursor.getString(cursor.getColumnIndex(COL_2)));
//                        item.setListItemStatus(cursor.getInt(cursor.getColumnIndex(COL_3)));
//                        itemList.add(item);
//                    }
//                    while (cursor.moveToNext());
//                }
//            }
//        } finally {
//            db.endTransaction();
//        }
//
//        System.out.println("------------------------" + itemList);
//
//        return itemList;
//    }
}
