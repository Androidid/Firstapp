package com.example.zzb.firstapp.Main;


import android.graphics.Color;
import android.support.v4.app.*;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.zzb.firstapp.Fifth.FifthFragment;
import com.example.zzb.firstapp.First.FirstFragment;
import com.example.zzb.firstapp.Forth.ForthFragment;
import com.example.zzb.firstapp.R;
import com.example.zzb.firstapp.Second.SecondFragment;
import com.example.zzb.firstapp.Third.ThirdFragment;

/**
 * Created by zzb on 2016/3/1.
 */
public class Main extends FragmentActivity implements ViewPager.OnPageChangeListener {
    private FirstFragment firstFragment = new FirstFragment();
    private SecondFragment secondFragment = new SecondFragment();
    private ThirdFragment thirdFragment = new ThirdFragment();
    private ForthFragment forthFragment = new ForthFragment();
    private FifthFragment fifthFragment = new FifthFragment(this);
    private int back_count = 0;
    private final Fragment[] fragments = {
            firstFragment, secondFragment, thirdFragment, forthFragment, fifthFragment
    };
    private final Class[] CLASS_OF_FRAGMENT = {
            FirstFragment.class, SecondFragment.class, ThirdFragment.class, ForthFragment.class, FifthFragment.class
    };
    private final String[] VALUE_OF_TAB = {
            "自选", "消息", "订阅", "发现", "我"
    };
    private final int[] IMAGE_OF_TAB = {
            R.drawable.first, R.drawable.second, R.drawable.third, R.drawable.forth, R.drawable.fifth
    };
    private final int []IMAGE_REPLACE ={
            R.drawable.first_replace,R.drawable.second_replace,R.drawable.third_replace,R.drawable.forth_replace,R.drawable.fifth_replace
    };
    private ViewPager viewPager;
    private FragmentTabHost tabHost;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        inflater = LayoutInflater.from(this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_view_pager);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(this);
        tabHost = (FragmentTabHost) findViewById(R.id.main_tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.main_view_pager);
        for (int i = 0; i < fragments.length; i++) {
            TabHost.TabSpec spec = tabHost.newTabSpec(VALUE_OF_TAB[i]).setIndicator(getView(i));
            tabHost.addTab(spec, CLASS_OF_FRAGMENT[i], null);
            tabHost.getTabWidget().getChildTabViewAt(i).setOnClickListener(new TabClickListener(viewPager,i));
        }
        tabHost.setCurrentTab(2);
    }

    private View getView(int index) {
        View view = inflater.inflate(R.layout.main_tab_item, null);
        TextView textView = (TextView) view.findViewById(R.id.main_tab_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.main_tab_image);
        imageView.setImageResource(IMAGE_OF_TAB[index]);
        textView.setText(VALUE_OF_TAB[index]);
        textView.setTextSize(10);
        return view;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int i = 0 ;i<fragments.length;i++){
            View view = tabHost.getTabWidget().getChildAt(i);
            ImageView imageView = (ImageView)view.findViewById(R.id.main_tab_image);
            TextView textView = (TextView)view.findViewById(R.id.main_tab_text);
            if(position ==i){
                imageView.setImageResource(IMAGE_REPLACE[i]);
                textView.setTextColor(Color.BLUE);
            }else{
                textView.setTextColor(Color.GRAY);
                imageView.setImageResource(IMAGE_OF_TAB[i]);
            }
        }
        tabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}