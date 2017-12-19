package com.aristys.aristysapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Progress implements Parcelable {

  private String mMessage;
  private String mTitle;
  private int mImage;
  private OrderStatus mStatus;

  public Progress() {
  }

  public Progress(String mMessage, String mTitle, int mImage, OrderStatus mStatus) {
    this.mMessage = mMessage;
    this.mTitle = mTitle;
    this.mImage = mImage;
    this.mStatus = mStatus;
  }

  public String getMessage() {
    return mMessage;
  }

  public void semMessage(String message) {
    this.mMessage = message;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    this.mTitle = title;
  }

  public int getImage() {
    return mImage;
  }

  public void setImage(int image) {
    this.mImage = image;
  }

  public OrderStatus getStatus() {
    return mStatus;
  }

  public void setStatus(OrderStatus mStatus) {
    this.mStatus = mStatus;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.mMessage);
    dest.writeString(this.mTitle);
    dest.writeInt(this.mStatus == null ? -1 : this.mStatus.ordinal());
  }

  protected Progress(Parcel in) {
    this.mMessage = in.readString();
    this.mTitle = in.readString();
    int tmpMStatus = in.readInt();
    this.mStatus = tmpMStatus == -1 ? null : OrderStatus.values()[tmpMStatus];
  }

  public static final Parcelable.Creator<Progress> CREATOR = new Parcelable.Creator<Progress>() {
    @Override
    public Progress createFromParcel(Parcel source) {
      return new Progress(source);
    }

    @Override
    public Progress[] newArray(int size) {
      return new Progress[size];
    }
  };
}
