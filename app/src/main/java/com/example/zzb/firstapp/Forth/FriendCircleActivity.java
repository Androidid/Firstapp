package com.example.zzb.firstapp.Forth;

import java.util.ArrayList;

import com.example.zzb.firstapp.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

public class FriendCircleActivity extends Activity implements OnClickListener{

	private ImageView back_from_circle;
	private ImageView publish_article;
	public static CircleMsgAdapter cirAdapter;
	
	private PullToRefreshListView msglistview;
	static{
		initCircleMsg();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.friend_circle);
		
		back_from_circle=(ImageView)findViewById(R.id.back_from_friendcircle4);
		publish_article=(ImageView)findViewById(R.id.publish_article4);
		
		back_from_circle.setOnClickListener(this);
		publish_article.setOnClickListener(this);
		
		
		cirAdapter=new CircleMsgAdapter(FriendCircleActivity.this, R.layout.circle_item_layout, CircleMsg.msglist);
		msglistview=(PullToRefreshListView)findViewById(R.id.circle_msg_listview);
		msglistview.setAdapter(cirAdapter);
		msglistview.setOnItemClickListener(new CircleMsgOnClickListener(this));
		msglistview.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				
				new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						
						super.onPostExecute(result);
						CircleMsg.msglist.add(0,new CircleMsg("小小", "你好啊"));
						cirAdapter.notifyDataSetChanged();
						msglistview.onRefreshComplete();
					}
					
				}.execute();
				
			}
		});
	}
	@Override
	public void onClick(View arg0) {
		
		switch (arg0.getId()) {
		case R.id.back_from_friendcircle4:
			finish();
			break;
		case R.id.publish_article4:
			Intent intent=new Intent(FriendCircleActivity.this,PublishArticleActivity.class);
			intent.putExtra("isPublish", true);
			startActivity(intent);
			break;
		default:
			break;
		}
		
	}
	
	private static void initCircleMsg()
	{
		CircleMsg msg1=new CircleMsg("张三", "大家好，我是张三kvbkgvughxdkbgvxkdgbvkxdbnbkxgvkfxbvkljxdbvkdxbljkvbxbvkfcbvkjdxb jkvgxjkbgvxjkdbvkjbvk");
		CircleMsg msg2=new CircleMsg("李四", "大家好，我是李四");
		CircleMsg msg3=new CircleMsg("王五", "大家好，我是王五");
		CircleMsg msg4=new CircleMsg("赵六", "大家好，我是赵六","张三","我是张三");
		CircleMsg msg5=new CircleMsg("田七", "大家好，我是田七");
		CircleMsg msg6=new CircleMsg("王八", "大家好，我是王八");
		
		
		CircleMsg.msglist.add(msg1);
		CircleMsg.msglist.add(msg2);
		CircleMsg.msglist.add(msg3);
		CircleMsg.msglist.add(msg4);
		CircleMsg.msglist.add(msg5);
		CircleMsg.msglist.add(msg6);
		

	}

}
