package com.aristys.aristysapp.Object;

public class Start {
  private String start_name;
  private int start_thumbnail;

  public Start() {
  }

  public Start(String name, int thumbnail) {
    this.start_name = name;
    this.start_thumbnail = thumbnail;
  }

  public String getName() {
    return start_name;
  }

  public void setName(String name) {
    this.start_name = name;
  }

  public int getThumbnail() {
    return start_thumbnail;
  }

  public void setThumbnail(int thumbnail) {
    this.start_thumbnail = thumbnail;
  }
}
