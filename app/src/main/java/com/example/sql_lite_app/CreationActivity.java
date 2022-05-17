package com.example.sql_lite_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sql_lite_app.sqlLite.MyDataHelper;

public class CreationActivity extends AppCompatActivity {

    private TextView titleArea;
    private TextView tagArea;
    private TextView descriptionArea;
    private Button createButton;
    private MyDataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);
        //ui
        this.titleArea = findViewById(R.id.note_title);
        this.tagArea = findViewById(R.id.note_tag);
        this.descriptionArea = findViewById(R.id.note_description);
        this.createButton = findViewById(R.id.creation_button);

        this.createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper = new MyDataHelper(CreationActivity.this);
                if (titleArea.getText().length() == 0 || tagArea.getText().length() == 0 || descriptionArea.getText().length() == 0) {
                    Toast.makeText(CreationActivity.this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
                } else
                    dbHelper.addNote(titleArea.getText().toString(), tagArea.getText().toString().trim(), descriptionArea.getText().toString());
            }
        });
    }
}