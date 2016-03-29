package com.example.zzb.firstapp.Forth;

import android.util.Log;

public class CircleMsg {
	
	private String writer;
	private String content;
	private int countOfZan=0;
	private boolean isZhuanfa;
	private String zhuanfaContent;
	private String zhuanfaFromwho;
	private int countOfPinglun=0;
	private long time;
	
	
	public CircleMsg(String writer,String content)
	{
		Log.d("wen", "getView");
		this.writer=writer;
		this.content=content;
		time=System.currentTimeMillis();
		isZhuanfa=false;
	}
	public CircleMsg(String writer,String content,String zhuanFromWho,String zhuanfaContent)
	{
		this.writer=writer;
		this.content=content;
		this.zhuanfaFromwho=zhuanFromWho;
		this.zhuanfaContent=zhuanfaContent;
		time=System.currentTimeMillis();
		isZhuanfa=true;
	}
	
	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getCountOfZan() {
		return countOfZan;
	}


	public void setCountOfZan(int countOfZan) {
		this.countOfZan = countOfZan;
	}


	public boolean isZhuanfa() {
		return isZhuanfa;
	}


	public void setZhuanfa(boolean isZhuanfa) {
		this.isZhuanfa = isZhuanfa;
	}


	public String getZhuanfaContent() {
		return zhuanfaContent;
	}


	public void setZhuanfaContent(String zhuanfaContent) {
		this.zhuanfaContent = zhuanfaContent;
	}


	public String getZhuanfaFromwho() {
		return zhuanfaFromwho;
	}


	public void setZhuanfaFromwho(String zhuanfaFromwho) {
		this.zhuanfaFromwho = zhuanfaFromwho;
	}


	public int getCountOfPinglun() {
		return countOfPinglun;
	}


	public void setCountOfPinglun(int countOfPinglun) {
		this.countOfPinglun = countOfPinglun;
	}

	public long getTime(){
		return time;
	}
	


	/** 
	 * 将时间戳转为代表"距现在多久之前"的字符串 
	 * @param timeStr   时间戳 
	 * @return 
	 */  
	public static String getStandardDate(long t) {  
	  
	    StringBuffer sb = new StringBuffer();  
	    long time = System.currentTimeMillis() - (t*1000);  
	    long mill = (long) Math.ceil(time /1000);//秒前  
	  
	    long minute = (long) Math.ceil(time/60/1000.0f);// 分钟前  
	  
	    long hour = (long) Math.ceil(time/60/60/1000.0f);// 小时  
	  
	    long day = (long) Math.ceil(time/24/60/60/1000.0f);// 天前  
	  
	    if (day - 1 > 0) {  
	        sb.append(day + "天");  
	    } else if (hour - 1 > 0) {  
	        if (hour >= 24) {  
	            sb.append("1天");  
	        } else {  
	            sb.append(hour + "小时");  
	        }  
	    } else if (minute - 1 > 0) {  
	        if (minute == 60) {  
	            sb.append("1小时");  
	        } else {  
	            sb.append(minute + "分钟");  
	        }  
	    } else if (mill - 1 > 0) {  
	        if (mill == 60) {  
	            sb.append("1分钟");  
	        } else {  
	            sb.append(mill + "秒");  
	        }  
	    } else {  
	        sb.append("刚刚");  
	    }  
	    if (!sb.toString().equals("刚刚")) {  
	        sb.append("前");  
	    }  
	    return sb.toString();  
	} 

}
