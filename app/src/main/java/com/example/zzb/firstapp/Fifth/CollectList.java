package com.example.zzb.firstapp.Fifth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zzb.firstapp.Forth.ArticleActivity;
import com.example.zzb.firstapp.Forth.CircleMsg;
import com.example.zzb.firstapp.Forth.CircleMsgOnClickListener;
import com.example.zzb.firstapp.Main.Main;
import com.example.zzb.firstapp.R;

/**
 * Created by zzb on 2016/4/17.
 */
public class CollectList  extends Activity{
    private CollectListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CollectListAdapter(this, R.layout.circle_item_layout, CircleMsg.shoucanglist);
        setContentView(R.layout.fifth_collect_list);
        ListView listView = (ListView)findViewById(R.id.fifth_collect_listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(CollectList.this, ArticleActivity.class));

            }
        });
    }
}
