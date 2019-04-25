package com.example.android.architectureexample;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

// ******************* This is our Entity compeonent in Our  Room ***************//
// this annotation to change the table name
// (by default it is Book (the name of class)

@Entity(tableName = "book_table")
public class Book {


    @PrimaryKey(autoGenerate = true)
    private int id ;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    private String title;

    private String description;

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    private String categorie;

    @ColumnInfo(name="image_url")
    private int thumbnail;

    @ColumnInfo(name = "priority")
    private int priority;

    public Book(String title, String categorie,String description, int priority) {
        // the id is automatically generated
        this.title = title;
        this.description = description;

        this.categorie = categorie;
        this.priority = priority;
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

    public int getPriority() {
        return priority;
    }



    // we need only setting the ID becaue it tis auto genrated
    public void setId(int id) {
        this.id = id;
    }
    public int getThumbnail() {
        return thumbnail;
    }
}
