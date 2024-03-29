package com.example.android.architectureexample;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    private static final int EDIT_NOTE_REQUEST = 3;

    private BookViewModel bookViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddNote = findViewById(R.id.btn_add_book);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditBookActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycleview_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final BookAdapter adapter = new BookAdapter();
        recyclerView.setAdapter(adapter);

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        bookViewModel.getAllBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> notes) {

                //we changed our setBook method with submitList which is a AdapterList Method
                adapter.submitList(notes);
            }
        });

        // to my our recycler View swippable
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //viewHolder.getAdapterPosition();// give us the Adapter positon
                // if this item in the recycler View is swipped, we will delete it
                bookViewModel.delete(adapter.getBookAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Book deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        adapter.setOnItemClickListener(new BookAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                Intent intent  = new Intent(MainActivity.this, AddEditBookActivity.class);
                intent.putExtra(AddEditBookActivity.EXTRA_TITLE,book.getTitle());
                intent.putExtra(AddEditBookActivity.EXTRA_DESCRIPTION,book.getDescription());
                intent.putExtra(AddEditBookActivity.EXTRA_PRIORITY,book.getPriority());
                intent.putExtra(AddEditBookActivity.EXTRA_ID,book.getId());

                startActivityForResult(intent,EDIT_NOTE_REQUEST);


            }
        });








    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditBookActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditBookActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddEditBookActivity.EXTRA_PRIORITY, 1);

            Book note = new Book(title, description, priority);
            bookViewModel.insert(note);

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
                // when the requet is for editing an item
            int id = data.getIntExtra(AddEditBookActivity.EXTRA_ID,-1);

            if(id==-1)
            {
                Toast.makeText(this, "Not cannot be Updated", Toast.LENGTH_SHORT).show();
                        return;
            }
            else
            {
                String title = data.getStringExtra(AddEditBookActivity.EXTRA_TITLE);
                String description = data.getStringExtra(AddEditBookActivity.EXTRA_DESCRIPTION);
                int priority = data.getIntExtra(AddEditBookActivity.EXTRA_PRIORITY, 1);
                Book book = new Book(title,description,priority);
                book.setId(id);
                bookViewModel.update(book);
            }



        }
        else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId())
        {

            case R.id.delete_all_notes:
                bookViewModel.deleteAll();
                Toast.makeText(this, "All Books Are deleted", Toast.LENGTH_SHORT).show();
                return true;
                default:
                    return super.onOptionsItemSelected(item);

        }
    }
}