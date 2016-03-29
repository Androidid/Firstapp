package com.example.zzb.firstapp.LoginAndRegist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zzb.firstapp.Main.Main;
import com.example.zzb.firstapp.data.MyDatabaseHelper;
import com.example.zzb.firstapp.R;

public class MakeNichengActivity extends Activity implements OnClickListener{

	private Button back_button;
	private Button modify_button;
	private Button ok_button;
	private EditText inputNicheng;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.nichen_layout);

		back_button=(Button)findViewById(R.id.back_button);
		modify_button=(Button)findViewById(R.id.modify_nicheng_button);
		ok_button=(Button)findViewById(R.id.ok_nicheng_button);
		inputNicheng=(EditText)findViewById(R.id.input_nicheng);

		back_button.setOnClickListener(this);
		modify_button.setOnClickListener(this);
		ok_button.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {

		//返回
		if(arg0.getId()==R.id.back_button)
		{
			Intent intent = new Intent(MakeNichengActivity.this,LoginActivity.class);
			startActivity(intent);
			finish();
		}
		//修改昵称
		else if(arg0.getId()==R.id.modify_nicheng_button)
		{
			String nicheng=inputNicheng.getText().toString();
			isNichengLegal(nicheng);
		}
		//确定修改
		else if(arg0.getId()==R.id.ok_nicheng_button)
		{

			String nicheng=inputNicheng.getText().toString();
			if(isNichengLegal(nicheng))
			{
				String phoneNumber=getIntent().getStringExtra("phoneNumber");
				String password=getIntent().getStringExtra("password");
				SQLiteDatabase database= MyDatabaseHelper.helper.getWritableDatabase();
				ContentValues value=new ContentValues();
				value.put("phoneNumber", phoneNumber);
				value.put("password", password);
				value.put("nicheng", nicheng);
				database.insert("User", null, value);

				SharedPreferences.Editor editor = getSharedPreferences("isfirstlogin",MODE_PRIVATE).edit();
				editor.putBoolean("isfirstlogin",false);
				editor.putString("phoneNumber",phoneNumber);
				editor.putString("password",password);
				editor.putString("user_name",nicheng);

				editor.apply();
				Intent intent = new Intent (MakeNichengActivity.this,Main.class);

				startActivity(intent);
				finish();
			}

		}

	}

	//判断昵称是否合法
	public boolean isNichengLegal(String nicheng)
	{
		if(nicheng==null||nicheng.equals(""))
		{
			Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		else if(nicheng.length()>15)
		{
			Toast.makeText(this, "昵称超过15个字符", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
}
