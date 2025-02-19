package com.example.app.sqlite;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper{
    private Context context;
    private static final String DATABASE_NAME ="bibliotecalibros.db"
    private static final String DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "mi_biblioteca";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE= "titulo_libro";
    private static final String COLUMN_AUTHOR="autor_libro";
    private static final String COLUMN_PAGES="paginas_libro";

    MyDatabaseHelper(@Nullable Context context{
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
}

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE" + TABLE_NAME +
                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_AUTHOR + " TEXT, " +
                        COLUMN_PAGES + " INTEGER) ; ";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i,int i1){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }
    void addBook(String title,String author,int pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_AUTHOR,author);
        cv.put(COLUMN_PAGES,pages);

        long result = db.insert(TABLE_NAME,null ,cv);
        if (result == -1){
            Toast.makeText(context," Error ",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"¡ Se agregó correctamente !",Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData() {
        String query "SELECT FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor null;
        if (db != null) {
            cursor = db.rawQuery(query, selectionArgs null);
        }
        return cursor;
    }

    void updateDate(String row_id, String title, String author, String pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);

        long result = db.update(TABLE_NAME, CV, whereClause:" id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, text:" Ocurrió un error al actualizar ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, text:"¡ Se actualizó correctamente !", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow (String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, whereClause: "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, text:" Ocurrió un error al eliminar ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, text: "¡ Se eliminó correctamente !", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM" + TABLE_NAME);

    }

}
