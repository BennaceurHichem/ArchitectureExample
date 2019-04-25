package com.example.android.architectureexample;


// DAO is ana interface of abstract class because the Room will generate all the content of these methods
// recommmendation : One DAO per Entity


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

public interface BookDao {


    // we juyst defined the annotaion to specify to the room what should be the content
    @Insert
    void insert(List<Book> book);

    @Update
    void update(Book book);
// wa can pass a list of Notes ...//
    @Delete
    void delete(Book book);



    // Query annotation to create our query//
     @Query("DELETE  FROM book_table")
    void deleteAllBooks();

     @Query("SELECT * FROM book_table ORDER BY priority DESC")
        // LiveData: we can observe this object , any modification ==> Our activity will be notified
     LiveData<List<Book>> selectAll();







}
