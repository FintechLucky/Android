package com.example.finpay_andrioid;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class Dataservice {

    private String Local_URL = "http://10.0.2.2:8080/"; //Local Host test
    private String AWS_URL = "http://221.168.38.227:8080"; // AWS EC2 URL

    Retrofit retrofitClient =
            new Retrofit.Builder()
                    .baseUrl(AWS_URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    SingUp singUp = retrofitClient.create(SingUp.class);
    Login login = retrofitClient.create(Login.class);
}

interface SingUp {
    @POST("user/new")
    Call<User> signUp(@Body Map<String, String> map);
}

interface Login {
    @POST("user/login")
    Call<User> login(@Body Map<String, String> map);
}
