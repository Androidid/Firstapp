package com.example.zzb.firstapp.Main;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by zzb on 2016/3/28.
 */
public class TabClickListener implements View.OnClickListener {
    private int index;
    private ViewPager viewPager;
    public TabClickListener(ViewPager viewPager,int index){
        this.index = index;
        this.viewPager = viewPager;
    }
    @Override
    public void onClick(View v) {
        viewPager.setCurrentItem(index);
    }
}
