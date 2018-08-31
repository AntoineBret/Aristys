package com.aristys.aristysapp.ui.ux.describe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.UX;
import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;

import java.util.List;

public class UXViewAdapter extends RecyclerView.Adapter <UXViewAdapter.ViewHolder> {

  private Context context;

  public List<UX> uxList;

  private LayoutInflater inflater = null;

  public UXViewAdapter(Context context, List <UX> uxList) {
    this.context = context;
    this.uxList = uxList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public UXViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ux_cardview, viewGroup, false);
    return new UXViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final UXViewAdapter.ViewHolder holder, final int i) {
    holder.setIsRecyclable(false);
    final UX ux = uxList.get(i);
    Glide.with(context).load(ux.getUxthumbnail()).into(holder.ux_thumbnail);
    holder.ux_title.setText(ux.getUxtitle());
    holder.ux_subtitle.setText(ux.getUxsubtitle());
    holder.ux_desc.setText(ux.getUxdesc());

  }

  @Override
  public int getItemCount() {
    return uxList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView ux_title;
    public TextView ux_subtitle;
    public DocumentView ux_desc;
    private ImageView ux_thumbnail;

    public ViewHolder(View view) {
      super(view);

      ux_title = (TextView) view.findViewById(R.id.ux_title);
      ux_subtitle = (TextView) view.findViewById(R.id.ux_subtitle);
      ux_thumbnail = (ImageView) view.findViewById(R.id.ux_thumbnail);
      ux_desc = (DocumentView) view.findViewById(R.id.ux_desc);
    }
  }
}
