package com.example.zzb.firstapp.Fifth;

/**
 * Created by zzb on 2016/3/30.
 */
public class MySubscribeMessage {
    private String title;
    private String message;

    public MySubscribeMessage(String title,String message){
        this.title  = title;
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
