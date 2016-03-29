package com.example.zzb.firstapp.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zzb.firstapp.welcome.ViewPagerAdapter;

/**
 * Created by zzb on 2016/3/28.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment[]fragments;
    private FragmentManager fm;

    public MainViewPagerAdapter(FragmentManager fm,Fragment[]fragments){
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
