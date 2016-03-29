package com.example.zzb.firstapp.welcome;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.widget.Toast;

import com.example.zzb.firstapp.LoginAndRegist.LoginActivity;
import com.example.zzb.firstapp.Main.Main;
import com.example.zzb.firstapp.R;


public class SplashActivity extends Activity {
    boolean isFirstIn=false;
    private static final int GO_HOME=1000;
    private static final int GO_GUIDE=1001;
    private static final long DELAY_MILLS=3000;
    public static final String SHAREDPREFERENCES_NAME = "first_pref";
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        init();
    }
    private void init() {
        SharedPreferences preferences = getSharedPreferences(
                SHAREDPREFERENCES_NAME, MODE_PRIVATE);
        // 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        isFirstIn = preferences.getBoolean("isFirstIn", true);

        // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        if (!isFirstIn) {
            // 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
            mHandler.sendEmptyMessageDelayed(GO_HOME, DELAY_MILLS);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, DELAY_MILLS);
        }

    }
    private void goHome() {
        Intent intent;
        SharedPreferences sharedPreferences = getSharedPreferences("isfirstlogin",MODE_PRIVATE);
        boolean isFirstLogin = sharedPreferences.getBoolean("isfirstlogin",true);

        LoginActivity.creatDatabase(this);
        if(!isFirstLogin) {
            String phoneNumber = sharedPreferences.getString("phoneNumber",null);
            String password = sharedPreferences.getString("password",null);
            String EmailNumber = sharedPreferences.getString("EmailNumber",null);
            if(LoginActivity.isNumberAndPasswordCorrect(phoneNumber, password)||LoginActivity.isEmailAndPasswordCorrect(EmailNumber,password)) {
                intent = new Intent(SplashActivity.this, Main.class);
            }else{
                intent = new Intent(SplashActivity.this,LoginActivity.class);
                Toast.makeText(SplashActivity.this,"用户名或密码错误请重新登录",Toast.LENGTH_SHORT).show();
            }
        }else{
            intent = new Intent(SplashActivity.this,LoginActivity.class);
        }
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }
    private void goGuide(){
        Intent intent =new Intent(SplashActivity.this,GuideActivity.class);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }
}
