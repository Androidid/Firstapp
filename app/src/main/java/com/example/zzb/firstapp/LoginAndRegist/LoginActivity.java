package com.example.zzb.firstapp.LoginAndRegist;



import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zzb.firstapp.Main.Main;
import com.example.zzb.firstapp.data.MyDatabaseHelper;
import com.example.zzb.firstapp.R;


public class LoginActivity extends Activity implements OnClickListener{

	private boolean isPhone=true;
	private Button login_button;
	private Button register_button;
	private Button wechat_button;
	private Button weibo_button;
	private Button qq_button;
	private Button email_button;
	private Button forget_password_button;
	private ImageView user_image;
	private EditText inputPhoneNumber;
	private EditText inputEmail;
	private EditText inputPassword;
	private LinearLayout phone_linearlayout;
	private LinearLayout email_linearlayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_layout);

		//初始化，从布局文件得到各个控件，为按钮注册监听
		init();

		//判断是否是第一次打开app，如果是就创建数据库
		creatDatabase(this);

	}

	@Override
	public void onClick(View arg0) {
		//登陆
		if(arg0.getId()==R.id.login_button)
		{
			boolean result = false;
			SharedPreferences.Editor editor = getSharedPreferences("isfirstlogin",MODE_PRIVATE).edit();
			if(isPhone)
			{

				String phoneNumber=null;
				String password=null;
				phoneNumber=inputPhoneNumber.getText().toString().trim();
				password=inputPassword.getText().toString();
				result=isNumberAndPasswordCorrect(phoneNumber, password);
				if(result){
					editor.putString("phoneNumber",phoneNumber);
					editor.putString("password",password);
					editor.apply();
				}
			}
			else
			{

				String EmailNumber=null;
				String password=null;
				EmailNumber=inputEmail.getText().toString().trim();
				password=inputPassword.getText().toString();
				result=isEmailAndPasswordCorrect(EmailNumber, password);
				if(result){
					editor.putString("EmailNumber",EmailNumber);
					editor.putString("password",password);
					editor.apply();
				}
			}

			if(result)
			{

				Intent intent = new Intent(LoginActivity.this,Main.class);
				editor.putBoolean("isfirstlogin", false);
				editor.apply();
				Toast.makeText(this, "登陆成功", Toast.LENGTH_LONG).show();
				startActivity(intent);

				finish();
			}
			else
			{
				Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_LONG).show();
			}
			//	Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
			//	startActivity(intent);
		}
		//注册
		else if(arg0.getId()==R.id.register_button)
		{
			Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent);
			finish();
		}
		//微信
		else if(arg0.getId()==R.id.wechat_button)
		{
			Toast.makeText(this, "微信登陆", Toast.LENGTH_SHORT).show();
		}
		//微博
		else if(arg0.getId()==R.id.weibo_button)
		{
			Toast.makeText(this, "微博登陆", Toast.LENGTH_SHORT).show();
		}
		//QQ
		else if(arg0.getId()==R.id.QQ_button)
		{
			Toast.makeText(this, "QQ登陆", Toast.LENGTH_SHORT).show();
		}
		//邮箱
		else if(arg0.getId()==R.id.email_button)
		{
			inputPassword.setText(null);
			if(isPhone)
			{
				phone_linearlayout.setVisibility(View.GONE);
				email_linearlayout.setVisibility(View.VISIBLE);
				forget_password_button.setVisibility(View.GONE);
				email_button.setBackgroundResource(R.drawable.phone1);
				isPhone=false;
			}
			else
			{
				email_linearlayout.setVisibility(View.GONE);
				phone_linearlayout.setVisibility(View.VISIBLE);
				forget_password_button.setVisibility(View.VISIBLE);
				email_button.setBackgroundResource(R.drawable.email1);
				isPhone=true;
			}
		}
		//忘记密码
		else if(arg0.getId()==R.id.forget_password_button)
		{
			Intent intent=new Intent(LoginActivity.this,FindPasswordActivity.class);
			startActivity(intent);
		}

	}

	//判断手机号与密码是否正确。。。。待实现
	public static boolean isNumberAndPasswordCorrect(String number,String passwors)
	{
		boolean result=false;
		SQLiteDatabase database= MyDatabaseHelper.helper.getWritableDatabase();
		Cursor cursor=database.rawQuery("select * from User where phoneNumber=? and password=?", new String[]{number,passwors});
		if(cursor.moveToFirst())
		{
			String numberOfPhone=cursor.getString(cursor.getColumnIndex("phoneNumber"));
			String numberOfPassword=cursor.getString(cursor.getColumnIndex("password"));
			if(number.equals(numberOfPhone)&&passwors.equals(numberOfPassword))
				result=true;
		}
		return result;
	}
	//判断邮箱与密码是否正确。。。待实现
	public static boolean isEmailAndPasswordCorrect(String number,String passwors)
	{
		boolean result=false;


		return result;
	}

	//初始化，从布局文件得到各个控件，为按钮注册监听
	private void init()
	{
		//找到按钮
		login_button=(Button)findViewById(R.id.login_button);
		register_button=(Button)findViewById(R.id.register_button);
		wechat_button=(Button)findViewById(R.id.wechat_button);
		weibo_button=(Button)findViewById(R.id.weibo_button);
		qq_button=(Button)findViewById(R.id.QQ_button);
		email_button=(Button)findViewById(R.id.email_button);
		forget_password_button=(Button)findViewById(R.id.forget_password_button);
		user_image=(ImageView)findViewById(R.id.image_user);
		inputPhoneNumber=(EditText)findViewById(R.id.phone_number);
		inputPassword=(EditText)findViewById(R.id.password);
		inputEmail=(EditText)findViewById(R.id.email_or_username);
		phone_linearlayout=(LinearLayout)findViewById(R.id.phone_linearlayout);
		email_linearlayout=(LinearLayout)findViewById(R.id.email_linearlayout);

		//注册监听
		login_button.setOnClickListener(this);
		register_button.setOnClickListener(this);
		wechat_button.setOnClickListener(this);
		weibo_button.setOnClickListener(this);
		qq_button.setOnClickListener(this);
		email_button.setOnClickListener(this);
		forget_password_button.setOnClickListener(this);
	}
	//判断是否是第一次打开app，如果是就创建数据库
	public static void creatDatabase(Context context)
	{
		SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(context);
		boolean isfirstOpen=pref.getBoolean("isfirst", true);
		if(isfirstOpen)
		{
			//重新安装的时候 数据库并不会重新建立，而是原来那个老的

			MyDatabaseHelper.helper=new MyDatabaseHelper(context, "LoveStockSaying", null, 1);
			SQLiteDatabase database=MyDatabaseHelper.helper.getWritableDatabase();
			SharedPreferences.Editor editor=pref.edit();
			editor.putBoolean("isfirst", false);
			ContentValues value=new ContentValues();
			value.put("phoneNumber", "13266811240");
			value.put("password", "123456");
			database.insert("User", null, value);
		}
	}
}
