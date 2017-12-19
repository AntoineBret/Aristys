package com.aristys.aristysapp.model;

public class Photo {
  public String phototitle, photosubtitle, photodesc;
  public int photothumbnail;

  public Photo(String phototitle, String photosubtitle, String photodesc, int photothumbnail) {
    this.phototitle = phototitle;
    this.photosubtitle = photosubtitle;
    this.photodesc = photodesc;
    this.photothumbnail = photothumbnail;
  }

  public String getPhototitle() {
    return phototitle;
  }

  public String getPhotosubtitle() {
    return photosubtitle;
  }

  public int getPhotothumbnail() {
    return photothumbnail;
  }

  public String getPhotodesc() {
    return photodesc;
  }
}

