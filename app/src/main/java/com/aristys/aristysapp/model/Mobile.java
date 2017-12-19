package com.aristys.aristysapp.model;

public class Mobile {

  public String mobiletitle, mobilesubtitle, mobiledesc;
  public int mobilethumbnail;

  public Mobile(String mobiletitle, String mobilesubtitle, String mobiledesc, int mobilethumbnail) {
    this.mobiletitle = mobiletitle;
    this.mobilesubtitle = mobilesubtitle;
    this.mobiledesc = mobiledesc;
    this.mobilethumbnail =mobilethumbnail;
  }

  public String getMobiletitle() {
    return mobiletitle;
  }

  public String getMobilesubtitle() {
    return mobilesubtitle;
  }

  public int getMobilethumbnail() {
    return mobilethumbnail;
  }

  public String getMobiledesc() {
    return mobiledesc;
  }
}

