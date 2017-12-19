package com.aristys.aristysapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.Object.Start;
import com.aristys.aristysapp.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class StartAdapter extends RecyclerView.Adapter <StartAdapter.ViewHolder> {

  private Context context;
  private List <Start> startList;

  public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView start_title;
    public ImageView start_thumbnail;

    public ViewHolder(View view) {
      super(view);
      start_title = (TextView) view.findViewById(R.id.start_title);
      start_thumbnail = (ImageView) view.findViewById(R.id.start_thumbnail);
      start_thumbnail.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    }
  }

  public StartAdapter(Context context, List <Start> startList) {
    this.context = context;
    this.startList = startList;
  }

  @Override
  public StartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.start_card, parent, false);
    context = parent.getContext();
    return new ViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    Start start = startList.get(position);
    holder.start_title.setText(start.getName());

    Glide.with(context).load(start.getThumbnail()).into(holder.start_thumbnail);
  }

  @Override
  public int getItemCount() {
    return startList.size();
  }
}
