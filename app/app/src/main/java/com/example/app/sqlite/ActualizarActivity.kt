package com.example.app.sqlite

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app.R

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, author_input, pages_input;
    Button update_button; delete_button;

    String id,title,author,pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        pages_input = findViewById(R.id.pages_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);


        //Primero se llama aquí
        getAndSet IntentData ();
        //Seccion accion bar titulo
        ActionBar ab = getSupportActionBar ();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View . OnClickListener () {

            @Override
            public void onClick(View view) {

                //Después esto
                MyDatabaseHelper myDB = new MyDatabaseHelper(context: UpdateActivity.this);
                myDB.updateData(id, title, author, pages);
            }

        });

        delete button . setOnClickListener (new View . OnClickListener () {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {

        if (getIntent().hasExtra(name: "id") && getIntent().hasExtra (name: "title") && getIntent().hasExtra(name: "author") &&
        getIntent().hasExtra(name: "pages")) {

        //obtenemos datos
        id = getIntent().getStringExtra(name: "id");
        title = getIntent().getStringExtra(name: "title");
        author = getIntent().getStringExtra(name: "author");
        pages = getIntent().getStringExtra(name:"pages");
        //establecemos datos
        title_input.setText(title);
        author_input.setText(author);
        pages_input.setText(pages);
    }else{
        Toast.makeText(context: this, text: "Sin datos.", Toast.LENGTH_SHORT).show();
    }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context: this);
        builder.setTitle("Eliminar" + title + " ?");
        builder.setMessage("Esta seguro de que desea eliminar " + title + " ?");
        builder.setPositiveButton(text: "Si", new DialogInterface.OnClickListener() {

             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 MyDatabaseHelper myDB = new MyDatabaseHelper(context: UpdateActivity.this);
                 myDB.deleteOneRow(id);
                 finish();
        }
    });
        builder.setNegativeButton (text: "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
    }
        }

    );
        builder.create().show();
}

}