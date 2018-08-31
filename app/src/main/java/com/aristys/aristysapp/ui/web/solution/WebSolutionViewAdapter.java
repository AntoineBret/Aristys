package com.aristys.aristysapp.ui.web.solution;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.Solution;
import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;

import java.util.List;

public class WebSolutionViewAdapter extends RecyclerView.Adapter <WebSolutionViewAdapter.ViewHolder> {

  private Context context;

  public List<Solution> solutionList;

  private LayoutInflater inflater = null;

  public WebSolutionViewAdapter(Context context, List <Solution> solutionList) {
    this.context = context;
    this.solutionList = solutionList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public WebSolutionViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.websolution_cardview, viewGroup, false);
    return new WebSolutionViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final WebSolutionViewAdapter.ViewHolder holder, final int i) {
    holder.setIsRecyclable(false);
    final Solution solution = solutionList.get(i);
    Glide.with(context).load(solution.getSolutionthumbnail()).into(holder.solution_thumbnail);
    holder.solution_title.setText(solution.getSolutiontitle());
    holder.solution_subtitle.setText(solution.getSolutionsubtitle());
    holder.solution_desc.setText(solution.getSolutiondesc());

  }

  @Override
  public int getItemCount() {
    return solutionList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView solution_title;
    public TextView solution_subtitle;
    public DocumentView solution_desc;
    private ImageView solution_thumbnail;

    public ViewHolder(View view) {
      super(view);

      solution_title = (TextView) view.findViewById(R.id.solution_title);
      solution_subtitle = (TextView) view.findViewById(R.id.solution_subtitle);
      solution_thumbnail = (ImageView) view.findViewById(R.id.solution_thumbnail);
      solution_desc = (DocumentView) view.findViewById(R.id.solution_desc);
    }
  }
}
