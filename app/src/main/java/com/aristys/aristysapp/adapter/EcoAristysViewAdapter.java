package com.aristys.aristysapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.EcoAristys;
import com.aristys.aristysapp.model.Mobile;
import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;

import java.util.List;

public class EcoAristysViewAdapter extends RecyclerView.Adapter <EcoAristysViewAdapter.ViewHolder> {

  private Context context;

  private List<EcoAristys> ecoAristysList;

  private LayoutInflater inflater = null;

  public EcoAristysViewAdapter(Context context, List <EcoAristys> ecoAristysList) {
    this.context = context;
    this.ecoAristysList = ecoAristysList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public EcoAristysViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ecoaristys_cardview, viewGroup, false);
    return new EcoAristysViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final EcoAristysViewAdapter.ViewHolder holder, final int i) {
    holder.setIsRecyclable(false);
    final EcoAristys ecoaristys = ecoAristysList.get(i);
    Glide.with(context).load(ecoaristys.getEcoaristysthumbnail()).into(holder.ecoaristys_thumbnail);
    holder.ecoaristys_title.setText(ecoaristys.getEcoaristystitle());
    holder.ecoaristys_subtitle.setText(ecoaristys.getEcoaristyssubtitle());
    holder.ecoaristys_desc.setText(ecoaristys.getEcoaristysdesc());

  }

  @Override
  public int getItemCount() {
    return ecoAristysList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView ecoaristys_title;
    public TextView ecoaristys_subtitle;
    public DocumentView ecoaristys_desc;
    private ImageView ecoaristys_thumbnail;

    public ViewHolder(View view) {
      super(view);

      ecoaristys_title = (TextView) view.findViewById(R.id.ecoaristys_title);
      ecoaristys_subtitle = (TextView) view.findViewById(R.id.ecoaristys_subtitle);
      ecoaristys_thumbnail = (ImageView) view.findViewById(R.id.ecoaristys_thumbnail);
      ecoaristys_desc = (DocumentView) view.findViewById(R.id.ecoaristys_desc);
    }
  }
}
