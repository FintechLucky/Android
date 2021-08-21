package com.example.finpay_andrioid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LoginPage";
    Dataservice dataService = new Dataservice();

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing Member
    public static final String USER_ID_KEY = "user_id_key";

    // variable for shared preferences.
    SharedPreferences sharedPreferences;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // initializing our shared preferences.
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        user_id = sharedPreferences.getString(USER_ID_KEY,null);

        EditText id = findViewById(R.id.edit_id);
        EditText pw = findViewById(R.id.edit_pw);

        Button btn_start = (Button) findViewById(R.id.signup);
        btn_start.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
            }
        });

        Button btn_signup = (Button)findViewById(R.id.login);
        btn_signup.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Map<String, String> map = new HashMap<>();
                map.put("user_id", id.getText().toString());
                map.put("user_pass", pw.getText().toString());
                System.out.println(map);
                dataService.login.login(map).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        Log.d(TAG, String.valueOf(response.body()));
                        User result = response.body();
                        System.out.println(result);
                        if (result.getUser_id().equals("wrong_pass")) {
                            pw.setText("");
                            pw.setHint("비밀번호가 일치하지 않습니다.");
                            pw.setHintTextColor(Color.RED);
                        } else if (result.getUser_id().equals("no_user")) {
                            id.setText("");
                            id.setHint("존재하지 않는 회원입니다.");
                            id.setHintTextColor(Color.RED);
                        } else{
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString(USER_ID_KEY, result.getUser_id());

                            editor.apply();

                            Intent login = new Intent(getApplicationContext(), MeetActivity.class);
                            startActivity(login);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
    }
}