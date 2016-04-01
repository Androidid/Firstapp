package com.example.zzb.firstapp.Fifth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zzb.firstapp.R;

/**
 * Created by zzb on 2016/3/30.
 */
public class RecommendSubscribe extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fifth_subscribe_listview,container,false);


        return view;
    }
}
