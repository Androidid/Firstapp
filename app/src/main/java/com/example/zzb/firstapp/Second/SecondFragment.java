package com.example.zzb.firstapp.Second;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zzb.firstapp.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * Created by zzb on 2016/3/1.
 */
public class SecondFragment extends Fragment implements  AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener{


    private  String date_text;
    public PullToRefreshListView pullToRefreshListView;

    public  static ArrayList<Second_Item>list = new ArrayList<Second_Item>();
    private MySecondAdapter mySecondAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("second","on create");
        View view = inflater.inflate(R.layout.secondfragment,container,false);
        pullToRefreshListView = (PullToRefreshListView)view.findViewById(R.id.pull_to_refresh_listview);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        date_text = "2016年03月07号";
        getlist();
        mySecondAdapter = new MySecondAdapter(getActivity(),R.layout.second_listview_layout,list);
        pullToRefreshListView.setAdapter(mySecondAdapter);
        pullToRefreshListView.setOnItemClickListener(this);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if(pullToRefreshListView.isHeaderShown()){
                    new GetHeaderDataTask().execute();
                }else{
                    new GetBottomDataTask().execute();
                }
            }
        }) ;
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("second","on resume");
    }

    private ArrayList<Second_Item> getlist(){
        if(list==null||list.size()==0) {
            for (int i = 0; i < 20; i++) {
                Second_Item item = new Second_Item("我是标题" + (i + 1), "" + (i + 1), date_text);
                list.add(item);
            }
        }else{
        }
        return list;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent   = new Intent (getActivity(),ChatActivity.class);
        intent.putExtra("id", position);
        intent.putExtra("title",list.get(position).getTitle());
        startActivity(intent);
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
    private class GetHeaderDataTask extends AsyncTask<Void,Void,String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            try{
                Thread.sleep(3000);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }

            return  new String[0];
        }

        @Override
        protected void onPostExecute(String[] strings) {
            list.add(new Second_Item("我是标题" + 21, "" + 21, date_text));
            list.add(new Second_Item("我是标题" + 22, "" + 22, date_text));
            list.add(new Second_Item("我是标题" + 23, "" + 23, date_text));
            mySecondAdapter.notifyDataSetChanged();
            pullToRefreshListView.onRefreshComplete();
            super.onPostExecute(strings);
        }
    }
    private  class GetBottomDataTask extends AsyncTask<Void,Void,String[]>{

        @Override
        protected String[] doInBackground(Void... params) {
            try{
                Thread.sleep(4000);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] strings) {
            list.add(new Second_Item("我是标题" + 24, "" + 24, date_text));
            list.add(new Second_Item("我是标题" + 25, "" + 25, date_text));

            mySecondAdapter.notifyDataSetChanged();
            pullToRefreshListView.onRefreshComplete();

            super.onPostExecute(strings);
        }
    }

}
