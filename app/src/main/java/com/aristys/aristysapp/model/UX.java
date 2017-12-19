package com.aristys.aristysapp.model;

public class UX {

  public String uxtitle, uxsubtitle, uxdesc;
  public int uxthumbnail;

  public UX(String uxtitle, String uxsubtitle, String uxdesc, int uxthumbnail) {
    this.uxtitle = uxtitle;
    this.uxsubtitle = uxsubtitle;
    this.uxdesc = uxdesc;
    this.uxthumbnail = uxthumbnail;
  }

  public String getUxtitle() {
    return uxtitle;
  }

  public String getUxsubtitle() {
    return uxsubtitle;
  }

  public int getUxthumbnail() {
    return uxthumbnail;
  }

  public String getUxdesc() {
    return uxdesc;
  }
}

