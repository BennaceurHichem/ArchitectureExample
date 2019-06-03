package com.example.android.architectureexample;

import android.net.sip.SipSession;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends ListAdapter<Book, BookAdapter.BookHolder> {
    private onItemClickListener listener;

    protected BookAdapter() {
        super(DIFF_CALLBACK);
    }



    private static final DiffUtil.ItemCallback<Book> DIFF_CALLBACK =  new DiffUtil.ItemCallback<Book>() {
    @Override
    public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
        //return true if 2 Items are the same

        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Book oldbook, @NonNull Book newBook) {

        // return true if the item content is exactly the smae(title,description anad id ...)
        // WARNING: but obj.equals(obj2) alwaaays return false because these are 2 deiferent objects so we should compare each attribute
        return oldbook.getTitle().equals(newBook.getTitle()) && oldbook.getDescription().equals(newBook.getDescription()) && oldbook.getPriority() == newBook.getPriority();
    }
};

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new BookHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        Book currentBook = getItem(position);
        holder.textViewTitle.setText(currentBook.getTitle());
        holder.textViewDescription.setText(currentBook.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentBook.getPriority()));
    }



    // To get the Adapter position( return the book where the adapter is in
    public Book getBookAt(int position) {
        return getItem(position);
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
                        listener.onItemClick(getItem(positionItem));
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