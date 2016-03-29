package com.example.zzb.firstapp.data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper{

	public static MyDatabaseHelper helper;

	public static final String CREATE_USER="create table User("
			+"phoneNumber text,"
			+"password text,"
			+"nicheng text,"
			+"weixin text,"
			+"qq text,"
			+"email text,"
			+"weibo text)";
	public MyDatabaseHelper(Context context, String name,
							CursorFactory factory, int version) {
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_USER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO 自动生成的方法存根

	}



}