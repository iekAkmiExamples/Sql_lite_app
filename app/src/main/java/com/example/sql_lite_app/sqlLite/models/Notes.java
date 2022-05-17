package com.example.sql_lite_app.sqlLite.models;

public class Notes {
    private int _id;
    private String title;
    private String description;
    private String tag;
    private String creationDate;

    public Notes(int _id, String title, String description, String tag, String creationDate) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.tag = tag;
        this.creationDate = creationDate;
    }

    public int get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
