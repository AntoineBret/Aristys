package com.aristys.aristysapp.model;

public class EcoPartner {

  public String ecopartnertitle, ecopartnersubtitle, ecopartnerdesc;
  public int ecopartnerthumbnail;

  public EcoPartner(String ecopartnertitle, String ecopartnersubtitle, String ecopartnerdesc, int ecopartnerthumbnail) {
    this.ecopartnertitle = ecopartnertitle;
    this.ecopartnersubtitle = ecopartnersubtitle;
    this.ecopartnerdesc = ecopartnerdesc;
    this.ecopartnerthumbnail = ecopartnerthumbnail;
  }

  public String getEcopartnertitle() {
    return ecopartnertitle;
  }

  public String getEcopartnersubtitle() {
    return ecopartnersubtitle;
  }

  public int getEcopartnerthumbnail() {
    return ecopartnerthumbnail;
  }

  public String getEcopartnerdesc() {
    return ecopartnerdesc;
  }
}


