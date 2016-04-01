package com.example.zzb.firstapp.Fifth;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.zzb.firstapp.R;

import org.w3c.dom.Text;

/**
 * Created by zzb on 2016/3/30.
 */
public class Subscribe extends FragmentActivity {

    private FragmentTabHost tabHost;
    private final String []VALUE_OF_TITLE = {
            "我的订阅","推荐订阅"
    };
    private final Class []CLASS_OF_FRAGMENT ={
            MySubscribe.class,RecommendSubscribe.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fifth_subscribe);
        tabHost = (FragmentTabHost)findViewById(R.id.fifth_subscribe_tabhost);
        tabHost.setup(this,getSupportFragmentManager(),R.id.fifth_subscribe_fragment);
        for(int  i = 0;i<VALUE_OF_TITLE.length;i++){
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(VALUE_OF_TITLE[i]).setIndicator(getView(i));
            tabHost.addTab(tabSpec,CLASS_OF_FRAGMENT[i],null);
        }
        tabHost.setCurrentTab(0);
    }
    private View getView(int index){
        View view = LayoutInflater.from(this).inflate(R.layout.fifth_subscribe_tabhost_item,null);
        TextView textView = (TextView)view.findViewById(R.id.fifth_tabhost_item_text);
        textView.setText(VALUE_OF_TITLE[index]);
        return view;
    }
}
