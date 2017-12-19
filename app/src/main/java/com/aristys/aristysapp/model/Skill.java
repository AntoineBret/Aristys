package com.aristys.aristysapp.model;

public class Skill {
  public String skilltitle;
  public int skillthumbnail;

  public Skill(String skilltitle, int skillthumbnail) {
    this.skilltitle = skilltitle;
    this.skillthumbnail = skillthumbnail;
  }

  public String getSkilltitle() {
    return skilltitle;
  }

  public int getSkillthumbnail() {
    return skillthumbnail;
  }

}

