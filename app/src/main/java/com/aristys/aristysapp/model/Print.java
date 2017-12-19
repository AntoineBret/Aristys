package com.aristys.aristysapp.model;

public class Print {

  public String printtitle, printsubtitle, printdesc;
  public int printthumbnail;

  public Print(String printtitle, String printsubtitle, String printdesc, int printthumbnail) {
    this.printtitle = printtitle;
    this.printsubtitle = printsubtitle;
    this.printdesc = printdesc;
    this.printthumbnail = printthumbnail;
  }

  public String getPrinttitle() {
    return printtitle;
  }

  public String getPrintsubtitle() {
    return printsubtitle;
  }

  public int getPrintthumbnail() {
    return printthumbnail;
  }

  public String getPrintdesc() {
    return printdesc;
  }
}

