package com.example.sqlite_th2;

import java.io.Serializable;

public class Song implements Serializable {
    private static int lastId = 0;
    private int id;
    private String name;
    private String singer;
    private String album;
    private String type;
    private boolean favorite;

    public Song(String name, String singer, String album, String type, boolean favorite) {
        lastId++;
        this.id = lastId;
        this.name = name;
        this.singer = singer;
        this.album = album;
        this.type = type;
        this.favorite = favorite;
    }

    public Song(int id, String name, String singer, String album, String type, boolean favorite) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.album = album;
        this.type = type;
        this.favorite = favorite;
    }

    public Song() {
        lastId++;
        this.id = lastId;
    }

    public static void setLastId(int lastId) {
        Song.lastId = lastId;
    }


    public static int getLastId() {
        return lastId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name='" + name + '\'' + ", singer='" + singer + '\'' + ", album='" + album + '\'' + ", type='" + type + '\'' + ", favorite=" + favorite + '}';
    }
}
