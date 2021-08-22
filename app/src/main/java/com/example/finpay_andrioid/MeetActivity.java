package com.example.finpay_andrioid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeetActivity extends AppCompatActivity {

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing Member
    public static final String USER_ID_KEY = "user_id_key";

    private static final String TAG = "MeetActivity";

    // variable for shared preferences.
    SharedPreferences sharedPreferences;
    String user_id;
    int value = 0;
    int value1 = 0;

    Dataservice dataService = new Dataservice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ArrayList<String> userList = this.InitializeData();

        // initializing our shared preferences.
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        user_id = sharedPreferences.getString(USER_ID_KEY,null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet);

        Intent secondIntent = getIntent();
        value = secondIntent.getIntExtra("value",0);
        value1 = secondIntent.getIntExtra("value_select", 0);

        Button btn_request = (Button)findViewById(R.id.request_test);

        if (value == 1){
            btn_request.setVisibility(View.VISIBLE);
        }

        Button btn_koscom = (Button) findViewById(R.id.test2);

        if(value1 == 1){
            btn_koscom.setVisibility(View.VISIBLE);
        }

        final Button user_name = findViewById(R.id.mypage);
        user_name.setText(user_id);

        Button btn_start = (Button) findViewById(R.id.signup_button);
        btn_start.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),SelectActivity.class);
                intent.putExtra("userList", userList);
                startActivity(intent);
            }
        });

        Button btn_test = (Button) findViewById(R.id.test);
        btn_test.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), PaymainActivity.class);
                startActivity(intent);
            }

        });

        Button btn_lucky = (Button) findViewById(R.id.request_test);
        btn_lucky.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CheckAcitivity.class);
                startActivity(intent);
            }
        });
    }

    public ArrayList<String> InitializeData() {
        ArrayList<String> items = new ArrayList<>();
        dataService.userList.userList().enqueue(new Callback<List<UserDto>>() {
            @Override
            public void onResponse(Call<List<UserDto>> call, Response<List<UserDto>> response) {
                for (UserDto userDto : response.body()) {
                    if (!userDto.getUser_id().equals(user_id)) {
                        Log.d(TAG, userDto.getUser_id());
                        items.add(userDto.getUser_id());
                    }
                }
                Log.d(TAG, String.valueOf(items));
            }


            @Override
            public void onFailure(Call<List<UserDto>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return items;
    }

}