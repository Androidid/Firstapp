package com.example.zzb.firstapp.Forth;

import java.util.List;


import com.example.zzb.firstapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PinglunAdapter extends ArrayAdapter<Pinglun>{
	
	private int resourceId;
	private Context context;
	
	public PinglunAdapter(Context context, int resource, List<Pinglun> objects,CircleMsg msg) {
		super(context, resource, objects);
		this.context=context;
		this.resourceId=resource;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=null;
		final ViewHolder viewHolder;
		Pinglun pl=getItem(position);
		
		if(convertView==null)
		{
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder=new ViewHolder();
			viewHolder.pinglun_user_name=(TextView)view.findViewById(R.id.pinglun_user_name);
			viewHolder.pinglun_content=(TextView)view.findViewById(R.id.pinglun_content);
			viewHolder.pinglun_RelativeLayout=(RelativeLayout)view.findViewById(R.id.Pinglun_RelativeLayout);
			viewHolder.time=(TextView)view.findViewById(R.id.pinglun_time);
			
			view.setTag(viewHolder);
		}
		else
		{
			view=convertView;
			viewHolder=(ViewHolder)view.getTag();
		}
		
		viewHolder.pinglun_user_name.setText(pl.getUserName());
		viewHolder.pinglun_content.setText(pl.getContent());
		viewHolder.time.setText(pl.getTime());
		
		
		return view;
		
	}
	
	class ViewHolder
	{
		RelativeLayout pinglun_RelativeLayout;
		TextView pinglun_user_name;
		TextView pinglun_content;
		TextView time;
		CircleMsg msg;
		int position;
	}

}
