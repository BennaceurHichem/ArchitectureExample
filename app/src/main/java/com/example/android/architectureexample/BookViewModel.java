package com.example.android.architectureexample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class BookViewModel extends AndroidViewModel {


    // We should never store a context of an activity or a View that rference an  an activity n the
    // in the ViewModel because it causes a Memory Leak when we instantiate a destroyed activity reference

    //Instantiate a repository
    private BookRepository repository;

    private LiveData<List<Book>> allBooks;




    public BookViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepository(application); //pass the context

        allBooks = repository.getAllBooks();
    }


    public void insert(Book book)
    {
        repository.insert(book);
    }

    public void update(Book book)
    {
        repository.update(book);
    }

    public void delete(Book book)
    {
        repository.delete(book);
    }

    public void deleteAll()
    {
        repository.deleteAll();
    }
    public LiveData<List<Book>> getAllBooks()
    {
        return repository.getAllBooks();
    }
}
