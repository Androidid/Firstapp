package com.example.zzb.firstapp.Second;

/**
 * Created by zzb on 2016/3/7.
 */
public class Second_Item {
    private String title;
    private String message;
    private String date;
    public Second_Item(String title,String message,String date){
        this.title = title;
        this.message  = message;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
