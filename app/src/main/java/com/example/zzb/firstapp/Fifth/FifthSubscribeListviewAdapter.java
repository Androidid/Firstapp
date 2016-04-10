package com.example.zzb.firstapp.Fifth;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzb.firstapp.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by zzb on 2016/3/30.
 */
public class FifthSubscribeListviewAdapter extends ArrayAdapter{

    public final static int MYSUBSCRIBE = 0;
    public final static int RECOMMENDSUBSCRIBE = 1;
    private int type;
    private TextView  title_textview;
    private TextView  message_textview;
    private List<MySubscribeMessage>list;
    private Context context;
    public FifthSubscribeListviewAdapter(Context context, int resource, List list,int type) {
        super(context, resource, list);
        this.context = context;
        this.list = list;
        this.type = type;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.fifth_subscribe_listview_adapter,null);
        Button button  = (Button )view.findViewById(R.id.fifth_subscribe_listview_adapter_button_cancel);
        title_textview = (TextView)view.findViewById(R.id.fifth_subscribe_listview_adapter_title);
        message_textview = (TextView)view.findViewById(R.id.fifth_subscribe_listview_adapter_message);
        MySubscribeMessage message  = list.get(position);
        title_textview.setText(message.getTitle());
        message_textview.setText(message.getMessage());
        if(type ==RECOMMENDSUBSCRIBE)
        {
            button.setText("订阅");

            button.setBackgroundColor(Color.GREEN);
        }
        else{
            button.setText("取消");

            button.setBackgroundColor(Color.GRAY);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type){
                    case FifthSubscribeListviewAdapter.MYSUBSCRIBE:
                        Toast.makeText(getContext(),"click 取消",Toast.LENGTH_SHORT).show();
                        break;
                    case FifthSubscribeListviewAdapter.RECOMMENDSUBSCRIBE:
                        Toast.makeText(getContext(),"click 确定",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return view;
    }
}
