package com.example.schooldemo.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schooldemo.MainActivity;
import com.example.schooldemo.api.MyApi;
import com.example.schooldemo.api.MyClient;
import com.example.schooldemo.model.MyList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyListViewModel extends ViewModel {
    public String title="";
    public String descrition="";
    public String eventDate="";
    public String createdDate="";
    public String standard="";
    public String section="";
    public  String module="";
    public MutableLiveData<ArrayList<MyListViewModel>> mutableLiveData=new MutableLiveData<>();
    private ArrayList<MyListViewModel>arrayList;
    //private UserReposin userReposin;
    private ArrayList<MyList>myList;
/*    public String getImageurl(){
        return artistimage;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadimage(ImageView imageView, String imageUrl){
        Glide.with(imageView.getContext()).load(imageUrl).apply(RequestOptions.circleCropTransform()).into(imageView);
        //Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    }*/

    public MyListViewModel(){

    }

    public MyListViewModel(MyList myList){
        this.title=myList.title;
        this.descrition=myList.descrition;
        this.eventDate= formattedTime(myList.eventDate);
        this.createdDate= "On "+formattedDate(myList.createdDate);
        this.standard="Class "+myList.standard;
        this.section=myList.section;
    }
    String formattedDate(String date){
      return new SimpleDateFormat("dd MMMM")
        .format(new Date(Integer.parseInt(date) * 1000L));
    }
  String formattedTime(String time){
    return new SimpleDateFormat("HH:mm a")
      .format(new Date(Integer.parseInt(time) * 1000L));
  }
    public MutableLiveData<ArrayList<MyListViewModel>> getMutableLiveData() {

        arrayList=new ArrayList<>();

        MyApi api= MyClient.getInstance().getMyApi();
      Call<ArrayList<MyList>> call;
        if(module.equals("class")){
          call=api.getClassData();
        }else {
          call=api.getData();
        }
        call.enqueue(new Callback<ArrayList<MyList>>() {
            @Override
            public void onResponse(Call<ArrayList<MyList>> call, Response<ArrayList<MyList>> response) {
                myList=new ArrayList<>();
                myList=response.body();
                System.out.println("list: "+myList);
                for (int i=0; i<myList.size(); i++){
                    MyList myk=myList.get(i);
                    MyListViewModel myListViewModel=new MyListViewModel(myk);
                    arrayList.add(myListViewModel);
                    mutableLiveData.setValue(arrayList);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<MyList>> call, Throwable t) {
            System.out.println("exception");
            }
        });
        return mutableLiveData;
    }

}
