package com.example.zzb.firstapp.Forth;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pinglun {
	private String userName;
	private long time;
	private String content;
	
	Pinglun(String userName,String content,long time)
	{
		this.userName=userName;
		this.content=content;
		this.time=time;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTime() {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date d1=new Date(time);  
        String t1=format.format(d1); 
		return t1;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
