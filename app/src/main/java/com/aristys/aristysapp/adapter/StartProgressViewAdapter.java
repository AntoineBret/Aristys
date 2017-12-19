package com.aristys.aristysapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.Progress;
import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;
import com.github.vipulasri.timelineview.TimelineView;

import org.w3c.dom.Text;

import java.util.List;

public class StartProgressViewAdapter extends RecyclerView.Adapter <StartProgressViewAdapter.ViewHolder> {

  private Context context;

  public List <Progress> progressList;

  private LayoutInflater inflater = null;

  public StartProgressViewAdapter(Context context, List<Progress> progressList) {
    this.context = context;
    this.progressList = progressList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public StartProgressViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.startprogress_cardview, viewGroup, false);
    return new StartProgressViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final StartProgressViewAdapter.ViewHolder holder, final int i) {
    holder.setIsRecyclable(false);
    final Progress progress = progressList.get(i);
    holder.mTitle.setText(progress.getTitle());
    holder.mMessage.setText(progress.getMessage());
    Glide.with(context).load(progress.getImage()).into(holder.mImage);
    }

  @Override
  public int getItemCount() {
    return progressList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView mTitle;
    private DocumentView mMessage;
    private ImageView mImage;
    private TimelineView mTimelineView;

    public ViewHolder(View view) {
      super(view);

      mTitle = (TextView) view.findViewById(R.id.text_timeline_title);
      mMessage = (DocumentView) view.findViewById(R.id.text_timeline_message);
      mImage = (ImageView) view.findViewById(R.id.image_timeline);
      mTimelineView = (TimelineView) view.findViewById(R.id.time_marker);
    }
  }
}

