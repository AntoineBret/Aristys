package com.aristys.aristysapp.model;


public class Safety {

  public String safetytitle, safetysubtitle, safetydesc;
  public int safetythumbnail;

  public Safety(String safetytitle, String safetysubtitle, String safetydesc, int safetythumbnail) {
    this.safetytitle = safetytitle;
    this.safetysubtitle = safetysubtitle;
    this.safetydesc = safetydesc;
    this.safetythumbnail = safetythumbnail;
  }

  public String getSafetytitle() {
    return safetytitle;
  }

  public String getSafetysubtitle() {
    return safetysubtitle;
  }

  public int getSafetythumbnail() {
    return safetythumbnail;
  }

  public String getSafetydesc() {
    return safetydesc;
  }
}

