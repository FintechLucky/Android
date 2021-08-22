package com.example.finpay_andrioid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MemberListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<UserDto> userList;

    public MemberListAdapter(Context context, ArrayList<UserDto> userList){
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount(){
        return userList.size();
    }
    @Override
    public Object getItem(int i){
        return userList.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        View v = View.inflate(context, R.layout.user, null);

        TextView userid = (TextView) v.findViewById(R.id.id);
        userid.setText(userList.get(i).getUser_id());
        v.setTag(userList.get(i).getUser_id());

        return v;
    }
}
