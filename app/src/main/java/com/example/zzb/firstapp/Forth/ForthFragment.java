package com.example.zzb.firstapp.Forth;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zzb.firstapp.R;

/**
 * Created by zzb on 2016/3/1.
 */
public class ForthFragment extends Fragment implements OnClickListener{

    private LinearLayout friendCircle;
    private ImageView redPoint;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forthfragment,container,false);

        friendCircle=(LinearLayout)view.findViewById(R.id.friend_circle_button4);
        redPoint=(ImageView)view.findViewById(R.id.red_point_image);

        friendCircle.setOnClickListener(this);

        return view;
    }


    public void onClick(View arg0) {

        switch (arg0.getId()) {
            case R.id.friend_circle_button4:
                redPoint.setVisibility(View.GONE);
                Intent intent=new Intent(getActivity(),FriendCircleActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }

    }
}
