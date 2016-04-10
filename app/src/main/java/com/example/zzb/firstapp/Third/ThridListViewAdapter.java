package com.example.zzb.firstapp.Third;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzb.firstapp.R;
import com.handmark.pulltorefresh.library.PullToRefreshAdapterViewBase;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by zzb on 2016/4/8.
 */
public class ThridListViewAdapter extends ArrayAdapter implements View.OnClickListener{

    private List<SubscribeMessage> list;
    private ViewHolder viewHolder;
    private int position;
    private   SubscribeMessage message;
    public ThridListViewAdapter(Context context, int resource, List list) {
        super(context, resource, list);
        this.list = list;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =null;
        this.position = position;
        message = list.get(position);
        if(convertView == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(R.layout.third_item_layout,null);
            viewHolder.comment_button = (LinearLayout)view.findViewById(R.id.comment_button);
            viewHolder.comment_button.setOnClickListener(this);
            viewHolder.forward_button = (LinearLayout)view.findViewById(R.id.forward_button);
            viewHolder.forward_button.setOnClickListener(this);
            viewHolder.from_button = (TextView)view.findViewById(R.id.third_item_from);
            viewHolder.from_button.setOnClickListener(this);
            viewHolder.parise_button = (LinearLayout)view.findViewById(R.id.praise_button);
            viewHolder.parise_button.setOnClickListener(this);
            viewHolder.to_button = (TextView)view.findViewById(R.id.third_item_to);
            viewHolder.to_button.setOnClickListener(this);
            viewHolder.date = (TextView)view.findViewById(R.id.data_textview);
            viewHolder.title_button  = (TextView)view.findViewById(R.id.third_item_title);
            viewHolder.title_button.setOnClickListener(this);
            viewHolder.comment_count = (TextView)view.findViewById(R.id.third_comment_count);
            viewHolder.parise_count = (TextView)view.findViewById(R.id.third_praise_count);
            viewHolder.forward_count = (TextView)view.findViewById(R.id.third_forward_count);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder =(ViewHolder) convertView.getTag();
        }
            viewHolder.date.setText(message.getDate());
            viewHolder.to_button.setText(message.getTo());
            viewHolder.from_button.setText(message.getFrom());
            viewHolder.title_button.setText(message.getTitle());
            viewHolder.comment_count.setText(message.getCommentCount()+"");
            viewHolder.parise_count.setText(message.getPraiseCount()+"");
            viewHolder.forward_count.setText(message.getForwardCount()+"");
        return view;
    }

    @Override
    public void onClick(View v) {
       // Toast.makeText(getContext(),position+"",Toast.LENGTH_SHORT).show();
        switch (v.getId()){
            case R.id.comment_button:
       //       message.setCommentCount(message.getCommentCount()+1);
                Toast.makeText(getContext(),"click comment",Toast.LENGTH_SHORT).show();
                break;
            case R.id.praise_button:
                message.setPraiseCount(message.getPraiseCount() + 1);
                viewHolder.parise_count.setText(message.getPraiseCount()+"");
                Toast.makeText(getContext(),"click praise",Toast.LENGTH_SHORT).show();
                break;
            case R.id.forward_button:
       //         message.setForwardCount(message.getForwardCount()+1);
                Toast.makeText(getContext(),"click forward",Toast.LENGTH_SHORT).show();
                break;
            case R.id.third_item_title:
                Toast.makeText(getContext(),"click title",Toast.LENGTH_SHORT).show();
                break;
            case R.id.third_item_from:
                Toast.makeText(getContext(),"click from",Toast.LENGTH_SHORT).show();
                break;
            case R.id.third_item_to:
                Toast.makeText(getContext(),"click to",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class ViewHolder{
        public LinearLayout  forward_button;
        public LinearLayout  comment_button;
        public LinearLayout parise_button;
        public TextView  title_button;
        public TextView from_button;
        public TextView to_button;
        public TextView date;
        public TextView forward_count;
        public TextView comment_count;
        public TextView parise_count;
    }
}
