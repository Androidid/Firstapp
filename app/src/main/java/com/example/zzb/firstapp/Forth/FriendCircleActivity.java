package com.example.zzb.firstapp.Forth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.zzb.firstapp.R;

import java.util.ArrayList;

public class FriendCircleActivity extends Activity implements OnClickListener{

	private ImageView back_from_circle;
	private ImageView publish_article;
	public static CircleMsgAdapter cirAdapter;
	public static ArrayList<CircleMsg> msglist=new ArrayList<CircleMsg>();
	private ListView msglistview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.friend_circle);
		
		back_from_circle=(ImageView)findViewById(R.id.back_from_friendcircle4);
		publish_article=(ImageView)findViewById(R.id.publish_article4);
		
		back_from_circle.setOnClickListener(this);
		publish_article.setOnClickListener(this);
		
		initCircleMsg();
		cirAdapter=new CircleMsgAdapter(FriendCircleActivity.this, R.layout.circle_item_layout, msglist);
		msglistview=(ListView)findViewById(R.id.circle_msg_listview);
		
		msglistview.setAdapter(cirAdapter);
	}
	@Override
	public void onClick(View arg0) {
		
		switch (arg0.getId()) {
		case R.id.back_from_friendcircle4:
			finish();
			break;
		case R.id.publish_article4:
			Intent intent=new Intent(FriendCircleActivity.this,PublishArticleActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		
	}
	
	private void initCircleMsg()
	{
		CircleMsg msg1=new CircleMsg("张三", "大家好，我是张三");
		CircleMsg msg2=new CircleMsg("李四", "大家好，我是李四");
		CircleMsg msg3=new CircleMsg("王五", "大家好，我是王五");
		CircleMsg msg4=new CircleMsg("赵六", "大家好，我是赵六","张三","我是张三");
		CircleMsg msg5=new CircleMsg("田七", "大家好，我是田七");
		
		
		msglist.add(msg1);
		msglist.add(msg2);
		msglist.add(msg3);
		msglist.add(msg4);
		msglist.add(msg5);
		
		Log.d("wen", "gsnbgsiurbg");
	}

}
