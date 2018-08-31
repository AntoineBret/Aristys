package com.aristys.aristysapp.ui.other.photo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.Photo;
import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;

import java.util.List;

public class OtherPhotoViewAdapter extends RecyclerView.Adapter <OtherPhotoViewAdapter.ViewHolder> {

  private Context context;

  public List<Photo> photoList;

  private LayoutInflater inflater = null;

  public OtherPhotoViewAdapter(Context context, List <Photo> photoList) {
    this.context = context;
    this.photoList = photoList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public OtherPhotoViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.otherphoto_cardview, viewGroup, false);
    return new OtherPhotoViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final OtherPhotoViewAdapter.ViewHolder holder, final int i) {
    holder.setIsRecyclable(false);
    final Photo photo = photoList.get(i);
    Glide
      .with(context)
      .load(photo.getPhotothumbnail())
      .into(holder.photo_thumbnail);
    holder.photo_title.setText(photo.getPhototitle());
    holder.photo_subtitle.setText(photo.getPhotosubtitle());
    holder.photo_desc.setText(photo.getPhotodesc());
  }

  @Override
  public int getItemCount() {
    return photoList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView photo_title;
    public TextView photo_subtitle;
    public DocumentView photo_desc;
    private ImageView photo_thumbnail;

    public ViewHolder(View view) {
      super(view);

      photo_title = (TextView) view.findViewById(R.id.photo_title);
      photo_subtitle = (TextView) view.findViewById(R.id.photo_subtitle);
      photo_thumbnail = (ImageView) view.findViewById(R.id.photo_thumbnail);
      photo_desc = (DocumentView) view.findViewById(R.id.photo_desc);
    }
  }
}
