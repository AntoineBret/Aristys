package com.aristys.aristysapp.model;

public class EcoAristys {

  public String ecoaristystitle, ecoaristyssubtitle, ecoaristysdesc;
  public int ecoaristysthumbnail;

  public EcoAristys(String ecoaristystitle, String ecoaristyssubtitle, String ecoaristysdesc, int ecoaristysthumbnail) {
    this.ecoaristystitle = ecoaristystitle;
    this.ecoaristyssubtitle = ecoaristyssubtitle;
    this.ecoaristysdesc = ecoaristysdesc;
    this.ecoaristysthumbnail = ecoaristysthumbnail;
  }

  public String getEcoaristystitle() {
    return ecoaristystitle;
  }

  public String getEcoaristyssubtitle() {
    return ecoaristyssubtitle;
  }

  public int getEcoaristysthumbnail() {
    return ecoaristysthumbnail;
  }

  public String getEcoaristysdesc() {
    return ecoaristysdesc;
  }
}

