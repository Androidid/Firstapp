package com.example.zzb.firstapp.Second;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zzb.firstapp.R;

import java.util.List;

/**
 * Created by zzb on 2016/3/7.
 */
public class MySecondAdapter extends ArrayAdapter<Second_Item> {

    private  int resourceId;
    private Context context;
    private TextView title;
    private TextView message;
    private TextView date;

    public MySecondAdapter(Context context, int resource, List<Second_Item> objects) {
        super(context, resource, objects);
        this.context  = context;
        this.resourceId = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.second_listview_layout,null);
        Second_Item item  =getItem(position);
        title = (TextView)view.findViewById(R.id.second_list_title);
        message = (TextView)view.findViewById(R.id.second_list_message);
        date = (TextView)view.findViewById(R.id.second_list_date);
        title.setText(item.getTitle());
        String _message = item.getMessage();
        if(_message.length()<=10) {
            message.setText(_message);
        }else {
            message.setText(_message.substring(0, 10) + "...");

        }
        date.setText(item.getDate());

        return view;
    }

}
