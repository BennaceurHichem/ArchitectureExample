package com.example.android.architectureexample;




//   A WAY TO ABSTRACT DATA all data source operations
//PROVIDE CLEAN API TO THE REST OF THE APP (View model ...)

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

//View Model will treat and manipulate data by using Repository
// only(which will access to the room)
public class BookRepository {


    private BookDao bookDao;
    private LiveData<List<Book>> allBooks;


    public BookRepository(Application application)
    {
            BookDatabase database = BookDatabase.getInstance(application);
            bookDao = database.bookDAO(); //ABSTRACT METHOD
            allBooks = bookDao.selectAll();
    }


// ROOM DOESN'T ALLOW DATABASE OPERATION ON THE MAIN THREAD so we should use AsyncTask to execute our databse operation
    //in this threads
    public void insert(Book book )
    {
            new InsertNoteAsyncTask(bookDao).execute(book);
    }
    public void update(Book book)
    {
        new UpdateAsyncTask(bookDao).execute(book);
    }
    public void delete(Book book)
    {
        new DeleteAsyncTask(bookDao).execute(book);
    }
    public void deleteAll()
    {
        new DeleteAllAsyncTask(bookDao).execute();

    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }



    private static class InsertNoteAsyncTask extends AsyncTask<Book,Void,Void>
        {
            private BookDao bookDao;

            public InsertNoteAsyncTask(BookDao bookDao) {
                this.bookDao = bookDao;
            }





            @Override
            protected Void doInBackground(Book... books) {

                bookDao.insert(books[0]);
                return null;
            }
        }


        private static class UpdateAsyncTask extends AsyncTask<Book,Void,Void>
        {
            public BookDao bookDao;


            public UpdateAsyncTask(BookDao books)
            {
                this.bookDao = books;
            }

            @Override
            protected Void doInBackground(Book... books) {

                bookDao.update(books[0]);
                return null;
            }
        }

    private static class DeleteAsyncTask extends AsyncTask<Book,Void,Void>
    {
        public BookDao bookDao;


        public DeleteAsyncTask(BookDao books)
        {
            this.bookDao = books;
        }

        @Override
        protected Void doInBackground(Book... books) {

            bookDao.delete(books[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Book,Void,Void>
    {
        public BookDao bookDao;


        public DeleteAllAsyncTask(BookDao books)
        {
            this.bookDao = books;
        }

        @Override
        protected Void doInBackground(Book... books) {

            bookDao.deleteAllBooks();
            return null;
        }
    }


























}
