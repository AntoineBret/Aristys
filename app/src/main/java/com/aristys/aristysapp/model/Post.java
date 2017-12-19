package com.aristys.aristysapp.model;

import android.text.Html;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

  public String title, date, imgURL, content;

  public static Post parse (JSONObject object) {
    Post post = new Post();
    post.title= Html.fromHtml(object.optString("title")).toString();
    post.date=getDateFormatted(object.optString("date"));
    post.imgURL=object.optString("featured_image");
    post.content=object.optString("content");

    return post;
  }

    private static String getDateFormatted(String date) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String s=df.format(new Date());
        return s;
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

}
