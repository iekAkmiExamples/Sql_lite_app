package com.example.sql_lite_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sql_lite_app.adapters.NotesAdapter;
import com.example.sql_lite_app.sqlLite.MyDataHelper;
import com.example.sql_lite_app.sqlLite.models.Notes;
import com.example.sql_lite_app.sqlLite.tables.NotesTable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDataHelper dbHelper;
    private FloatingActionButton addButton, refreshButton;
    private RecyclerView list;
    private ArrayList<Notes> notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ui
        this.list = findViewById(R.id.recyclerView);
        this.addButton = findViewById(R.id.addButton);
        this.refreshButton = findViewById(R.id.refreshButton);
        this.notes = new ArrayList<>();
        //db
        dbHelper = new MyDataHelper(MainActivity.this);
        Cursor cursor = dbHelper.readAllNotes();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(NotesTable._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(NotesTable.COLUMN_TITLE));
            String desc = cursor.getString(cursor.getColumnIndexOrThrow(NotesTable.COLUMN_DESCRIPTION));
            String tag = cursor.getString(cursor.getColumnIndexOrThrow(NotesTable.COLUMN_TAG));
            String creationDate = cursor.getString(cursor.getColumnIndexOrThrow(NotesTable.COLUMN_CREATION_DATE));

            Notes note = new Notes(id,title,desc,tag,creationDate);
            if(note != null)
                notes.add(note);
        }

        cursor.close();

        NotesAdapter adapter = new NotesAdapter(MainActivity.this,this,dbHelper);
        int size = notes.size();
        Log.i("Size of notes",Integer.toString(size));
        adapter.setNotes(notes);
        this.list.setAdapter(adapter);
        this.list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        this.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreationActivity.class);
                startActivity(intent);
            }
        });

        this.refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshActivity();
            }
        });
    }

    private void refreshActivity(){
        finish();
        startActivity(getIntent());
    }
}