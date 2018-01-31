package com.aristys.aristysapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.Mobile;
import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class OtherMobileViewAdapter extends RecyclerView.Adapter<OtherMobileViewAdapter.ViewHolder> {

  private Context context;

  public List<Mobile> mobileList;

  private LayoutInflater inflater = null;

  public OtherMobileViewAdapter(Context context, List<Mobile> mobileList) {
    this.context = context;
    this.mobileList = mobileList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public OtherMobileViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.othermobile_cardview, viewGroup, false);
    return new OtherMobileViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final OtherMobileViewAdapter.ViewHolder holder, final int i) {
    holder.setIsRecyclable(false);
    final Mobile mobile = mobileList.get(i);
    Glide
      .with(context)
      .load(mobile.getMobilethumbnail())
      .apply(new RequestOptions().override(600, 200))
      .into(holder.mobile_thumbnail);
    holder.mobile_title.setText(mobile.getMobiletitle());
    holder.mobile_subtitle.setText(mobile.getMobilesubtitle());
    holder.mobile_desc.setText(mobile.getMobiledesc());

  }

  @Override
  public int getItemCount() {
    return mobileList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView mobile_title;
    public TextView mobile_subtitle;
    public DocumentView mobile_desc;
    private ImageView mobile_thumbnail;

    public ViewHolder(View view) {
      super(view);

      mobile_title = (TextView) view.findViewById(R.id.mobile_title);
      mobile_subtitle = (TextView) view.findViewById(R.id.mobile_subtitle);
      mobile_thumbnail = (ImageView) view.findViewById(R.id.mobile_thumbnail);
      mobile_desc = (DocumentView) view.findViewById(R.id.mobile_desc);
    }
  }
}
