package com.example.zzb.firstapp.Forth;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zzb.firstapp.R;

import java.util.List;

public class CircleMsgAdapter extends ArrayAdapter<CircleMsg>{

	private int resourceId;
	private Context context;
	
	public CircleMsgAdapter(Context context, int resource, List<CircleMsg> msg) {
		super(context, resource, msg);
		resourceId=resource;
		this.context=context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d("wen", "getView");
		
		CircleMsg msg=getItem(position);
		View view=null;
		ViewHolder viewHolder;
		if(convertView==null)
		{
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.content=(TextView)view.findViewById(R.id.content4);
			viewHolder.pinglun=(TextView)view.findViewById(R.id.count_of_pinglun);
			viewHolder.time=(TextView)view.findViewById(R.id.time_before4);
			viewHolder.user_name=(TextView)view.findViewById(R.id.user_name);
			viewHolder.zan=(TextView)view.findViewById(R.id.count_of_zan);
			viewHolder.zhuanfa=(LinearLayout)view.findViewById(R.id.zhuanfa);
			viewHolder.zhuan_content=(TextView)view.findViewById(R.id.zhuanfa_content);
			viewHolder.zhuan_from_who=(TextView)view.findViewById(R.id.zhuanfa_from_who);
			view.setTag(viewHolder);
		}else
		{
			view=convertView;
			viewHolder=(ViewHolder)view.getTag();
		}
		
		
		viewHolder.content.setText(msg.getContent());
		viewHolder.pinglun.setText(msg.getCountOfPinglun()+"");
		viewHolder.time.setText(CircleMsg.getStandardDate(msg.getTime()).toString());
		viewHolder.user_name.setText(msg.getWriter());
		viewHolder.zan.setText(msg.getCountOfZan()+"");
		if(msg.isZhuanfa())
		{
			viewHolder.zhuanfa.setVisibility(View.VISIBLE);
			viewHolder.zhuan_content.setText(msg.getZhuanfaContent());
			viewHolder.zhuan_from_who.setText(msg.getZhuanfaFromwho());
		}
		else
		{
			viewHolder.zhuanfa.setVisibility(View.GONE);
		}
		
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
		TextView pinglun;
	}

}
