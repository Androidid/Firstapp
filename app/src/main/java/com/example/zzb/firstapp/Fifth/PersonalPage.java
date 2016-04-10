package com.example.zzb.firstapp.Fifth;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.example.zzb.firstapp.R;

/**
 * Created by LiuGuoJie on 2016/4/9.
 */
public class PersonalPage extends Activity implements PullScrollView.OnTurnListener {
    private PullScrollView myScrollView;
    private ImageView Img;
    private TableLayout myTableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fifth_personal_page);

        init();

    }
    void init(){
        myScrollView=(PullScrollView)findViewById(R.id.scroll_View);
        Img=(ImageView)findViewById(R.id.fifth_personal_background);
        myTableLayout=(TableLayout)findViewById(R.id.table_layout);
        myScrollView.setImg(Img);
        myScrollView.setOnturnlistener(this);
    }
    @Override
    public void onTurn() {

    }
}
