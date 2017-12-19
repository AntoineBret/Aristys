package com.aristys.aristysapp.model;

public class Design {

    public String designtitle;
    public int designimage;

    public Design(String title, int image) {
        this.designtitle = title;
        this.designimage = image;
    }

    public String getDesigntitle() {
        return designtitle;
    }

    public int getDesignimage() {
        return designimage;
    }

}

