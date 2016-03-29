package com.example.zzb.firstapp.Forth;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zzb.firstapp.R;

public class PublishArticleActivity extends Activity implements OnClickListener{

	private Button cancle;
	private Button publish;
	private EditText input_article;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.publish_article_layout);
		
		cancle=(Button)findViewById(R.id.cancle4);
		publish=(Button)findViewById(R.id.publish4);
		input_article=(EditText)findViewById(R.id.input_article);
		
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
			FriendCircleActivity.msglist.add(0, new CircleMsg("我", content));
			FriendCircleActivity.cirAdapter.notifyDataSetChanged();
			finish();
			break;

		default:
			break;
		}
		
	}
	

}
