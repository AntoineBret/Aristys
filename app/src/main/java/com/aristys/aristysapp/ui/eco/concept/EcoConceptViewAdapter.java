package com.aristys.aristysapp.ui.eco.concept;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.Eco;
import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;

import java.util.List;

public class EcoConceptViewAdapter extends RecyclerView.Adapter <EcoConceptViewAdapter.ViewHolder> {

  private Context context;

  public List<Eco> ecoList;

  private LayoutInflater inflater = null;

  public EcoConceptViewAdapter(Context context, List <Eco> ecoList) {
    this.context = context;
    this.ecoList = ecoList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public EcoConceptViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ecoconcept_cardview, viewGroup, false);
    return new EcoConceptViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final EcoConceptViewAdapter.ViewHolder holder, final int i) {
    holder.setIsRecyclable(false);
    final Eco eco = ecoList.get(i);
    Glide.with(context).load(eco.getEcothumbnail()).into(holder.eco_thumbnail);
    holder.eco_title.setText(eco.getEcotitle());
    holder.eco_subtitle.setText(eco.getEcosubtitle());
    holder.eco_desc.setText(eco.getEcodesc());

  }

  @Override
  public int getItemCount() {
    return ecoList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView eco_title;
    public TextView eco_subtitle;
    public DocumentView eco_desc;
    private ImageView eco_thumbnail;

    public ViewHolder(View view) {
      super(view);

      eco_title = (TextView) view.findViewById(R.id.eco_title);
      eco_subtitle = (TextView) view.findViewById(R.id.eco_subtitle);
      eco_thumbnail = (ImageView) view.findViewById(R.id.eco_thumbnail);
      eco_desc = (DocumentView) view.findViewById(R.id.eco_desc);
    }
  }
}
