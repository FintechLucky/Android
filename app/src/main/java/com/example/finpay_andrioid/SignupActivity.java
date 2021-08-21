package com.example.finpay_andrioid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignUpPage";
    Dataservice dataService = new Dataservice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //개인 정보
        EditText name = findViewById(R.id.edit_name);
        EditText id = findViewById(R.id.edit_id);
        EditText pw = findViewById(R.id.edit_pw);
        EditText pw_check = findViewById(R.id.edit_pw_check);

        //카드 정보
        EditText company = findViewById(R.id.edit_company);
        EditText card = findViewById(R.id.edit_number);
        EditText day = findViewById(R.id.edit_day);
        EditText cvc = findViewById(R.id.edit_cvc);

        //SignUp
        Button btn_signUp = findViewById(R.id.signup_button);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pw.getText().toString().equals(pw_check.getText().toString())) {
                    Map<String, String> map = new HashMap<>();
                    map.put("user_id", id.getText().toString());
                    map.put("user_name", name.getText().toString());
                    map.put("user_pass", pw.getText().toString());
                    map.put("card_company", company.getText().toString());
                    map.put("card_number", card.getText().toString());
                    map.put("card_validity", day.getText().toString());
                    map.put("card_cvc", cvc.getText().toString());
                    dataService.singUp.signUp(map).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.code() == 500) {
                                id.setText("");
                                id.setHint("이미 존재하는 회원입니다.");
                                id.setHintTextColor(Color.RED);
                            } else {
                                Toast.makeText(SignupActivity.this, "회원 가입 완료", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent1);
                            }

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.d(TAG, t.toString());
                            t.printStackTrace();
                        }
                    });
                } else {
                    pw_check.setText("");
                    pw_check.setHint("비밀번호가 일치하지 않습니다.");
                    pw_check.setHintTextColor(Color.RED);
                }
            }
        });


    }

}