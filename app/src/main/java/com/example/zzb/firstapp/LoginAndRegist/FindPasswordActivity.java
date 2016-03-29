package com.example.zzb.firstapp.LoginAndRegist;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zzb.firstapp.data.MyDatabaseHelper;
import com.example.zzb.firstapp.R;

public class FindPasswordActivity extends Activity implements OnClickListener{

	private Button back_button;
	private Button getCheckNumber_button;
	private Button modify_password_button;
	private EditText inputPhoneNumber;
	private EditText inputCheckNumber;
	private EditText inputNewPassword;
	private String checkNumberRandom=null;
	private boolean hasGetCheckNumber=false;
	private String phoneNumber=null;

	private int time=60;
	Timer timer;


	Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			if(msg.what==1)
			{
				getCheckNumber_button.setText(time+"秒");

			}
			if(time<0)
			{
				time=60;
				timer.cancel();
				getCheckNumber_button.setText("获取验证码");
				getCheckNumber_button.setEnabled(true);
			}
			super.handleMessage(msg);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.find_password_layout);

		back_button=(Button)findViewById(R.id.back_button);
		getCheckNumber_button=(Button)findViewById(R.id.get_check_number);
		modify_password_button=(Button)findViewById(R.id.modify_button);
		inputPhoneNumber=(EditText)findViewById(R.id.phone_number);
		inputCheckNumber=(EditText)findViewById(R.id.check_number);
		inputNewPassword=(EditText)findViewById(R.id.new_password);

		back_button.setOnClickListener(this);
		getCheckNumber_button.setOnClickListener(this);
		modify_password_button.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {

		//返回
		if(arg0.getId()==R.id.back_button)
		{
			finish();
		}
		//获取验证码
		else if(arg0.getId()==R.id.get_check_number)
		{
			checkNumberRandom="654321";
			String number=null;

			number=inputPhoneNumber.getText().toString().trim();

			//如果手机号正确，进入倒计时，并发出验证码到该手机号
			//......待实现
			if(number.length()==11)
			{
				//判断手机号是否已经注册
				boolean result=false;
				SQLiteDatabase database= MyDatabaseHelper.helper.getWritableDatabase();
				Cursor cursor=database.rawQuery("select * from User where phoneNumber=?", new String[]{number});
				if(cursor.moveToFirst())
				{
					if(number.equals(cursor.getString(cursor.getColumnIndex("phoneNumber"))))
						result=true;
				}
				if(result==false)
				{
					Toast.makeText(this, "手机号不存在", Toast.LENGTH_SHORT).show();
					return ;
				}
				phoneNumber=number;
				TimeDown();
				Toast.makeText(this, "验证码已发送到您的手机", Toast.LENGTH_SHORT).show();
				hasGetCheckNumber=true;
			}
			else
			{
				Toast.makeText(this, "手机号错误", Toast.LENGTH_SHORT).show();
			}
		}
		//修改密码
		else if(arg0.getId()==R.id.modify_button)
		{
			if(hasGetCheckNumber==false)
			{
				Toast.makeText(this, "请先获取验证码", Toast.LENGTH_SHORT).show();
				return ;
			}

			String check=inputCheckNumber.getText().toString().trim();
			String newPassword=inputNewPassword.getText().toString();

			if(!check.equals(checkNumberRandom))
			{
				Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT);
				return ;
			}

			if(newPassword.length()<6)
			{
				Toast.makeText(this, "密码不能小于6位", Toast.LENGTH_SHORT);
				return ;
			}

			SQLiteDatabase database=MyDatabaseHelper.helper.getWritableDatabase();
			ContentValues value=new ContentValues();
			value.put("password", newPassword);
			database.update("User", value, "phoneNumber=?", new String[]{phoneNumber});
			Toast.makeText(this, "修改密码成功", Toast.LENGTH_SHORT).show();
			hasGetCheckNumber=false;
			finish();
		}
	}

	public void TimeDown()
	{
		getCheckNumber_button.setEnabled(false);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				time--;
				Message message = new Message();
				message.what = 1;
				handler.sendMessage(message);
			}
		};
		timer = new Timer();
		timer.schedule(task, 0, 1000);
	}


}
