package com.tastypoisonfly.exercise.Data;

import Service.Messages;

import java.io.Serializable;
import java.sql.Timestamp;


public class MessageData implements Serializable {
    private String title;
    private String author;
    private String text;
    private Timestamp publishTime;
    public  MessageData(){

    }
    public MessageData(String title,String author,String text,Timestamp publishTime){
        this.title = title;
        this.author = author;
        this.text =text;
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }
}
