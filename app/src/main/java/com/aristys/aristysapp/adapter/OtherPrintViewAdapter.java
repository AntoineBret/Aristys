package com.aristys.aristysapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.Print;
import com.bluejamesbond.text.DocumentView;
import com.bumptech.glide.Glide;

import java.util.List;

public class OtherPrintViewAdapter extends RecyclerView.Adapter <OtherPrintViewAdapter.ViewHolder> {

private Context context;

public List<Print> printList;

private LayoutInflater inflater = null;

public OtherPrintViewAdapter(Context context, List <Print> printList) {
  this.context = context;
  this.printList = printList;
  inflater = LayoutInflater.from(context);
  }

@Override
public OtherPrintViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
  this.context = viewGroup.getContext();
  View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.otherprint_cardview, viewGroup, false);
  return new OtherPrintViewAdapter.ViewHolder(view);
  }

@Override
public void onBindViewHolder(final OtherPrintViewAdapter.ViewHolder holder, final int i) {
  holder.setIsRecyclable(false);
  final Print print = printList.get(i);
  Glide.with(context).load(print.getPrintthumbnail()).into(holder.print_thumbnail);
  holder.print_title.setText(print.getPrinttitle());
  holder.print_subtitle.setText(print.getPrintsubtitle());
  holder.print_desc.setText(print.getPrintdesc());

}

@Override
public int getItemCount() {
  return printList.size();
  }

public class ViewHolder extends RecyclerView.ViewHolder {

  public TextView print_title;
  public TextView print_subtitle;
  public DocumentView print_desc;
  private ImageView print_thumbnail;

  public ViewHolder(View view) {
    super(view);

    print_title = (TextView) view.findViewById(R.id.print_title);
    print_subtitle = (TextView) view.findViewById(R.id.print_subtitle);
    print_thumbnail = (ImageView) view.findViewById(R.id.print_thumbnail);
    print_desc = (DocumentView) view.findViewById(R.id.print_desc);
  }
}
}
