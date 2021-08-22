package com.example.finpay_andrioid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class UserListAdapter extends BaseAdapter {

    private Context context;
    private List<User> userList;

    public UserListAdapter(Context context, List<User> userList){
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

        CheckBox state = (CheckBox) v.findViewById(R.id.selectstate);
        TextView userid = (TextView) v.findViewById(R.id.id);

        userid.setText(userList.get(i).getUser_id());

        v.setTag(userList.get(i).getUser_id());

        return v;
    }
    

}
