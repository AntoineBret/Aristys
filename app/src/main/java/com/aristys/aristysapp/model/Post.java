package com.aristys.aristysapp.model;

import android.text.Html;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Post {

  public static final String SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
  public static final String LOCAL_FORMAT = "yyyy-MM-dd";

  private String title, date, imgURL, content;

  public static Post parse(JSONObject object) {
    Post post = new Post();
    post.title = Html.fromHtml(object.optString("title")).toString();
    post.date = getDateFormatted(object.optString("date"));
    post.imgURL = object.optString("featured_image");
    post.content = object.optString("content");

    return post;
  }

  private static String getDateFormatted(String dateString) {
    String dateLocalString = "";

    SimpleDateFormat dfServer = new SimpleDateFormat(SERVER_FORMAT, Locale.getDefault());

    try {
      Date date = dfServer.parse(dateString);
      SimpleDateFormat dfLocal = new SimpleDateFormat(LOCAL_FORMAT, Locale.getDefault());
      dateLocalString = dfLocal.format(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return dateLocalString;
  }

  public String getTitle() {
    return title;
  }

  public String getDate() {
    return date;
  }

  public String getImgURL() {
    return imgURL;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
