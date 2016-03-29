package com.example.zzb.firstapp.Second;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zzb on 2016/3/8.
 */
public class ChatContent {

    public static  int CHAT_LEFT =  0;
    public static  int CHAT_RIGHT  = 1;
    private int type;
    private String message;
    private String sendtime;
    private long longtime;

    private long distance;

    public ChatContent(String a){
        if(a==null)
            return ;

        String []data = a.split(",");
        this.type =Integer.parseInt(data[0]);
        this.message = data[1];
        this.sendtime =  data[2];
        this.longtime =Long.parseLong(data[3]);
        this.distance = Integer.parseInt(data[4]);
    }
    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getDistance() {
        return distance;
    }

    public void setLongtime(long longtime) {
        this.longtime = longtime;
    }

    public long getLongtime() {
        return longtime;
    }

    public void setSendtime(String sendtime) {

        this.sendtime  = sendtime;
    }

    public String getSendtime() {

        return sendtime;
    }

    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }

    public ChatContent(String message,int type){
        this.message = message;
        this.type =  type;
    }
}
