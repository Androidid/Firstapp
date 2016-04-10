package com.example.zzb.firstapp.Second;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzb.firstapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zzb on 2016/3/7.
 */
public class ChatActivity extends Activity implements View.OnClickListener {
    private String title_value;
    private ListView listView;
    private ChatContent chatContent;
    public static ArrayList<ChatContent>list = new ArrayList<ChatContent>();
    private MyChatAdapter myChatAdapter;
    private EditText editText;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        Intent intent = getIntent();
        TextView textView = (TextView)findViewById(R.id.chat_title);
        textView.setText(intent.getStringExtra("title"));
      //  Toast.makeText(this,intent.getStringExtra("title"),Toast.LENGTH_SHORT).show();
        int id = getIntent().getIntExtra("id",-1);
        SharedPreferences pref = getSharedPreferences("chatrecord",MODE_PRIVATE);
        if(pref.getBoolean(id+"",false)==true){
            list = new ArrayList<ChatContent>();
            String data = pref.getString(id+"value",null);
            String []a =data.split("#");

            for(String i : a){

              list.add(new ChatContent(i));
            }
        }else{
            list = new ArrayList<ChatContent>();
        }
        listView = (ListView)findViewById(R.id.chat_listview);
        myChatAdapter = new MyChatAdapter(this,android.R.layout.simple_list_item_1,list,System.currentTimeMillis());
        listView.setAdapter(myChatAdapter);
        editText = (EditText)findViewById(R.id.chat_edittext);
        send = (Button)findViewById(R.id.chat_send);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chat_send:
                if(!TextUtils.isEmpty(editText.getText())){
                    chatContent = new ChatContent(editText.getText().toString(),ChatContent.CHAT_RIGHT);
                    SimpleDateFormat sdf   = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String data  = sdf.format(new Date(System.currentTimeMillis()));
                    chatContent.setSendtime(data);
                    chatContent.setLongtime(System.currentTimeMillis());

                    if(list.size()>=1){
                        chatContent.setDistance(chatContent.getLongtime()-list.get(list.size()-1).getLongtime());
                    }else{
                        chatContent.setDistance(8000000);
                    }

                    list.add(chatContent);
                   myChatAdapter.notifyDataSetChanged();
                    listView.setSelection(list.size());
                    editText.setText("");
                }else{
                    Toast.makeText(this,"内容不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = getIntent();
        int id = getIntent().getIntExtra("id", 1);
        SharedPreferences.Editor editor = getSharedPreferences("chatrecord",MODE_PRIVATE).edit();
        String temp = "";
        if(list.size()>0) {
            for (ChatContent l : list) {
                temp += l.getType() + "," + l.getMessage() + "," + l.getSendtime() + "," + l.getLongtime() + "," + l.getDistance();
                temp += "#";
            }
            editor.putString(id + "value", temp);
            editor.putBoolean(id + "", true);
            editor.commit();
        }
        String data;
        if(list.size()>0) {
            data = list.get(list.size() - 1).getSendtime();

            Second_Item second_item = SecondFragment.list.get(id);
            second_item.setDate(data);
            ChatContent c = list.get(list.size() - 1);
            if (c.getType() == ChatContent.CHAT_LEFT) {
                second_item.setMessage(second_item.getTitle() + ":" + c.getMessage());
            } else {
                second_item.setMessage("我:" + c.getMessage());
            }
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
