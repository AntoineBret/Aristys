package com.aristys.aristysapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.Design;
import com.bumptech.glide.Glide;

import java.util.List;

public class StartDesignViewAdapter extends RecyclerView.Adapter <StartDesignViewAdapter.ViewHolder> {

  private Context context;

  public List <Design> designList;

  private LayoutInflater inflater = null;

  public StartDesignViewAdapter(Context context, List <Design> designList) {
    this.context = context;
    this.designList = designList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public StartDesignViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.startdesign_cardview, viewGroup, false);
    return new StartDesignViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final StartDesignViewAdapter.ViewHolder holder, final int i) {
    holder.setIsRecyclable(false);
    final Design design = designList.get(i);
    Glide.with(context).load(design.getDesignimage()).into(holder.design_thumbnail);
    holder.design_title.setText(design.getDesigntitle());
  }

  @Override
  public int getItemCount() {
    return designList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView design_title;
    private ImageView design_thumbnail;

    public ViewHolder(View view) {
      super(view);

      design_thumbnail = (ImageView) view.findViewById(R.id.design_thumbnail);
      design_title = (TextView) view.findViewById(R.id.design_title);
    }
  }
}
