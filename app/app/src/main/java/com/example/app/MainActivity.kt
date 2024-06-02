package com.example.app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity extends AppCompatActivity() {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_datos;

    MyDatabaseHelper myDB;
    ArrayList<String> book id, book title, book author,book pages;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.recyclerView);
        no_datos = findViewById(R.id.no_datos);
        add_button.setOnClickListener(new View.OnClickListener((view) {
           Intent intent = new Intent(MainActivity.this,AddActivity.class);
            startActivity(intent);

        });

        myDB = new MyDatabaseHelper (context: MainActivity.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter (context: MainActivity.this, book_id, book_title, book_author, book_pages);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager (new LinearLayoutManager(context: MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }


        void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            no_datos.setVisibility(View.VISIBLE)

        }else{
            while (cursor.moveToNext()) {

            book_id.add(cursor.getString(0));
            book_title.add(cursor.getString(1));
            book_author.add(cursor.getString(2));
            book_pages.add(cursor.getString(3));
            }
            empty_imageview.setVisibility(View.GONE);
            no_datos.setVisibility(View.GONE)

        }

    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() = R.id.delete_all) {
            confirmDialog()
        }
        return super.onOptionsItemSelected(item);

    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context: this);
        builder.setTitle("Eliminar" + title + " ?");
        builder.setMessage("Esta seguro de que desea eliminar todos los datos ? ");
        builder.setPositiveButton(text: "Si", new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            MyDatabaseHelper myDB = new MyDatabaseHelper (context:MainActivity.this);
            myDB.deleteAllData();
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
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


}