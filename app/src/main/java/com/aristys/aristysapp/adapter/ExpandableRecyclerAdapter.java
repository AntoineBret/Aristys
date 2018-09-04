package com.aristys.aristysapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.DetailPost;
import com.aristys.aristysapp.model.Post;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.aristys.aristysapp.adapter.DateFormat.getDateFormatted;

public class ExpandableRecyclerAdapter extends RecyclerView.Adapter <ExpandableRecyclerAdapter.ViewHolder> {

  private Context context;

  public List <Post> postsList;

  private LayoutInflater inflater = null;

  public ExpandableRecyclerAdapter(Context context, List <Post> postsList) {
    this.context = context;
    this.postsList = postsList;
    inflater = LayoutInflater.from(context);

  }

  public void setData(List<Post> postsList) {
    this.postsList = postsList;
    notifyDataSetChanged();
  }

  @Override
  public ExpandableRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expandable_card_row, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ExpandableRecyclerAdapter.ViewHolder holder, final int i) {

    holder.setIsRecyclable(false);

    final Post post = postsList.get(i);

    holder.title.setText(Html.fromHtml(post.getTitle()));
    holder.date.setText(getDateFormatted(post.getDate()));

    Glide.with(context).load(post.getImgURL()).into(holder.thumbnail);
    holder.thumbnail.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context, DetailPost.class);
        intent.putExtra("content", post.getContent());
        context.startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return postsList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView title, date;
    private ImageView thumbnail;

    public ViewHolder(View view) {

      super(view);

      title = (TextView) view.findViewById(R.id.wptitle);
      date = (TextView) view.findViewById(R.id.wpdate);
      thumbnail = (ImageView) view.findViewById(R.id.wpthumbnail);
    }
  }
}
