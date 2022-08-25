package com.example.myapplication.Controller.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseImp {
    public static AppDatabase db;
    public static AppDatabase getInstance(Context context){
        if(db == null){
            db = Room.databaseBuilder(context,AppDatabase.class,"database").build();
        }
        return db;
    }
}
