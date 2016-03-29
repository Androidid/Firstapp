package com.example.zzb.firstapp.LoginAndRegist;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zzb.firstapp.data.MyDatabaseHelper;
import com.example.zzb.firstapp.R;

public class RegisterActivity extends Activity implements OnClickListener{

    Timer timer ;

    private String passwordRandom=null;
    private boolean hasGetPassword=false;

    private Button login_button;
    private Button register_button;
    private Button getpassword_button;
    private Button wechat_button;
    private Button qq_button;
    private Button weibo_button;
    private EditText inputPhone;
    private EditText inputPassword;

    private int time=60;



    Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            if(msg.what==1)
            {
                getpassword_button.setText(time+"秒");

            }
            if(time<0)
            {
                time=60;
                timer.cancel();
                getpassword_button.setText("获取密码");
                getpassword_button.setEnabled(true);
                inputPhone.setEnabled(true);
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register_layout);

        //找到控件
        login_button=(Button)findViewById(R.id.login_button);
        register_button=(Button)findViewById(R.id.register_button);
        getpassword_button=(Button)findViewById(R.id.get_password);
        wechat_button=(Button)findViewById(R.id.wechat_button);
        weibo_button=(Button)findViewById(R.id.weibo_button);
        qq_button=(Button)findViewById(R.id.QQ_button);
        inputPhone=(EditText)findViewById(R.id.phone_number);
        inputPassword=(EditText)findViewById(R.id.password);

        //注册监听
        login_button.setOnClickListener(this);
        register_button.setOnClickListener(this);
        getpassword_button.setOnClickListener(this);
        wechat_button.setOnClickListener(this);
        weibo_button.setOnClickListener(this);
        qq_button.setOnClickListener(this);



    }

    public void onClick(View arg0) {

        //登陆
        if(arg0.getId()==R.id.login_button)
        {
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        //注册
        else if(arg0.getId()==R.id.register_button)
        {
            if(hasGetPassword==false)
            {
                Toast.makeText(this, "请先获取密码", Toast.LENGTH_SHORT).show();
                return ;
            }

            String pass=inputPassword.getText().toString();
            String number=inputPhone.getText().toString().trim();
            if(passwordRandom.equals(pass))
            {
                hasGetPassword=false;
                Intent intent=new Intent(RegisterActivity.this,MakeNichengActivity.class);
                intent.putExtra("phoneNumber", number);
                intent.putExtra("password", pass);
                startActivity(intent);
                finish();
            }
            else
                Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();;
        }
        //获取密码
        else if(arg0.getId()==R.id.get_password)
        {
            //随机生成一个密码,然后发送短信给手机号

            String phone_number=inputPhone.getText().toString().trim();
            if(phone_number.length()!=11)
            {
                Toast.makeText(this, "手机号错误，请重新输入", Toast.LENGTH_LONG).show();
                return ;
            }

            //判断手机号是否已经注册过
            boolean result=false;
            SQLiteDatabase database= MyDatabaseHelper.helper.getWritableDatabase();
            Cursor cursor=database.rawQuery("select * from User where phoneNumber=?", new String[]{phone_number});
            if(cursor.moveToFirst())
            {
                if(phone_number.equals(cursor.getString(cursor.getColumnIndex("phoneNumber"))))
                    result=true;
            }
            if(result)
            {
                Toast.makeText(this, "该手机号已注册过，请直接登陆", Toast.LENGTH_SHORT).show();
                return ;
            }

            hasGetPassword=true;
            passwordRandom="123456";

            //开始倒计时
            TimeDown();
        }
        //微信
        else if(arg0.getId()==R.id.wechat_button)
        {
            Toast.makeText(this, "微信注册", Toast.LENGTH_SHORT).show();
        }
        //微博
        else if(arg0.getId()==R.id.weibo_button)
        {
            Toast.makeText(this, "微博注册", Toast.LENGTH_SHORT).show();
        }
        //QQ
        else if(arg0.getId()==R.id.QQ_button)
        {
            Toast.makeText(this, "QQ注册", Toast.LENGTH_SHORT).show();
        }

    }


    //获取密码按钮进行60秒倒计时
    public void TimeDown()
    {
        getpassword_button.setEnabled(false);
        inputPhone.setEnabled(false);
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

