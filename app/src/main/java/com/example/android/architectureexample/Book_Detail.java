package com.example.android.architectureexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class Book_Detail extends AppCompatActivity {

    private TextView title ;
    private TextView category;
    private TextView description;
    private ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__detail);


        title =findViewById(R.id.book_title);
        category = findViewById(R.id.book_categorie);
        description = findViewById(R.id.book_description);
        img = findViewById(R.id.book_image);

        Intent getMyIntent = getIntent();
        String bookTitle = getMyIntent.getExtras().getString("BookTitle");
        String bookDescription = getMyIntent.getExtras().getString("BookDescription");
        String bookCategory = getMyIntent.getExtras().getString("BookCategory");
        int thumbnail  = getMyIntent.getExtras().getInt("BookImage");

        title.setText(bookTitle);
        category.setText(bookCategory);
        description.setText(bookDescription);
        img.setImageResource(thumbnail);



    }
}
