package com.example.zzb.firstapp.Second;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zzb.firstapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzb on 2016/3/8.
 */
public class MyChatAdapter extends ArrayAdapter<ChatContent> {


    private List<ChatContent> list;
    private int resourceId;
    private Context context;
    private Viewholder viewholder;
    private static long currenttime;

    public MyChatAdapter(Context context, int resource, List<ChatContent> objects,long currenttime) {
        super(context, resource, objects);
        this.list = objects;
        this.resourceId = resource;
        this.context = context;
        this.currenttime = currenttime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view ;
        if(convertView == null) {
            view= LayoutInflater.from(context).inflate(R.layout.chat_listitem_layout,null);
            viewholder = new Viewholder();
            viewholder.leftlayout = (LinearLayout) view.findViewById(R.id.chat_left_linearlayout);
            viewholder.rightlayout = (LinearLayout) view.findViewById(R.id.chat_right_linearlayout);
            viewholder.lefttextview = (TextView) view.findViewById(R.id.chat_left_textview);
            viewholder.righttextview = (TextView) view.findViewById(R.id.chat_right_textview);
            viewholder.timetextview = (TextView)view.findViewById(R.id.chat_time_textview);
            viewholder.timelayout = (LinearLayout)view.findViewById(R.id.chat_time_layout);
            view.setTag(viewholder);
        }else {
            view = convertView;
            viewholder = (Viewholder)view.getTag();
        }
        SimpleDateFormat sdf   = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String data  = sdf.format(new Date(System.currentTimeMillis()));
            ChatContent chatContent = getItem(position);

            if(ChatActivity.list.size()>1) {

                if(chatContent.getDistance()<=60000){
                    viewholder.timelayout.setVisibility(View.GONE);

                }else{
                    viewholder.timelayout.setVisibility(View.VISIBLE);
                    viewholder.timetextview.setText(chatContent.getSendtime());
                }
            }else{
                viewholder.timetextview.setText(chatContent.getSendtime()+"");
                MyChatAdapter.currenttime = System.currentTimeMillis();
            }
            if (chatContent.getType() == ChatContent.CHAT_LEFT) {
                viewholder.rightlayout.setVisibility(View.GONE);
                viewholder.leftlayout.setVisibility(View.VISIBLE);
                viewholder.lefttextview.setText(chatContent.getMessage());
            } else if (chatContent.getType() == ChatContent.CHAT_RIGHT) {
                viewholder.leftlayout.setVisibility(View.GONE);
                viewholder.rightlayout.setVisibility(View.VISIBLE);
                viewholder.righttextview.setText(chatContent.getMessage());
            }
        return view;
    }
    class Viewholder{
        public LinearLayout leftlayout;
        public LinearLayout rightlayout;
        public TextView lefttextview;
        public TextView righttextview;
        public TextView timetextview;
        public LinearLayout timelayout;
    }
}
