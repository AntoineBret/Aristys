package com.aristys.aristysapp.ui.partnair;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.Partnair;
import com.bumptech.glide.Glide;

import java.util.List;

public class PartnairAdapter extends RecyclerView.Adapter <PartnairAdapter.ViewHolder> {

    private Context context;
    private List<Partnair> partnairList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mtitle;
        public ImageView mthumbnail;

        public ViewHolder(View view) {
            super(view);
            mtitle = (TextView) view.findViewById(R.id.mtitle);
            mthumbnail = (ImageView) view.findViewById(R.id.mthumbnail);
        }
    }

    public PartnairAdapter(Context context, List <Partnair> partnairList) {
        this.context = context;
        this.partnairList = partnairList;
    }

    @Override
    public PartnairAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.partnair_card, parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Partnair partnair = partnairList.get(position);
        holder.mtitle.setText(partnair.getName());

        Glide.with(context).load(partnair.getThumbnail()).into(holder.mthumbnail);
    }

    @Override
    public int getItemCount() {
        return partnairList.size();
    }
}
