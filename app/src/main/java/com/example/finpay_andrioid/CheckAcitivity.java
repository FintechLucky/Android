package com.example.finpay_andrioid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CheckAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        Button btn_start = (Button) findViewById(R.id.signup_button);
        btn_start.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MeetActivity.class);
                intent.putExtra("value_select", 1);
                startActivity(intent);
            }
        });
    }

}
