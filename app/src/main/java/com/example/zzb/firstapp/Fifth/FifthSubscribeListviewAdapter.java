package com.example.zzb.firstapp.Fifth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.zzb.firstapp.R;

import java.util.List;

/**
 * Created by zzb on 2016/3/30.
 */
public class FifthSubscribeListviewAdapter extends ArrayAdapter{

    private List<MySubscribeMessage>list;
    private Context context;
    public FifthSubscribeListviewAdapter(Context context, int resource, List list) {
        super(context, resource, list);
        this.list= list;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.fifth_subscribe_listview_adapter,null);

    }
}
