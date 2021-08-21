package com.example.finpay_andrioid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MeetActivity extends AppCompatActivity {

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing Member
    public static final String USER_ID_KEY = "user_id_key";

    // variable for shared preferences.
    SharedPreferences sharedPreferences;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // initializing our shared preferences.
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        user_id = sharedPreferences.getString(USER_ID_KEY,null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet);
        final Button user_name = findViewById(R.id.mypage);
        user_name.setText(user_id);

        Button btn_start = (Button) findViewById(R.id.signup_button);
        btn_start.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),SelectActivity.class);
                startActivity(intent);
            }
        });
    }

}