package com.aristys.aristysapp.ui.web.superior;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.Superior;
import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;

import java.util.List;

public class WebSuperiorViewAdapter extends RecyclerView.Adapter <WebSuperiorViewAdapter.ViewHolder> {

  private Context context;

  public List<Superior> superiorList;

  private LayoutInflater inflater = null;

  public WebSuperiorViewAdapter(Context context, List <Superior> superiorList) {
    this.context = context;
    this.superiorList = superiorList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public WebSuperiorViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.websuperior_cardview, viewGroup, false);
    return new WebSuperiorViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final WebSuperiorViewAdapter.ViewHolder holder, final int i) {
    holder.setIsRecyclable(false);
    final Superior superior = superiorList.get(i);
    Glide.with(context).load(superior.getSuperiorthumbnail()).into(holder.superior_thumbnail);
    holder.superior_title.setText(superior.getSuperiortitle());
    holder.superior_subtitle.setText(superior.getSuperiorsubtitle());
    holder.superior_desc.setText(superior.getSuperiordesc());

  }

  @Override
  public int getItemCount() {
    return superiorList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView superior_title;
    public TextView superior_subtitle;
    public DocumentView superior_desc;
    private ImageView superior_thumbnail;

    public ViewHolder(View view) {
      super(view);

      superior_title = (TextView) view.findViewById(R.id.superior_title);
      superior_subtitle = (TextView) view.findViewById(R.id.superior_subtitle);
      superior_thumbnail = (ImageView) view.findViewById(R.id.superior_thumbnail);
      superior_desc = (DocumentView) view.findViewById(R.id.superior_desc);
    }
  }
}

