package com.example.finpay_andrioid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PaymainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymain);

        Button btn_start = (Button) findViewById(R.id.newpay);
        btn_start.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),NewpayActivity.class);
                startActivity(intent);
            }
        });

        Button btn_QR = (Button) findViewById(R.id.QR);
        btn_QR.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),QRActivity.class);
                startActivity(intent);
            }
        });

        Button btn_check = (Button) findViewById(R.id.paycheck);
        btn_check.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent);
            }
        });

    }
}
