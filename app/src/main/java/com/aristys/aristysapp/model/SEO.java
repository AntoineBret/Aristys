package com.aristys.aristysapp.model;

public class SEO {

  public String seotitle, seosubtitle, seodesc;
  public int seothumbnail;

  public SEO(String seotitle, String seosubtitle, String seodesc, int seothumbnail) {
    this.seotitle = seotitle;
    this.seosubtitle = seosubtitle;
    this.seodesc = seodesc;
    this.seothumbnail = seothumbnail;
  }

  public String getSeotitle() {
    return seotitle;
  }

  public String getSeosubtitle() {
    return seosubtitle;
  }

  public int getSeothumbnail() {
    return seothumbnail;
  }

  public String getSeodesc() {
    return seodesc;
  }
}

