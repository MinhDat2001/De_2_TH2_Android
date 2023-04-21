package com.example.sqlite_th2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SongDAO {
    private SQLiteDatabase db;
    private final SongDatabaseHelper dbHelper;

    public SongDAO(Context context) {
        dbHelper = new SongDatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public int getMaxId() {
        open();
        int maxId = 0;

        Cursor cursor = db.rawQuery("SELECT MAX(id) FROM " + SongDatabaseHelper.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            maxId = cursor.getInt(0);
        }
        cursor.close();
        close();

        return maxId;
    }


    public void addBook(Song song) {
        open();
        ContentValues values = new ContentValues();
        values.put(SongDatabaseHelper.COLUMN_ID, song.getId());
        values.put(SongDatabaseHelper.COLUMN_NAME, song.getName());
        values.put(SongDatabaseHelper.COLUMN_SINGER, song.getSinger());
        values.put(SongDatabaseHelper.COLUMN_ALBUM, song.getAlbum());
        values.put(SongDatabaseHelper.COLUMN_TYPE, song.getType());
        values.put(SongDatabaseHelper.COLUMN_FAVORITE, song.isFavorite());

        db.insert(SongDatabaseHelper.TABLE_NAME, null, values);
        close();
    }

    public void updateBook(Song song) {
        open();
        ContentValues values = new ContentValues();
        values.put(SongDatabaseHelper.COLUMN_NAME, song.getName());
        values.put(SongDatabaseHelper.COLUMN_SINGER, song.getSinger());
        values.put(SongDatabaseHelper.COLUMN_ALBUM, song.getAlbum());
        values.put(SongDatabaseHelper.COLUMN_TYPE, song.getType());
        values.put(SongDatabaseHelper.COLUMN_FAVORITE, song.isFavorite());

        db.update(SongDatabaseHelper.TABLE_NAME, values, SongDatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(song.getId())});
        close();
    }

    public void deleteBook(Song song) {
        open();
        db.delete(SongDatabaseHelper.TABLE_NAME, SongDatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(song.getId())});
        close();
    }

    public List<Song> getAllBooks() {
        open();
        List<Song> songList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + SongDatabaseHelper.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String singer = cursor.getString(2);
                String album = cursor.getString(3);
                String type = cursor.getString(4);
                Song song = new Song(id, name, singer, album, type, true);
                songList.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return songList;
    }


    public List<Song> searchByPublisher(String pub) {
        open();
        List<Song> songs = new ArrayList<>();

        String[] columns = {SongDatabaseHelper.COLUMN_ID, SongDatabaseHelper.COLUMN_NAME, SongDatabaseHelper.COLUMN_SINGER, SongDatabaseHelper.COLUMN_ALBUM, SongDatabaseHelper.COLUMN_TYPE, SongDatabaseHelper.COLUMN_FAVORITE};
        String selection = SongDatabaseHelper.COLUMN_TYPE + "=?";
        String[] selectionArgs = {pub};

        Cursor cursor = db.query(SongDatabaseHelper.TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String singer = cursor.getString(2);
                String album = cursor.getString(3);
                String type = cursor.getString(4);

                Song song = new Song(id, name, singer, album, type, true);
                songs.add(song);
            } while (cursor.moveToNext());
        }

        cursor.close();
        close();
        return songs;
    }


}
