package com.example.provapratica_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView VisualizarLivros;
    private Button btnAdicionarLivro;
    private DatabaseHelper databaseHelper;
    private BookAdapter adapter;
    private List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VisualizarLivros = findViewById(R.id.listViewBooks);
        btnAdicionarLivro = findViewById(R.id.btnAddBook);
        databaseHelper = new DatabaseHelper(this);

        BuscarLivros();

        btnAdicionarLivro.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            startActivity(intent);
        });

        VisualizarLivros.setOnItemClickListener((adapterView, view, position, id) -> {
            Book selectedBook = bookList.get(position);
            Intent intent = new Intent(MainActivity.this, EditBookActivity.class);
            intent.putExtra("BOOK_ID", selectedBook.getId()); // Envia pelo numero da edição
            startActivity(intent);
        });

       VisualizarLivros.setOnItemLongClickListener((adapterView, view, position, id) -> {
            Book selectedBook = bookList.get(position);
            databaseHelper.deleteBook(selectedBook.getId()); // Deleta pelo numero do livro
            BuscarLivros();
            Toast.makeText(MainActivity.this, "Livro excluído", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        BuscarLivros();
    }

    private void BuscarLivros() {
        bookList = databaseHelper.getAllBooks(); // Retorna os livros
        adapter = new BookAdapter(this, bookList);
        VisualizarLivros.setAdapter(adapter);
    }
}
