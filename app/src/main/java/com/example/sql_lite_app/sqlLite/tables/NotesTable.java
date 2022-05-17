package com.example.sql_lite_app.sqlLite.tables;

import android.provider.BaseColumns;

public class NotesTable implements BaseColumns {
    //table
    public static final String TABLE_NAME = "notes";
    //table columns
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "note_title";
    public static final String COLUMN_DESCRIPTION = "note_description";
    public static final String COLUMN_TAG = "note_tag";
    public static final String COLUMN_CREATION_DATE = "note_creation_date";
}
