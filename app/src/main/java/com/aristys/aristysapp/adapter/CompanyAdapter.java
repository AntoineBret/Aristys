package com.aristys.aristysapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aristys.aristysapp.Object.Company;

import com.aristys.aristysapp.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter <CompanyAdapter.ViewHolder> {

    private Context context;
    private List <Company> companyList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mtitle;
        public ImageView mthumbnail;

        public ViewHolder(View view) {
            super(view);
            mtitle = (TextView) view.findViewById(R.id.mtitle);
            mthumbnail = (ImageView) view.findViewById(R.id.mthumbnail);
        }
    }

    public CompanyAdapter(Context context, List <Company> companyList) {
        this.context = context;
        this.companyList = companyList;
    }

    @Override
    public CompanyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.company_card, parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Company company = companyList.get(position);
        holder.mtitle.setText(company.getName());

        Glide.with(context).load(company.getThumbnail()).into(holder.mthumbnail);
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }
}
