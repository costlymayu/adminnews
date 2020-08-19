package com.example.schooldemo.api;

import com.example.schooldemo.model.MyList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {
    @GET("news/school")
    abstract Call<ArrayList<MyList>> getData();

  @GET("news/class")
  abstract Call<ArrayList<MyList>> getClassData();
}
