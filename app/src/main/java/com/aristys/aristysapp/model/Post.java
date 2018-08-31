package com.aristys.aristysapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post <T> {

  public Post() {
  }

  @SerializedName("posts")
  @Expose
  private List<Post> posts;

  @SerializedName("title")
  @Expose
  private String title;

  @SerializedName("date")
  @Expose
  private String date;

  @SerializedName("featured_image")
  @Expose
  private String imgURL;

  @SerializedName("content")
  @Expose
  private String content;

    public Post(List<Post> posts, String title, String date, String imgURL, String content) {
        this.posts = posts;
        this.title = title;
        this.date = date;
        this.imgURL = imgURL;
        this.content = content;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}