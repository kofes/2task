package com.a2task.a2task.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 333da on 17.07.2016.
 */
public class DataBaseNote extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "2taskDb";
    public static final String TABLE_TASK = "task";

    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DATE = "date";
    public static final String KEY_DOIT = "doit";
    public static final String KEY_TYPE = "type";
    public static final String KEY_NOTE = "note";


    public DataBaseNote(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_TASK + "(" +
                KEY_ID + " integer primary key, " +
                KEY_TITLE + " text, " +
                KEY_NOTE + " text, " +
                KEY_DATE + " text, " +
                KEY_TYPE + " text, " +
                KEY_DOIT + " bool" + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist" + TABLE_TASK);
        onCreate(db);
    }
}
