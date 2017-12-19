package com.aristys.aristysapp.model;

import org.json.JSONObject;

public class Board {

  private String id, name, desc, shortUrl;

  public static Board parse (JSONObject object) {
    Board board = new Board();
    board.id=object.optString("id");
    board.name=object.optString("name");
    board.desc=object.optString("desc");
    board.shortUrl=object.optString("shortUrl");

    return board;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDesc() {
    return desc;
  }

  public String getShortUrl() {
    return shortUrl;
  }

}
