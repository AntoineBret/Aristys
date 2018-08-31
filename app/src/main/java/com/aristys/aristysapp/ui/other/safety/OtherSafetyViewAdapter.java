package com.aristys.aristysapp.ui.other.safety;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.Safety;
import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;

import java.util.List;

public class OtherSafetyViewAdapter extends RecyclerView.Adapter<OtherSafetyViewAdapter.ViewHolder> {

  private Context context;

  public List<Safety> safetyList;

  private LayoutInflater inflater = null;

  public OtherSafetyViewAdapter(Context context, List<Safety> safetyList) {
    this.context = context;
    this.safetyList = safetyList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public OtherSafetyViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.othersafety_cardview, viewGroup, false);
    return new OtherSafetyViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final OtherSafetyViewAdapter.ViewHolder holder, final int i) {
    holder.setIsRecyclable(false);
    final Safety safety = safetyList.get(i);
    Glide
      .with(context)
      .load(safety.getSafetythumbnail())
      .into(holder.safety_thumbnail);
    holder.safety_title.setText(safety.getSafetytitle());
    holder.safety_subtitle.setText(safety.getSafetysubtitle());
    holder.safety_desc.setText(safety.getSafetydesc());

  }

  @Override
  public int getItemCount() {
    return safetyList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView safety_title;
    public TextView safety_subtitle;
    public DocumentView safety_desc;
    private ImageView safety_thumbnail;

    public ViewHolder(View view) {
      super(view);

      safety_title = (TextView) view.findViewById(R.id.safety_title);
      safety_subtitle = (TextView) view.findViewById(R.id.safety_subtitle);
      safety_thumbnail = (ImageView) view.findViewById(R.id.safety_thumbnail);
      safety_desc = (DocumentView) view.findViewById(R.id.safety_desc);
    }
  }
}
