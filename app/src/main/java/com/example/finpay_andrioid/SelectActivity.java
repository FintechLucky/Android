package com.example.finpay_andrioid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectActivity extends AppCompatActivity {
    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;


    private static final String TAG = "SelectActivity";

    Dataservice dataService = new Dataservice();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_make);

        Intent intent = getIntent();

        // 빈 리스트 생성
        ArrayList<String> items = new ArrayList<String>() ;
        dataService.userList.userList().enqueue(new Callback<List<UserDto>>() {
            @Override
            public void onResponse(Call<List<UserDto>> call, Response<List<UserDto>> response) {
                for (UserDto userDto : response.body()) {
                    Log.d(TAG, userDto.getUser_id());
                    items.add(userDto.getUser_id());
                }


            }

            @Override
            public void onFailure(Call<List<UserDto>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        Log.d(TAG, String.valueOf(items));
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
