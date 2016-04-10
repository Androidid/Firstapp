package com.example.zzb.firstapp.Fifth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zzb.firstapp.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzb on 2016/3/30.
 */
public class MySubscribe extends Fragment {

    private static List<MySubscribeMessage> list =new ArrayList<MySubscribeMessage>();
    private FifthSubscribeListviewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fifth_subscribe_listview,container,false);
        PullToRefreshListView pullToRefreshListView = (PullToRefreshListView)view.findViewById(R.id.fifith_subscirbe_listview);
        if(list.size()==0) {
            list.add(new MySubscribeMessage("股行天下", "大盘趋势一旦形成,便不会轻易改变..."));
            list.add(new MySubscribeMessage("股票医生", "把您的股票问题发上来,让专家帮你会诊..."));
            list.add(new MySubscribeMessage("网友说股", "聊大师,谈个股,畅所欲言"));
        }
        adapter = new FifthSubscribeListviewAdapter(getContext(),android.R.layout.simple_list_item_1,list,FifthSubscribeListviewAdapter.MYSUBSCRIBE);
        pullToRefreshListView.setAdapter(adapter);
        return view;
    }
}
