package com.example.zzb.firstapp.Third;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.zzb.firstapp.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zzb on 2016/3/1.
 */
public class ThirdFragment extends Fragment {

    private Button edit_button;
    private Button subscribe_button;
    private PullToRefreshListView listView;
    private static List<SubscribeMessage> list = new ArrayList<SubscribeMessage>();
    private static ThridListViewAdapter adapter  = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thirdfragment,container,false);
        edit_button = (Button)view.findViewById(R.id.third_topic_button);
        subscribe_button = (Button)view.findViewById(R.id.fifth_subscribe_button);
        listView = (PullToRefreshListView)view.findViewById(R.id.third_listview);

        if(list.size()==0)
        list.add(new SubscribeMessage("传统快递品牌遭遇[四大传播困境]","ffdsfaasf","理性投资","网友说股",new Date(System.currentTimeMillis()),2,3,4));
        if(adapter==null)
        adapter = new ThridListViewAdapter(getContext(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        return view;
    }

}
