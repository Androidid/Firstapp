package com.example.zzb.firstapp.Fifth;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.Toast;

import com.example.zzb.firstapp.R;

;

/**
 * Created by LiuGuoJie on 2016/3/6.
 *
 * 3/9调成了继承什么类  和 修改了下 layout
 */
public class SettingActivity extends PreferenceActivity {
    private Preference refresh;
    private Preference normal_setting;
    private Preference account_bangdi;
    private Preference change_mima;
    private Preference about_this_app;
    private Preference log_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);

        setContentView(R.layout.setting_layout);
    }
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        Toast.makeText(this,"do sth",Toast.LENGTH_SHORT).show();
        return true;
    }
}
