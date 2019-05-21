package com.example.android.architectureexample;

import android.net.sip.SipSession;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {
    private List<Book> books = new ArrayList<>();
    private onItemClickListener listener;

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new BookHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        Book currentBook = books.get(position);
        holder.textViewTitle.setText(currentBook.getTitle());
        holder.textViewDescription.setText(currentBook.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentBook.getPriority()));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> Books) {
        this.books = Books;
        notifyDataSetChanged();
    }

    // To get the Adapter position( return the book where the adapter is in
    public Book getBookAt(int position) {
        return books.get(position);
    }

    class BookHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public BookHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            // wz should catch the click of ana item here to make our treatment

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int positionItem = getAdapterPosition();

                    if (listener != null && positionItem != RecyclerView.NO_POSITION) {
                        listener.onItemClick(books.get(positionItem));
                    }
                }
            });
        }
    }


    public interface onItemClickListener {

        void onItemClick(Book book);


    }

    // set Our costum clickListener in this method
    public void setOnItemClickListener(onItemClickListener listenerr) {

        this.listener = listenerr;


    }
}