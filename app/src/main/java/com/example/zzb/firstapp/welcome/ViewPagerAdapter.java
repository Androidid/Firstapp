package com.example.zzb.firstapp.welcome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zzb.firstapp.LoginAndRegist.LoginActivity;
import com.example.zzb.firstapp.Main.Main;
import com.example.zzb.firstapp.R;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<View> views;
    private Activity activity;
    private static final String SHAREDPREFERENCES_NAME = "first_pref";
    public ViewPagerAdapter(List<View> views, Activity activity) {
        this.views = views;
        this.activity = activity;
    }
    @Override
    public void destroyItem(ViewGroup vp, int arg1, Object arg2) {
        vp.removeView(views.get(arg1));
    }

    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }
    @Override
    public Object instantiateItem(ViewGroup vp, int arg1) {
        vp.addView(views.get(arg1), 0);
        if (arg1 == views.size() - 1) {
            ImageView mStartWeiboImageButton = (ImageView) vp
                    .findViewById(R.id.start_app);
            mStartWeiboImageButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 设置已经引导
                    setGuided();
                    goHome();
                }

            });
        }
        return views.get(arg1);
    }
    private void goHome() {
        // 跳转
        Intent intent;

        SharedPreferences preferences = activity.getSharedPreferences("isfirstlogin",Context.MODE_PRIVATE);
        Boolean isFirstLogin = preferences.getBoolean("isfirstlogin",true);
        if(isFirstLogin) {
            intent = new Intent(activity, LoginActivity.class);
        }else{
            intent = new Intent(activity,Main.class);
        }

        activity.startActivity(intent);
        activity.finish();
    }
    private void setGuided() {
        SharedPreferences preferences = activity.getSharedPreferences(
                SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        // 存入数据
        editor.putBoolean("isFirstIn", false);
        // 提交修改
        editor.apply();
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }
    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }
    @Override
    public Parcelable saveState() {
        return null;
    }

}
