package com.example.scannerqr.DATABASE;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.scannerqr.DAO.CodeDAO;
import com.example.scannerqr.DTO.CodeDTO;

@Database(entities = {CodeDTO.class},version = 1)
public abstract class CodeDatabase extends RoomDatabase {
    private static final String NAME = "db_code";
    public static CodeDatabase instance;

    public static synchronized CodeDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, CodeDatabase.class,NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract CodeDAO codeDAO();
}
