package com.aristys.aristysapp.model;

public class Superior {

  public String superiortitle, superiorsubtitle, superiordesc;
  public int superiorthumbnail;

  public Superior(String superiortitle, String superiorsubtitle, String superiordesc, int superiorthumbnail) {
    this.superiortitle = superiortitle;
    this.superiorsubtitle = superiorsubtitle;
    this.superiordesc = superiordesc;
    this.superiorthumbnail = superiorthumbnail;
  }

  public String getSuperiortitle() {
    return superiortitle;
  }

  public String getSuperiorsubtitle() {
    return superiorsubtitle;
  }

  public int getSuperiorthumbnail() {
    return superiorthumbnail;
  }

  public String getSuperiordesc() {
    return superiordesc;
  }
}

