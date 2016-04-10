package com.example.zzb.firstapp.Forth;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzb.firstapp.R;

public class ArticleActivity extends Activity implements OnClickListener{
	private TextView userName;
	private TextView content;
	private TextView zhuanfaContent;
	private TextView zhuanfaFromWho;
	private TextView time;
	private LinearLayout zhuanfaLayout;
	private ImageButton more_choice;
	private ImageButton backFromArticleButton;
	private ImageButton shoucang_Button;
	private CircleMsg msg;
	private PinglunAdapter pladater;
	private ListView pllistview;
	private LinearLayout pinglun;
	private LinearLayout dian_zan;
	private LinearLayout zhuanfa;
	private TextView zan;
	private TextView pinglunshu;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.article_layout);
		userName=(TextView)findViewById(R.id.user_name);
		content=(TextView)findViewById(R.id.content_article);
		time=(TextView)findViewById(R.id.article_time);
		zhuanfaLayout=(LinearLayout)findViewById(R.id.zhuanfa);
		more_choice=(ImageButton)findViewById(R.id.more_choice);
		backFromArticleButton=(ImageButton)findViewById(R.id.back_from_article);
		shoucang_Button=(ImageButton)findViewById(R.id.shoucang_button4);
		pinglun=(LinearLayout)findViewById(R.id.pinglun_article);
		dian_zan=(LinearLayout)findViewById(R.id.dianzan_article);
		zan=(TextView)findViewById(R.id.zan_article);
		pinglunshu=(TextView)findViewById(R.id.pinglunshu_article4);
		zhuanfa=(LinearLayout)findViewById(R.id.zhuanfa_article);
		
		Intent intent=getIntent();
		int position=intent.getIntExtra("position", 0);
		msg=CircleMsg.msglist.get(position);
		
		userName.setText(msg.getWriter());
		content.setText(msg.getContent());
		pinglunshu.setText("评论 "+msg.getCountOfPinglun());
		boolean hasShoucang=msg.hasShoucang();
		
		if(hasShoucang)
			shoucang_Button.setBackgroundResource(R.drawable.wujiaoxing_yellow);
		else
			shoucang_Button.setBackgroundResource(R.drawable.wujiaoxing_gray);
		if(msg.hasZan())
			zan.setText("已赞");
		else
			zan.setText("点赞");
		
		//将时间戳转化为年月日时分秒
		long tt=msg.getTime();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date d1=new Date(tt);  
        String t1=format.format(d1); 
		time.setText(t1);
		
		
		
		if(msg.isZhuanfa())
		{
			zhuanfaContent=(TextView)findViewById(R.id.zhuanfa_content);
			zhuanfaFromWho=(TextView)findViewById(R.id.zhuanfa_from_who);
			zhuanfaContent.setText(msg.getZhuanfaContent());
			zhuanfaFromWho.setText(msg.getZhuanfaFromwho());
		}
		else
			zhuanfaLayout.setVisibility(View.GONE);
		
		more_choice.setOnClickListener(this);
		backFromArticleButton.setOnClickListener(this);
		shoucang_Button.setOnClickListener(this);
		pinglun.setOnClickListener(this);
		dian_zan.setOnClickListener(this);
		zhuanfa.setOnClickListener(this);
		
		pladater=new PinglunAdapter(ArticleActivity.this, R.layout.pinglun_item, msg.getPinglunlist(), msg);
		pllistview=(ListView)findViewById(R.id.pinglun_listview);
		pllistview.setAdapter(pladater);
	}

	@Override
	public void onClick(View view) {
		
		switch(view.getId())
		{
		case R.id.more_choice:
			showPopupWindow(view);
			break;
		case R.id.back_from_article:
			finish();
			break;
		case R.id.shoucang_button4:
			if(msg.hasShoucang())
			{
				shoucang_Button.setBackgroundResource(R.drawable.wujiaoxing_gray);
				msg.setHasShoucang();
				CircleMsg.shoucanglist.remove(msg);
				Toast.makeText(ArticleActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
			}
			else
			{
				shoucang_Button.setBackgroundResource(R.drawable.wujiaoxing_yellow);
				msg.setHasShoucang();
				CircleMsg.shoucanglist.add(msg);
				Toast.makeText(ArticleActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.pinglun_article:
			Intent intent1=getIntent();
			int position=intent1.getIntExtra("position", 0);
			Intent intent=new Intent(ArticleActivity.this,PublishArticleActivity.class);
            intent.putExtra("isPinglun", true);
            intent.putExtra("position", position);
            startActivity(intent);
            break;
		case R.id.dianzan_article:
			if(msg.hasZan())
			{
				msg.setCountOfZan(msg.getCountOfZan()-1);
				zan.setText("点赞");
				msg.setZan();
				FriendCircleActivity.cirAdapter.notifyDataSetChanged();
			}
			else
			{
				msg.setCountOfZan(msg.getCountOfZan()+1);
				zan.setText("已赞");
				msg.setZan();
				FriendCircleActivity.cirAdapter.notifyDataSetChanged();
			}
			break;
		case R.id.zhuanfa_article:
			intent1=getIntent();
			position=intent1.getIntExtra("position", 0);
			intent=new Intent(ArticleActivity.this,PublishArticleActivity.class);
            intent.putExtra("isZhuanfa", true);
            intent.putExtra("position", position);
            startActivity(intent);
			break;
		}
	}
	
	
	 @Override
	protected void onRestart() {
		
		super.onRestart();
		pinglunshu.setText("评论 "+msg.getCountOfPinglun());
	}

	private void showPopupWindow(View view) {

	        // 一个自定义的布局，作为显示的内容
	        View contentView = LayoutInflater.from(ArticleActivity.this).inflate(
	                R.layout.more_choice_popupwindow, null);
	        
	        final PopupWindow popupWindow = new PopupWindow(contentView,
	                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
	        
	        // 设置按钮的点击事件
	        Button shoucang = (Button) contentView.findViewById(R.id.shoucang_more);
	        shoucang.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	                Toast.makeText(ArticleActivity.this, "这是收藏",
	                        Toast.LENGTH_SHORT).show();
	                popupWindow.dismiss();
	            }
	        });
	        Button jubao = (Button) contentView.findViewById(R.id.jubao_more);
	        jubao.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	                Toast.makeText(ArticleActivity.this, "这是举报",
	                        Toast.LENGTH_SHORT).show();
	                popupWindow.dismiss();
	            }
	        });
	        Button quxiao = (Button) contentView.findViewById(R.id.cancel_more);
	        quxiao.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	                Toast.makeText(ArticleActivity.this, "这是取消",
	                        Toast.LENGTH_SHORT).show();
	                popupWindow.dismiss();
	            }
	        });

	       

	        popupWindow.setTouchable(true);

	        popupWindow.setTouchInterceptor(new OnTouchListener() {

				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					Log.i("mengdd", "onTouch : ");

	                return false;
	                // 这里如果返回true的话，touch事件将被拦截
	                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
				}
	        });

	        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
	       
	   //     popupWindow.setBackgroundDrawable(getResources().getDrawable(
	      //          R.drawable.login_background));
	        
	   //     popupWindow.setAnimationStyle(R.style);
	        popupWindow.setBackgroundDrawable(new BitmapDrawable());
	        popupWindow.setOutsideTouchable(true);
	        // 设置好参数之后再show
	    //    popupWindow.showAsDropDown(view);
	        popupWindow.showAtLocation(this.findViewById(R.id.more_choice),
	                Gravity.BOTTOM, 0, 0);

	    }


}
