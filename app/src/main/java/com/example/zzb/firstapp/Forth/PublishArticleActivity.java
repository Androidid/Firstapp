package com.example.zzb.firstapp.Forth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzb.firstapp.R;

public class PublishArticleActivity extends Activity implements OnClickListener{

	private Button cancle;
	private Button publish;
	private EditText input_article;
	private TextView topic;
	private boolean isPinglun;
	private boolean isPublish;
	private boolean isZhuanfa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.publish_article_layout);
		
		Intent intent=getIntent();

		cancle=(Button)findViewById(R.id.cancle4);
		publish=(Button)findViewById(R.id.publish4);
		input_article=(EditText)findViewById(R.id.input_article);
		topic=(TextView)findViewById(R.id.topic_article4);
		
		isPinglun=intent.getBooleanExtra("isPinglun", false);
		isPublish=intent.getBooleanExtra("isPublish", false);
		isZhuanfa=intent.getBooleanExtra("isZhuanfa", false);
		if(isPinglun)
			topic.setText("评论");
		else if(isZhuanfa)
		{
			topic.setText("转发");
			int position=intent.getIntExtra("position", 0);
			CircleMsg msg=CircleMsg.msglist.get(position);
			if(msg.isZhuanfa())
			{
				String con="//@"+msg.getWriter()+":"+msg.getContent();
				input_article.setText(con);
				input_article.setSelection(0);
			}
		}
		
		cancle.setOnClickListener(this);
		publish.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		case R.id.cancle4:
			finish();
			break;
		case R.id.publish4:
			String content=input_article.getText().toString();
			if(content==null||content.equals(""))
			{
				Toast.makeText(PublishArticleActivity.this, "内容不能为空",Toast.LENGTH_SHORT).show();
				return ;
			}
			if(isPublish)
			{
				CircleMsg.msglist.add(0, new CircleMsg("我", content));
			}
			else if(isPinglun)
			{
				Intent intent=getIntent();
				int position=intent.getIntExtra("position", 0);
				CircleMsg msg=CircleMsg.msglist.get(position);
				Pinglun pl=new Pinglun("我", content, System.currentTimeMillis());
				msg.addPinglun(pl);
				Toast.makeText(this, "发表成功", Toast.LENGTH_SHORT).show();
			}
			else if(isZhuanfa)
			{
				Intent intent=getIntent();
				int position=intent.getIntExtra("position", 0);
				Log.d("PublishArticleActivity", position+"");
				CircleMsg msg=CircleMsg.msglist.get(position);
				if(msg.isZhuanfa())
				{
					String zhuanfawho=msg.getZhuanfaFromwho();
					String zhuancontent=msg.getZhuanfaContent();
					CircleMsg gg=new CircleMsg("我", content, zhuanfawho, zhuancontent);
					CircleMsg.msglist.add(0,gg);
					
				}
				else
				{
					String zhuanfawho=msg.getWriter();
					String zhuancontent=msg.getContent();
					CircleMsg gg=new CircleMsg("我", content, zhuanfawho, zhuancontent);
					CircleMsg.msglist.add(0,gg);
					
				}
				
			}
			FriendCircleActivity.cirAdapter.notifyDataSetChanged();	
			finish();	
			break;

		default:
			break;
		}
		
	}
	

}
