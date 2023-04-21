package com.example.sqlite_th2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SongDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "song_db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "songs";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SINGER = "singer";
    public static final String COLUMN_ALBUM = "album";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_FAVORITE = "favorite";

    public SongDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_SINGER + " TEXT," +
                COLUMN_ALBUM + " TEXT," +
                COLUMN_TYPE + " TEXT," +
                COLUMN_FAVORITE + " REAL" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }
}
