package com.example.provapratica_mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class EditBookActivity extends Activity {
    private EditText edtTitle, edtAuthor, edtCategory;
    private CheckBox chkRead;
    private Button btnUpdate, btnDelete;
    private DatabaseHelper databaseHelper;
    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        edtTitle = findViewById(R.id.edtTitle);
        edtAuthor = findViewById(R.id.edtAuthor);
        edtCategory = findViewById(R.id.edtCategory);
        chkRead = findViewById(R.id.chkRead);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        databaseHelper = new DatabaseHelper(this);

        // Pegando numero do livro enviado pela MainActivity
        bookId = getIntent().getIntExtra("BOOK_ID", -1);
        if (bookId == -1) {
            Toast.makeText(this, "Erro ao carregar livro!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // pesquisando o livro no banco
        for (Book book : databaseHelper.getAllBooks()) {
            if (book.getId() == bookId) {
                edtTitle.setText(book.getTitle());
                edtAuthor.setText(book.getAuthor());
                edtCategory.setText(book.getCategory());
                chkRead.setChecked(book.isRead());
                break;
            }
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString().trim();
                String author = edtAuthor.getText().toString().trim();
                String category = edtCategory.getText().toString().trim();
                boolean isRead = chkRead.isChecked();

                if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
                    Toast.makeText(EditBookActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseHelper.updateBook(new Book(bookId, title, author, category, isRead));
                Toast.makeText(EditBookActivity.this, "Livro atualizado!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteBook(bookId);
                Toast.makeText(EditBookActivity.this, "Livro excluído!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
