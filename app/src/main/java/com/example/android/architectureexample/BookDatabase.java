package com.example.android.architectureexample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Database(entities = {Book.class}, version =2,exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {


    private static BookDatabase instance;


// we can access to our databse by using DAO methods ..§//
    public abstract BookDao bookDAO();



        //synchronized means that only one thread at the time ca    n execute this method to avoid accidentally
    public static synchronized BookDatabase getInstance(Context context )
    {
        if( instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), BookDatabase.class, "Book Databse")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        //By adding addCallback our databse will be created and populated with the creation
        }

            //when we increment the version number
        //AFTER CREATNG A DATABSE INSTANCE WE CAN FILL THE CONTENT
        // OF THE ABSTRACT METHOD bookDao() [ the creation is done with Room.databaseBuilder....


            return instance;
        }
    // in this case we can, after creating the database,
    // make an initial fill of the book_table by usingRoomDatabase.calBack()

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback()
    {

        // onCreate when the databse is created, we dn't need onOpen which is executed every db opening
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // this
            new PopulateDbAsyncTask(instance).execute();

        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>
    {

        private BookDao bookDao;
        List<Book> lstBook;
        HashMap descriptionMap;
        List<String> titles;

        public PopulateDbAsyncTask(BookDatabase bookDb) {
            // we can o that because the Db has already created
            this.bookDao = bookDb.bookDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            lstBook.add(new Book("ريح الشمال","this is the description", 1));
            lstBook.add(new Book("ريح الجنوب","this is the description", 5));
            lstBook.add(new Book("النبي صلى الله عيله وسلم ","this is the description", 2));
            lstBook.add(new Book("حديث الصباح","this is the description", 3));
            lstBook.add(new Book("حديث المساء","this is the description", 4));
            lstBook.add(new Book("المقدمة","this is the description", 1));
            lstBook.add(new Book("بستان الواعظين","this is the description", 5));
            lstBook.add(new Book("لا تسألني لماذا أحببتها","this is the description", 4));
            lstBook.add(new Book("العواصف","this is the description", 2));
            lstBook.add(new Book("ألم نشرح لك صدرك ","this is the description", 1));
            lstBook.add(new Book("وحي القلم ","this is the description", 3));
            lstBook.add(new Book("قبس من حكايا","this is the description", 2));
            lstBook.add(new Book("عبقريات العقاد","this is the description", 4));


            bookDao.insert(lstBook.get(0));
            bookDao.insert(lstBook.get(1));
            bookDao.insert(lstBook.get(2));
            bookDao.insert(lstBook.get(3));
            bookDao.insert(lstBook.get(4));
            bookDao.insert(lstBook.get(5));
            bookDao.insert(lstBook.get(7));
            bookDao.insert(lstBook.get(8));
            bookDao.insert(lstBook.get(9));
            bookDao.insert(lstBook.get(10));
            bookDao.insert(lstBook.get(11));
            bookDao.insert(lstBook.get(12));

            return null;
        }
    }

}
