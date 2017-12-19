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
import com.aristys.aristysapp.adapter.OtherMobileViewAdapter;
import com.aristys.aristysapp.adapter.OtherPrintViewAdapter;
import com.aristys.aristysapp.model.Mobile;
import com.aristys.aristysapp.model.Print;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class MobileappFragment extends Fragment {

  private RecyclerView MobileRecyclerView;
  private OtherMobileViewAdapter MobileAdapter;
  public List<Mobile> mobileList;

  public static MobileappFragment newInstance() {
    return new MobileappFragment();
  }


  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_mobileapp, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mobileList = new ArrayList<>();

    MobileRecyclerView = (RecyclerView) getView().findViewById(R.id.mobile_recyclerView);
    MobileRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    MobileRecyclerView.setHasFixedSize(true);
    MobileRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

    MobileAdapter = new OtherMobileViewAdapter(getContext(), mobileList);
    MobileRecyclerView.setAdapter(MobileAdapter);

    initializeData();
  }

  private void initializeData() {
    int[] covers = new int[]{
      R.drawable.mobile_schema,
      R.drawable.mobile_android,
      R.drawable.mobile_materialdesign,
      R.drawable.mobile_ios,

    };

    Mobile m = new Mobile("Applications mobiles","Soyez présent sur le mobile de vos clients !", "Le marché des smartphones étant conséquent, il est devenu incontournable d’avoir son application Android et/ou iOS. Avec Aristys, Soyez présent sur le mobile de vos clients !" , covers[0]);
    mobileList.add(m);

    m = new Mobile("Applications Android","Le système d'exploitation mobile par Google","Installé sur plus de 80 % des smartphones en 2016, une application Android vous permettra d'améliorer votre image de marque et facilier votre communication en touchant un maximum de personnes.", covers[1]);
    mobileList.add(m);

    m = new Mobile("Material Design","Une expérience unifiée sur tout vos appareils","Le Material Design est un langage visuel qui synthétise les principes classiques de bon design avec l'innovation et la possibilité de la technologie et de la science. Avec Aristys profitez des dernières innovations en matière d'interface !", covers[2]);
    mobileList.add(m);

    m = new Mobile("Applications IOS","Le système d'exploitation mobile par Apple","Avec presque 18 % de part de marché, IOS reste un système d'exploitation incontournable. Il possède un plus grand taux d’achat avec des utilisateurs plus engagés qu'Android", covers[3]);
    mobileList.add(m);

    MobileAdapter.notifyDataSetChanged();
  }
}

