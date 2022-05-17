package com.example.sql_lite_app.sqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.sql_lite_app.sqlLite.tables.NotesTable;

import java.util.Date;

public class MyDataHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "Notes.db";
    public static final int DATABASE_VERSION = 1;

    public MyDataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String creationQuery =
                "CREATE TABLE " +
                        NotesTable.TABLE_NAME + " (" +
                        NotesTable.COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        NotesTable.COLUMN_TITLE +" TEXT, " +
                        NotesTable.COLUMN_DESCRIPTION +" TEXT, "+
                        NotesTable.COLUMN_TAG +" TEXT, "+
                        NotesTable.COLUMN_CREATION_DATE +" TEXT);";

        sqLiteDatabase.execSQL(creationQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NotesTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addNote(String title, String tag, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NotesTable.COLUMN_TITLE,title);
        contentValues.put(NotesTable.COLUMN_TAG,tag);
        contentValues.put(NotesTable.COLUMN_DESCRIPTION,description);
        contentValues.put(NotesTable.COLUMN_CREATION_DATE,(new Date()).toString());

        long result = db.insert(NotesTable.TABLE_NAME, null, contentValues);

        if(result == -1){
            Toast.makeText(this.context, "Failed to create a note", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this.context, "Successfully created", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllNotes(){
        SQLiteDatabase db = this.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                NotesTable.COLUMN_TITLE,
                NotesTable.COLUMN_TAG,
                NotesTable.COLUMN_DESCRIPTION,
                NotesTable.COLUMN_CREATION_DATE
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                NotesTable.COLUMN_CREATION_DATE + " DESC";

        Cursor cursor = db.query(
                NotesTable.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        return cursor;
    }

    public void updateNote(int row_id, String title, String tag, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NotesTable.COLUMN_TITLE, title);
        cv.put(NotesTable.COLUMN_TAG, tag);
        cv.put(NotesTable.COLUMN_DESCRIPTION, description);

        long result = db.update(NotesTable.TABLE_NAME, cv, "_id=?", new String[]{String.valueOf(row_id)});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteNote(int note_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(NotesTable.TABLE_NAME, "_id=?", new String[]{String.valueOf(note_id)});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
