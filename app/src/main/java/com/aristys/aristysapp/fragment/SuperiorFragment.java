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
import com.aristys.aristysapp.adapter.WebSuperiorViewAdapter;
import com.aristys.aristysapp.model.Superior;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class SuperiorFragment extends Fragment {

  private RecyclerView SuperiorRecyclerView;
  private WebSuperiorViewAdapter SuperiorAdapter;
  public List<Superior> superiorList;

  public static SuperiorFragment newInstance() {
    return new SuperiorFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_superior, container, false);
  }
  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    superiorList = new ArrayList<>();

    SuperiorRecyclerView = (RecyclerView) getView().findViewById(R.id.superior_recyclerView);
    SuperiorRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    SuperiorRecyclerView.setHasFixedSize(true);
    SuperiorRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

    SuperiorAdapter = new WebSuperiorViewAdapter(getContext(), superiorList);
    SuperiorRecyclerView.setAdapter(SuperiorAdapter);

    initializeData();
  }

  private void initializeData() {
    int[] covers = new int[]{
      R.drawable.superior_one,
      R.drawable.superior_two,
      R.drawable.superior_three,
      R.drawable.superior_four,

    };

    Superior s = new Superior("LA  solution  sur mesure !","Du sur mesure numérique spécialement pour vous, marquez votre différence", "Dans cette formule, retrouvez tout le savoir-faire d’Aristys-Web mis à votre disposition pour valoriser votre activité grâce à des logiciels comme MUSE et Wordpress DIVI. L’objectif est d’avoir un site internet original et efficace pour marquer le Web d'une empreinte unique : la VÔTRE !", covers[0]);
    superiorList.add(s);

    s = new Superior("Avantages AristysSUPERIEUR","","", covers[1]);
    superiorList.add(s);

    s = new Superior("","","", covers[2]);
    superiorList.add(s);

    s = new Superior("","","", covers[3]);
    superiorList.add(s);

    SuperiorAdapter.notifyDataSetChanged();
  }
}


