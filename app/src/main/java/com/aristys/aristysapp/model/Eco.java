package com.aristys.aristysapp.model;

public class Eco {

  public String ecotitle, ecosubtitle, ecodesc;
  public int ecothumbnail;

  public Eco(String ecotitle, String ecosubtitle, String ecodesc, int ecothumbnail) {
    this.ecotitle = ecotitle;
    this.ecosubtitle = ecosubtitle;
    this.ecodesc = ecodesc;
    this.ecothumbnail = ecothumbnail;
  }

  public String getEcotitle() {
    return ecotitle;
  }

  public String getEcosubtitle() {
    return ecosubtitle;
  }

  public int getEcothumbnail() {
    return ecothumbnail;
  }

  public String getEcodesc() {
    return ecodesc;
  }
}

