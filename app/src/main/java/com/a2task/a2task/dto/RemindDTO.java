package com.a2task.a2task.dto;

/**
 * Created by 333da on 16.07.2016.
 */
public class RemindDTO {
    private int id;
    private String title;
    private String text;
    private String type;
    private String date;

    public RemindDTO(int id, String title, String text, String type, String date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.type = type;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
