package com.example.provapratica_mobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBookActivity extends Activity {
    private EditText edtTitle, edtAuthor, edtCategory;
    private Button btnSave;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        edtTitle = findViewById(R.id.edtTitle);
        edtAuthor = findViewById(R.id.edtAuthor);
        edtCategory = findViewById(R.id.edtCategory);
        btnSave = findViewById(R.id.btnSave);
        databaseHelper = new DatabaseHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString().trim();
                String author = edtAuthor.getText().toString().trim();
                String category = edtCategory.getText().toString().trim();

                if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
                    Toast.makeText(AddBookActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseHelper.addBook(new Book(0, title, author, category, false));
                Toast.makeText(AddBookActivity.this, "Livro adicionado!", Toast.LENGTH_SHORT).show();
                finish(); // Fecha a tela e volta para a MainActivity
            }
        });
    }
}
