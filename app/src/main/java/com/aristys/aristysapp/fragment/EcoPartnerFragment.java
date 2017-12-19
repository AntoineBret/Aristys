package com.aristys.aristysapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.adapter.EcoPartnerViewAdapter;
import com.aristys.aristysapp.model.EcoPartner;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class EcoPartnerFragment extends Fragment{

  private RecyclerView EcoPartnerRecyclerView;
  private EcoPartnerViewAdapter EcoPartnerAdapter;
  public List<EcoPartner> ecoPartnerList;

  public static EcoPartnerFragment newInstance() {
    return new EcoPartnerFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_ecopartner, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ecoPartnerList = new ArrayList<>();

    EcoPartnerRecyclerView = (RecyclerView) getView().findViewById(R.id.ecopartner_recyclerView);
    EcoPartnerRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    EcoPartnerRecyclerView.setHasFixedSize(true);
    EcoPartnerRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

    EcoPartnerAdapter = new EcoPartnerViewAdapter(getContext(), ecoPartnerList);
    EcoPartnerRecyclerView.setAdapter(EcoPartnerAdapter);

    initializeData();
  }

  private void initializeData() {
    int[] covers = new int[]{
      R.drawable.mobile_schema,
      R.drawable.mobile_android,
      R.drawable.mobile_materialdesign,
      R.drawable.mobile_ios,

    };

    EcoPartner e = new EcoPartner("","", "" , covers[0]);
    ecoPartnerList.add(e);

    e = new EcoPartner("","","", covers[1]);
    ecoPartnerList.add(e);

    e = new EcoPartner("","","", covers[2]);
    ecoPartnerList.add(e);

    e = new EcoPartner("","","", covers[3]);
    ecoPartnerList.add(e);

    EcoPartnerAdapter.notifyDataSetChanged();
  }
}


