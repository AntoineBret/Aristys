package com.aristys.aristysapp.model;

public class Service {

  public String servicetitle, servicesubtitle, servicedesc;
  public int servicethumbnail;

  public Service(String servicetitle, String servicesubtitle, String servicedesc, int servicethumbnail) {
    this.servicetitle = servicetitle;
    this.servicesubtitle = servicesubtitle;
    this.servicedesc = servicedesc;
    this.servicethumbnail = servicethumbnail;
  }

  public String getServicetitle() {
    return servicetitle;
  }

  public String getServicesubtitle() {
    return servicesubtitle;
  }

  public int getServicethumbnail() {
    return servicethumbnail;
  }

  public String getServicedesc() {
    return servicedesc;
  }
}

