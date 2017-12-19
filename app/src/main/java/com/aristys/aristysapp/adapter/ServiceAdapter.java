package com.aristys.aristysapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.Service;
import com.bumptech.glide.Glide;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter <ServiceAdapter.ViewHolder> {

  private Context context;
  private List <Service> serviceList;

  public ServiceAdapter(Context context, List <Service> serviceList) {
    this.context = context;
    this.serviceList = serviceList;
  }

  @Override
  public ServiceAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_card, viewGroup, false);
    return new ServiceAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    Service service = serviceList.get(position);
    Glide.with(context).load(service.getServicethumbnail()).into(holder.service_thumbnail);
    holder.service_title.setText(service.getServicetitle());
    holder.service_subtitle.setText(service.getServicesubtitle());

    if(position==0)
      holder.relativeLayout.setBackgroundResource(R.color.colorPrimary);
    if(position==1)
      holder.relativeLayout.setBackgroundResource(R.color.colorPrimary);
    if(position==2)
      holder.relativeLayout.setBackgroundResource(R.color.orange);
    if(position==3)
      holder.relativeLayout.setBackgroundResource(R.color.green);
    if(position==4)
      holder.relativeLayout.setBackgroundResource(R.color.green);
    if(position==5)
      holder.relativeLayout.setBackgroundResource(R.color.colorBlack);
    if(position==6)
      holder.relativeLayout.setBackgroundResource(R.color.colorBlack);
  }

  @Override
  public int getItemCount() {
    return serviceList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView service_title, service_subtitle;
    private ImageView service_thumbnail;
    private RelativeLayout relativeLayout;

    public ViewHolder(View view) {
      super(view);
      relativeLayout = (RelativeLayout) view.findViewById(R.id.ln_service_thumbnail);
      service_title = (TextView) view.findViewById(R.id.mservice_title);
      service_subtitle = (TextView) view.findViewById(R.id.mservice_subtitle);
      service_thumbnail = (ImageView) view.findViewById(R.id.mservice_thumbnail);
    }
  }
}
