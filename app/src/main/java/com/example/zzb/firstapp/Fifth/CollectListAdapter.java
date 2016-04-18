package com.example.zzb.firstapp.Fifth;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzb.firstapp.Forth.ArticleActivity;
import com.example.zzb.firstapp.Forth.CircleMsg;
import com.example.zzb.firstapp.Forth.FriendCircleActivity;
import com.example.zzb.firstapp.Forth.PublishArticleActivity;
import com.example.zzb.firstapp.R;

import java.util.List;

/**
 * Created by zzb on 2016/4/17.
 */
public class CollectListAdapter extends ArrayAdapter {

    private int resource;
    private List<CircleMsg> list;
    private Context context;
    public CollectListAdapter(Context context, int resource, List<CircleMsg> list) {
        super(context, resource, list);
        this.resource = resource;
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        final ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resource, null);
            viewHolder = new ViewHolder();
            viewHolder.content = (TextView) view.findViewById(R.id.content4);
            viewHolder.count_pinglun = (TextView) view.findViewById(R.id.count_of_pinglun);
            viewHolder.pinglun = (TextView) view.findViewById(R.id.pinglun44);
            viewHolder.time = (TextView) view.findViewById(R.id.time_before4);
            viewHolder.user_name = (TextView) view.findViewById(R.id.user_name);
            viewHolder.zan = (TextView) view.findViewById(R.id.count_of_zan);
            viewHolder.zhuanfa = (LinearLayout) view.findViewById(R.id.zhuanfa);
            viewHolder.zhuan_content = (TextView) view.findViewById(R.id.zhuanfa_content);
            viewHolder.zhuan_from_who = (TextView) view.findViewById(R.id.zhuanfa_from_who);
            viewHolder.dian_zan = (LinearLayout) view.findViewById(R.id.dian_zan);
            viewHolder.buttom = (RelativeLayout)view.findViewById(R.id.forth_bottom_line);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.buttom.setVisibility(View.GONE);
        viewHolder.position = position;
        Log.d("main",position+"");
        viewHolder.msg = list.get(position);
        if(viewHolder.msg!=null) {
            viewHolder.content.setText(viewHolder.msg.getContent());
            viewHolder.count_pinglun.setText(viewHolder.msg.getCountOfPinglun() + "");
            viewHolder.time.setVisibility(View.GONE);
            viewHolder.user_name.setText(viewHolder.msg.getWriter());
            viewHolder.zan.setText(viewHolder.msg.getCountOfZan() + "");
            if (viewHolder.msg.isZhuanfa()) {
                viewHolder.zhuanfa.setVisibility(View.VISIBLE);
                viewHolder.zhuan_content.setText(viewHolder.msg.getZhuanfaContent());
                viewHolder.zhuan_from_who.setText(viewHolder.msg.getZhuanfaFromwho());
            } else {
                viewHolder.zhuanfa.setVisibility(View.GONE);
            }
        }
        return view;
    }

    class ViewHolder {
        LinearLayout zhuanfa;
        TextView user_name;
        TextView content;
        TextView zhuan_content;
        TextView zhuan_from_who;
        TextView time;
        TextView zan;
        RelativeLayout buttom;
        TextView count_pinglun;
        TextView pinglun;
        LinearLayout dian_zan;
        CircleMsg msg;
        int position;
    }



}
