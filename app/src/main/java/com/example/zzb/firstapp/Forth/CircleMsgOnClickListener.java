package com.example.zzb.firstapp.Forth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class CircleMsgOnClickListener implements OnItemClickListener{

	private Context context;
	
	public CircleMsgOnClickListener(Context context)
	{
		this.context=context;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		
		Intent intent=new Intent(context,ArticleActivity.class);
		
		intent.putExtra("position", position-1);
		intent.putExtra("isPublish", true);
		
		context.startActivity(intent);
		
	}

}
