package com.aristys.aristysapp.model;

public class Solution {

  public String solutiontitle, solutionsubtitle, solutiondesc;
  public int solutionthumbnail;

  public Solution(String solutiontitle, String solutionsubtitle, String solutiondesc, int solutionthumbnail) {
    this.solutiontitle = solutiontitle;
    this.solutionsubtitle = solutionsubtitle;
    this.solutiondesc = solutiondesc;
    this.solutionthumbnail = solutionthumbnail;
  }

  public String getSolutiontitle() {
    return solutiontitle;
  }

  public String getSolutionsubtitle() {
    return solutionsubtitle;
  }

  public int getSolutionthumbnail() {
    return solutionthumbnail;
  }

  public String getSolutiondesc() {
    return solutiondesc;
  }
}

