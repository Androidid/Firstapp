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
public class RecommendSubscribe extends Fragment {
    private static List<MySubscribeMessage> list = new ArrayList<MySubscribeMessage>();
    private PullToRefreshListView pullToRefreshListView;
    private FifthSubscribeListviewAdapter adapter;
    public void addData(MySubscribeMessage mySubscribeMessage){
        list.add(mySubscribeMessage);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fifth_recommend_subscribe_listview,container,false);
        if(list.size()==0) {
            list.add(new MySubscribeMessage("股市直击", "紧击大盘，让投资机会快人一步"));
            list.add(new MySubscribeMessage("股市段子手", "股市人生,沉浮当笑傲"));
        }
        pullToRefreshListView = (PullToRefreshListView)view.findViewById(R.id.fifth_recommendsubscribe_listview);
        adapter  = new FifthSubscribeListviewAdapter(getContext(),android.R.layout.simple_list_item_1,list,FifthSubscribeListviewAdapter.RECOMMENDSUBSCRIBE);
        pullToRefreshListView.setAdapter(adapter);
        return view;
    }
}
