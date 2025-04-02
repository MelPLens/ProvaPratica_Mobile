package com.example.provapratica_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;
import java.util.List;

public class BookAdapter extends BaseAdapter {
    private Context context;
    private List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() { return bookList.size(); }

    @Override
    public Object getItem(int position) { return bookList.get(position); }

    @Override
    public long getItemId(int position) { return bookList.get(position).getId(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        }

        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtAuthor = convertView.findViewById(R.id.txtAuthor);
        TextView txtCategory = convertView.findViewById(R.id.txtCategory);

        Book book = bookList.get(position);
        txtTitle.setText(book.getTitle());
        txtAuthor.setText("Autor: " + book.getAuthor());
        txtCategory.setText("Categoria: " + book.getCategory());

        return convertView;
    }
}
