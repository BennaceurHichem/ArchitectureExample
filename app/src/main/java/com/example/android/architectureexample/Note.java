package com.example.android.architectureexample;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

// ******************* This is our Entity compeonent in Our  Room ***************//
// this annotation to change the table name
// (by default it is Note (the name of class)

@Entity(tableName = "note_table")
public class Note {

    public Note( String title, String description, int priority) {
        // the id is automatically generated
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    @PrimaryKey(autoGenerate = true)
    private int id ;

    private String title;

    private String description;
    @ColumnInfo(name = "priority")
    private int priority;

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
}
