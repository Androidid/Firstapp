package com.example.zzb.firstapp.Fifth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzb.firstapp.welcome.GuideActivity;
import com.example.zzb.firstapp.R;

/**
 * Created by zzb on 2016/3/1.
 *
 * 3/6 修复一个bug  isFirstIn 那里应该默认false
 *
 * 3/6 添加了对应的几个按钮  创作了setting
 *
 * 3/9加了收藏
 */
public class FifthFragment extends Fragment implements View.OnClickListener{
    private Context context;
    @SuppressLint("ValidFragment")
    public FifthFragment(Context context){
        this.context = context;
    }
    public FifthFragment(){
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fifthfragment,container,false);
        //初始化
        Button setting=(Button)view.findViewById(R.id.fifth_setting);
        Button dingyue=(Button)view.findViewById(R.id.fifth_dingyue);
        Button lianxiren=(Button)view.findViewById(R.id.fifth_lianxiren);
        Button shoucang=(Button)view.findViewById(R.id.fifth_shoucang);
        LinearLayout profile=(LinearLayout)view.findViewById(R.id.user_pro);
        SharedPreferences pref = getContext().getSharedPreferences("isfirstlogin", Context.MODE_PRIVATE);
        String text = pref.getString("user_name", "用户名");
        TextView tv=(TextView)view.findViewById(R.id.fifth_user_id);
        tv.setText(text);
        profile.setOnClickListener(this);
        lianxiren.setOnClickListener(this);
        dingyue.setOnClickListener(this);
        setting.setOnClickListener(this);
        shoucang.setOnClickListener(this);

        Button welcomebutton  = (Button)view.findViewById(R.id.welcome_button);
        welcomebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, GuideActivity.class));
            }
        });

        return view;
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.fifth_setting:
                intent=new Intent(context,SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.fifth_lianxiren:
                intent=new Intent(context,AssortMain.class);
                startActivity(intent);
                break;
            case R.id.fifth_dingyue:
                Toast.makeText(context,"订阅",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fifth_shoucang:
                Toast.makeText(context,"收藏",Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_pro:
                Toast.makeText(context,"个人",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
