package com.example.schooldemo.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyClient {
    private static final String BASE_URL="http://demo8861484.mockable.io/";
    private static MyClient myClient;
    private Retrofit retrofit;
    private MyClient(){
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized MyClient getInstance(){
        if (myClient==null){
            myClient=new MyClient();
        }
        return myClient;
    }
    public MyApi getMyApi(){
        return retrofit.create(MyApi.class);
    }
}
