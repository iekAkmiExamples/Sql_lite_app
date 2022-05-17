package com.example.sql_lite_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sql_lite_app.sqlLite.MyDataHelper;

public class UpdateActivity extends AppCompatActivity {

    private TextView titleArea;
    private TextView tagArea;
    private TextView descriptionArea;
    private Button updateButton;
    private int noteId;
    private MyDataHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //ui
        this.titleArea = findViewById(R.id.note_titleU);
        this.tagArea = findViewById(R.id.note_tagU);
        this.descriptionArea = findViewById(R.id.note_descriptionU);
        this.updateButton = findViewById(R.id.update_button);

        insertData();

        this.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                dbHelper = new MyDataHelper(UpdateActivity.this);
                String title = titleArea.getText().toString();
                String  tag = tagArea.getText().toString().trim();
                String description = descriptionArea.getText().toString();
                dbHelper.updateNote(noteId, title, tag, description);
            }
        });
    }

    void insertData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("tag") && getIntent().hasExtra("description")){
            //Getting Data from Intent
            this.noteId = Integer. valueOf(getIntent().getStringExtra("id"));
            String title = getIntent().getStringExtra("title");
            String tag = getIntent().getStringExtra("tag");
            String description = getIntent().getStringExtra("description");

            //Setting Intent Data
            this.titleArea.setText(title);
            this.tagArea.setText(tag);
            this.descriptionArea.setText(description);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }


}