package com.example.zzb.firstapp.Third;

import com.example.zzb.firstapp.Fifth.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zzb on 2016/4/8.
 */
public class SubscribeMessage  {
    private String title;
    private String from;
    private String to;
    private String message;
    private Date date;
    private int forwardCount;
    private int commentCount;
    private int praiseCount;


    public SubscribeMessage(String title,String message,String from,String to,Date date,int forwardCount,int commentCount,int praiseCount){
        this.title = title;
        this.message  = message;
        this.from = from;
        this.to  = to;
        this.date = date;
        this.commentCount = commentCount;
        this.forwardCount = forwardCount;
        this.praiseCount = praiseCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String da = sdf.format(date);
        return da;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getForwardCount() {
        return forwardCount;
    }

    public void setForwardCount(int forwardCount) {
        this.forwardCount = forwardCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }
}
