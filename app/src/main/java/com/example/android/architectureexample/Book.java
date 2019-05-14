package com.example.android.architectureexample;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

// ******************* This is our Entity compeonent in Our  Room ***************//
// this annotation to change the table name
// (by default it is Book (the name of class)

@Entity(tableName = "book_table")
public class Book {


    @PrimaryKey(autoGenerate = true)
    private int id ;

    private String title;

    private String description;








    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    private int priority;





    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }





    public Book(String title,String description,int priority) {
        // the id is automatically generated
        this.title = title;
        this.description = description;
        this.priority = priority;


    }
    @Ignore
    public Book()
    {
        this.title = "";
        this.description = "";


    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }





    // we need only setting the ID becaue it tis auto genrated
    public void setId(int id) {
        this.id = id;
    }



}
