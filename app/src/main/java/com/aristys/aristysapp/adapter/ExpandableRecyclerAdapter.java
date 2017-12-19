package com.aristys.aristysapp.adapter;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.aristys.aristysapp.model.DetailPost;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.interfaces.NetworkController;
import com.aristys.aristysapp.model.Post;

import java.util.List;

public class ExpandableRecyclerAdapter extends RecyclerView.Adapter <ExpandableRecyclerAdapter.ViewHolder> {

  private Context context;

  public List <Post> postsList;

  private LayoutInflater inflater = null;

  private LruCache <Integer, Bitmap> imageCache;
  private RequestQueue queue;

  public ExpandableRecyclerAdapter(Context context, List <Post> postsList) {
    this.context = context;
    this.postsList = postsList;
    inflater = LayoutInflater.from(context);

    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    final int cacheSize = maxMemory / 8;
    imageCache = new LruCache <>(cacheSize);

    queue = Volley.newRequestQueue(context);
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
    String url = post.imgURL;

    holder.title.setText(post.getTitle());
    holder.date.setText(post.getDate());

    holder.thumbnail.setImageUrl(post.getImgURL(), NetworkController.getInstance(context).getImageLoader());
    holder.thumbnail.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context, DetailPost.class);
        intent.putExtra("content", post.content);
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
    private NetworkImageView thumbnail;

    public ViewHolder(View view) {

      super(view);

      title = (TextView) view.findViewById(R.id.wptitle);
      date = (TextView) view.findViewById(R.id.wpdate);
      thumbnail = (NetworkImageView) view.findViewById(R.id.wpthumbnail);
    }
  }
}
