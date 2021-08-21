package com.example.finpay_andrioid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity {
    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_make);

        Intent intent = getIntent();

        final ArrayList<String> items = new ArrayList<String>() ;

        // ArrayAdapter 생성. 아이템 View를 선택(multiple choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items) ;

        // listview 생성 및 adapter 지정.
        final ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter) ;

        Button btn_start = (Button) findViewById(R.id.signup_button);
        btn_start.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MeetActivity.class);
                startActivity(intent);
            }
        });

    }


}
