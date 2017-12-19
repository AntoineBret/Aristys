package com.aristys.aristysapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.EcoPartner;
import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;

import java.util.List;

public class EcoPartnerViewAdapter extends RecyclerView.Adapter <EcoPartnerViewAdapter.ViewHolder> {

  private Context context;

  public List<EcoPartner> ecoPartnerList;

  private LayoutInflater inflater = null;

  public EcoPartnerViewAdapter(Context context, List <EcoPartner> ecoPartnerList) {
    this.context = context;
    this.ecoPartnerList = ecoPartnerList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public EcoPartnerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ecopartner_cardview, viewGroup, false);
    return new EcoPartnerViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final EcoPartnerViewAdapter.ViewHolder holder, final int i) {
    holder.setIsRecyclable(false);
    final EcoPartner ecoPartner = ecoPartnerList.get(i);
    Glide.with(context).load(ecoPartner.getEcopartnerthumbnail()).into(holder.ecopartner_thumbnail);
    holder.ecopartner_title.setText(ecoPartner.getEcopartnertitle());
    holder.ecopartner_subtitle.setText(ecoPartner.getEcopartnersubtitle());
    holder.ecopartner_desc.setText(ecoPartner.getEcopartnerdesc());

  }

  @Override
  public int getItemCount() {
    return ecoPartnerList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView ecopartner_title;
    public TextView ecopartner_subtitle;
    public DocumentView ecopartner_desc;
    private ImageView ecopartner_thumbnail;

    public ViewHolder(View view) {
      super(view);

      ecopartner_title = (TextView) view.findViewById(R.id.ecopartner_title);
      ecopartner_subtitle = (TextView) view.findViewById(R.id.ecopartner_subtitle);
      ecopartner_thumbnail = (ImageView) view.findViewById(R.id.ecopartner_thumbnail);
      ecopartner_desc = (DocumentView) view.findViewById(R.id.ecopartner_desc);
    }
  }
}
