package com.aristys.aristysapp.ui.seo.describe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.SEO;
import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;

import java.util.List;

public class SEOViewAdapter extends RecyclerView.Adapter <SEOViewAdapter.ViewHolder> {

  private Context context;

  public List<SEO> seoList;

  private LayoutInflater inflater = null;

  public SEOViewAdapter(Context context, List <SEO> seoList) {
    this.context = context;
    this.seoList = seoList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public SEOViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.seo_cardview, viewGroup, false);
    return new SEOViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final SEOViewAdapter.ViewHolder holder, final int i) {
    holder.setIsRecyclable(false);
    final SEO seo = seoList.get(i);
    Glide.with(context).load(seo.getSeothumbnail()).into(holder.seo_thumbnail);
    holder.seo_title.setText(seo.getSeotitle());
    holder.seo_subtitle.setText(seo.getSeosubtitle());
    holder.seo_desc.setText(seo.getSeodesc());

  }

  @Override
  public int getItemCount() {
    return seoList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView seo_title;
    public TextView seo_subtitle;
    public DocumentView seo_desc;
    private ImageView seo_thumbnail;

    public ViewHolder(View view) {
      super(view);

      seo_title = (TextView) view.findViewById(R.id.seo_title);
      seo_subtitle = (TextView) view.findViewById(R.id.seo_subtitle);
      seo_thumbnail = (ImageView) view.findViewById(R.id.seo_thumbnail);
      seo_desc = (DocumentView) view.findViewById(R.id.seo_desc);
    }
  }
}
