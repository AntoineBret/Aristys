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
import com.aristys.aristysapp.adapter.EcoAristysViewAdapter;
import com.aristys.aristysapp.model.EcoAristys;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class EcoAristysFragment extends Fragment {

  private RecyclerView EcoAristysRecyclerView;
  private EcoAristysViewAdapter EcoAristysAdapter;
  private List<EcoAristys> ecoAristysList;

  public static EcoAristysFragment newInstance() {
    return new EcoAristysFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_ecoaristys, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ecoAristysList = new ArrayList<>();

    EcoAristysRecyclerView = (RecyclerView) getView().findViewById(R.id.ecoaristys_recyclerView);
    EcoAristysRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    EcoAristysRecyclerView.setHasFixedSize(true);
    EcoAristysRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

    EcoAristysAdapter = new EcoAristysViewAdapter(getContext(), ecoAristysList);
    EcoAristysRecyclerView.setAdapter(EcoAristysAdapter);

    initializeData();
  }

  private void initializeData() {
    int[] covers = new int[]{
      R.drawable.mobile_schema,
      R.drawable.mobile_android,
      R.drawable.mobile_materialdesign,
      R.drawable.mobile_ios,

    };

    EcoAristys e = new EcoAristys("","", "" , covers[0]);
    ecoAristysList.add(e);

    e = new EcoAristys("","","", covers[1]);
    ecoAristysList.add(e);

    e = new EcoAristys("","","", covers[2]);
    ecoAristysList.add(e);

    e = new EcoAristys("","","", covers[3]);
    ecoAristysList.add(e);

    EcoAristysAdapter.notifyDataSetChanged();
  }
}


