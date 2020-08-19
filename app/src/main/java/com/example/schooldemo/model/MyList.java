package com.example.schooldemo.model;

import com.google.gson.annotations.SerializedName;

public class MyList {
  @SerializedName("title")
  public String title = "";

  @SerializedName("descrition")
  public String descrition = "";

  @SerializedName("eventDate")
  public String eventDate = "";

  @SerializedName("createdDate")
  public String createdDate = "";
  @SerializedName("standard")
  public String standard = "";
  @SerializedName("section")
  public String section = "";

  public MyList(String title, String descrition, String eventDate, String createdDate, String standard, String section) {
    this.title = title;
    this.descrition = descrition;
    this.eventDate = eventDate;
    this.createdDate = createdDate;
    this.standard = standard;
    this.section = section;
  }

  public MyList() {
  }
}
