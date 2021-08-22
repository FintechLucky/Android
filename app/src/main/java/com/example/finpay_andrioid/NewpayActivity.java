package com.example.finpay_andrioid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewpayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_newpay);

        Button btn_request = (Button) findViewById(R.id.signup_button);
        btn_request.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MeetActivity.class);
                intent.putExtra("value", 1);
                intent.putExtra("value_select", 1);
                startActivity(intent);
            }
        });

    }
}
