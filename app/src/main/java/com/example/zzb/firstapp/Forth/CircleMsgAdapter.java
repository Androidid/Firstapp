package com.example.zzb.firstapp.Forth;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzb.firstapp.R;

public class CircleMsgAdapter extends ArrayAdapter<CircleMsg>{

	private int resourceId;
	private Context context;
	
	public CircleMsgAdapter(Context context, int resource, List<CircleMsg> msglist) {
		super(context, resource, msglist);
		resourceId=resource;
		this.context=context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		View view=null;
		final ViewHolder viewHolder;
		
		if(convertView==null)
		{
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.content=(TextView)view.findViewById(R.id.content4);
			viewHolder.count_pinglun=(TextView)view.findViewById(R.id.count_of_pinglun);
			viewHolder.pinglun=(TextView)view.findViewById(R.id.pinglun44);
			viewHolder.time=(TextView)view.findViewById(R.id.time_before4);
			viewHolder.user_name=(TextView)view.findViewById(R.id.user_name);
			viewHolder.zan=(TextView)view.findViewById(R.id.count_of_zan);
			viewHolder.zhuanfa=(LinearLayout)view.findViewById(R.id.zhuanfa);
			viewHolder.zhuan_content=(TextView)view.findViewById(R.id.zhuanfa_content);
			viewHolder.zhuan_from_who=(TextView)view.findViewById(R.id.zhuanfa_from_who);
			viewHolder.dowhat=(ImageButton)view.findViewById(R.id.dowhat4);
			viewHolder.dian_zan=(LinearLayout)view.findViewById(R.id.dian_zan);
			
			
			view.setTag(viewHolder);
		}else
		{
			view=convertView;
			viewHolder=(ViewHolder)view.getTag();
		}
		viewHolder.position=position;
		viewHolder.msg=getItem(position);
		viewHolder.content.setText(viewHolder.msg.getContent());
		viewHolder.count_pinglun.setText(viewHolder.msg.getCountOfPinglun()+"");
		viewHolder.time.setText(CircleMsg.getStandardDate(viewHolder.msg.getTime()).toString());
		viewHolder.user_name.setText(viewHolder.msg.getWriter());
		viewHolder.zan.setText(viewHolder.msg.getCountOfZan()+"");
		if(viewHolder.msg.isZhuanfa())
		{
			viewHolder.zhuanfa.setVisibility(View.VISIBLE);
			viewHolder.zhuan_content.setText(viewHolder.msg.getZhuanfaContent());
			viewHolder.zhuan_from_who.setText(viewHolder.msg.getZhuanfaFromwho());
		}
		else
		{
			viewHolder.zhuanfa.setVisibility(View.GONE);
		}
		
		
		viewHolder.dowhat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {

				showPopupWindow(view,viewHolder.msg,viewHolder.position);
			}
		});
		
		viewHolder.pinglun.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(context,ArticleActivity.class);
				
				intent.putExtra("position", viewHolder.position);
				
				
				context.startActivity(intent);
				
			}
		});
		
		viewHolder.dian_zan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(viewHolder.msg.hasZan())
				{
					viewHolder.msg.setCountOfZan(viewHolder.msg.getCountOfZan()-1);
					Toast.makeText(context, "取消点赞成功", Toast.LENGTH_SHORT).show();
				}
				else
				{
					viewHolder.msg.setCountOfZan(viewHolder.msg.getCountOfZan()+1);
					Toast.makeText(context, "点赞成功", Toast.LENGTH_SHORT).show();
				}
				viewHolder.msg.setZan();
				FriendCircleActivity.cirAdapter.notifyDataSetChanged();
				
			}
		});
		
		return view;
	}
	
	class ViewHolder
	{
		LinearLayout zhuanfa;
		TextView user_name;
		TextView content;
		TextView zhuan_content;
		TextView zhuan_from_who;
		TextView time;
		TextView zan;
		TextView count_pinglun;
		TextView pinglun;
		LinearLayout dian_zan;
		ImageButton dowhat;
		CircleMsg msg;
		int position;
	}
	
	private void showPopupWindow(View view,CircleMsg msg,int position) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(context).inflate(
                R.layout.dowhat_popupwindow, null);
        
        final PopupWindow popupWindow = new PopupWindow(contentView,
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        
        // 设置按钮的点击事件
        setOnclickListner(context,contentView,popupWindow,msg,position);
        
       

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
			//	Log.i("mengdd", "onTouch : ");

                return false;
			}
        });

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
//        popupWindow.showAtLocation(view,
//                Gravity.BOTTOM, 0, 0);
        
        int[] location = new int[2];  
        view.getLocationOnScreen(location);  
          
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1]-popupWindow.getHeight());

    }
	
	
	private void setOnclickListner(final Context contenxt,View contentView,final PopupWindow popupWindow,final CircleMsg msg,
			final int position)
	{
		 LinearLayout shoucang = (LinearLayout) contentView.findViewById(R.id.shoucang_dowhat);
	        shoucang.setOnClickListener(new OnClickListener() {
    
	            @Override
	            public void onClick(View v) {
	            	if(msg.hasShoucang())
	    			{
	    				msg.setHasShoucang();
						for(int i=CircleMsg.shoucanglist.size()-1;i>=0;i--){
							if(CircleMsg.shoucanglist.get(i).equals(msg))
								CircleMsg.shoucanglist.remove(i);
								break;
						}

	    				Toast.makeText(context, "取消收藏成功", Toast.LENGTH_SHORT).show();
	    			}
	    			else
	    			{
	    				msg.setHasShoucang();
	    				CircleMsg.shoucanglist.add(msg);
	    				Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
	    			}
	                popupWindow.dismiss();
	            }
	        });
	        LinearLayout zhuanfa = (LinearLayout) contentView.findViewById(R.id.zhuanfa_dowhat);
	        zhuanfa.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	            	Intent intent=new Intent(context,PublishArticleActivity.class);
	                intent.putExtra("isZhuanfa", true);
	                intent.putExtra("position", position);
	                context.startActivity(intent);
	                popupWindow.dismiss();
	            }
	        });
	        LinearLayout pinglun = (LinearLayout) contentView.findViewById(R.id.pinglun_dowhat);
	        pinglun.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	                Intent intent=new Intent(context,PublishArticleActivity.class);
	                intent.putExtra("isPinglun", true);
	                intent.putExtra("position", position);
	                context.startActivity(intent);
	                popupWindow.dismiss();
	            }
	        });
	        LinearLayout fenxiang = (LinearLayout) contentView.findViewById(R.id.fenxiang_dowhat);
	        fenxiang.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	                Toast.makeText(contenxt, "这是分享",
	                        Toast.LENGTH_SHORT).show();
	                popupWindow.dismiss();
	            }
	        });
	}


}
