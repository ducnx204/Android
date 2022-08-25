package com.example.myapplication.Controller.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.Controller.Dao.UserDao;
import com.example.myapplication.Controller.model.User;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
