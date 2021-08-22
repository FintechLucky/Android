package com.example.finpay_andrioid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectActivity extends AppCompatActivity {
    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing Member
    public static final String USER_ID_KEY = "user_id_key";

    // variable for shared preferences.
    SharedPreferences sharedPreferences;
    String user_id;

    private static final String TAG = "SelectActivity";
    Dataservice dataService = new Dataservice();

    @Override
    protected void onCreate(Bundle savedInstanceState){

        Intent users = getIntent();
        ArrayList<String> items = users.getStringArrayListExtra("userList");

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        user_id = sharedPreferences.getString(USER_ID_KEY,null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_make);

        Intent intent = getIntent();


        // ArrayAdapter 생성. 아이템 View를 선택(multiple choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter( this,android.R.layout.simple_list_item_multiple_choice, items) ;

        // listview 생성 및 adapter 지정.
        final ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        EditText meetInput = (EditText) findViewById(R.id.meetname);
        Button btn_start = (Button) findViewById(R.id.signup_button);
        btn_start.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){

                switch (v.getId()){
                    case R.id.signup_button:
                        SparseBooleanArray booleans = listView.getCheckedItemPositions();
                        StringBuilder sb = new StringBuilder();
                        List<String> userList = new ArrayList<>();

                        sb.append(user_id + " ");
                        userList.add(user_id);
                        for(int i =0; i< items.size(); i++) {
                            if (booleans.get(i)) {
                                sb.append(items.get(i)+" ");
                                userList.add(items.get(i));
                            }
                        }

                        Log.d(TAG, userList.get(0));
                        Log.d(TAG, sb.toString());
                        Toast.makeText(getApplicationContext(), meetInput.getText(), Toast.LENGTH_SHORT).show();

                        List<String> meetName = new ArrayList<>();
                        meetName.add(String.valueOf(meetInput.getText()));

                        Map<String, List<String>> map = new HashMap<>();
                        map.put("userList", userList);
                        map.put("meetName", meetName);
                        Log.d(TAG, String.valueOf(map));

                        dataService.newMeet.newMeet(map).enqueue(new Callback<MeetDto>() {
                            @Override
                            public void onResponse(Call<MeetDto> call, Response<MeetDto> response) {
                                Log.d(TAG, String.valueOf(response.body()));
                            }

                            @Override
                            public void onFailure(Call<MeetDto> call, Throwable t) {
                                t.printStackTrace();

                            }
                        });

                        Intent meet = new Intent(getApplicationContext(), MeetActivity.class);
                        startActivity(meet);

                        break;

                    default:
                        break;

                }
            }
        });

    }
}