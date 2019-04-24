package com.example.android.architectureexample;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;



@Database(entities = {Note.class}, version =1)
public abstract class NoteDatabase extends RoomDatabase {


    private static NoteDatabase instance;


// we can access to our databse by using DAO methods ..§//
    public abstract NoteDAO noteDAO();



        //synchronized means that only one thread at the time ca    n execute this method to avoid accidentally
    public static synchronized NoteDatabase getInstance(Context context )
    {
        if( instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "Note Databse")
                    .fallbackToDestructiveMigration()
                    .build();
        }

            //when we increment the version number

            return instance;
        }

}
