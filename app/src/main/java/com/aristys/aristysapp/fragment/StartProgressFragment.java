package com.aristys.aristysapp.fragment;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.adapter.StartProgressViewAdapter;
import com.aristys.aristysapp.model.OrderStatus;
import com.aristys.aristysapp.model.Progress;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class StartProgressFragment extends Fragment {

  private RecyclerView ProgressRecyclerView;
  private StartProgressViewAdapter ProgressAdapter;
  private List <Progress> progressList = new ArrayList <>();

  public static StartProgressFragment newInstance() {
    return new StartProgressFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_startprogress, container, false);

    progressList = new ArrayList<>();

    ProgressRecyclerView = (RecyclerView) view.findViewById(R.id.progress_recyclerView);
    ProgressRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    ProgressRecyclerView.setHasFixedSize(true);
    ProgressRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

    ProgressAdapter = new StartProgressViewAdapter(getContext(), progressList);
    ProgressRecyclerView.setAdapter(ProgressAdapter);

    setDataListItems();

    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    MaterialViewPagerHelper.registerRecyclerView(getActivity(), ProgressRecyclerView);
  }

  private void setDataListItems() {
    progressList.add(new Progress("Avec AristysSTART nous créons votre site professionnel de qualité au design moderne pour garantir votre visibilité web !", "START Déroulement",R.drawable.ic_start2, OrderStatus.INACTIVE));
    progressList.add(new Progress("L’offre AristySTART vous propose 5 thèmes aux designs modernes et élégants pour votre futur site internet. Choisissez votre design dès maintenant !", "START design",R.drawable.stageone, OrderStatus.INACTIVE));
    progressList.add(new Progress("Un conseiller web prendra contact avec vous dans les 24H (par mail ou par téléphone) pour convenir d’un rendez-vous et valider votre démarche projet web.", "START conseil",R.drawable.stagetwo, OrderStatus.ACTIVE));
    progressList.add(new Progress("Nous pouvons commencer la réalisation technique de votre site dans un délai express et ainsi vous livrer entre 5 et 10 jours. (sous réserve que le client fournisse tous ses contenus en temps voulu.) ", "START-ing site !",R.drawable.stagethree, OrderStatus.COMPLETED));
    progressList.add(new Progress("Votre site à partir de 800€ (mensualisation possible) et sans intérêt ni engagement !", "Budget et liberté !",R.drawable.ic_start3, OrderStatus.COMPLETED));
    progressList.add(new Progress("AristySART c’est l’assurance d’avoir un site internet complet respectant vos délais et votre budget qui sera maitrisé de bout en bout. N’hésitez pas à nous contacter pour de plus amples informations.", "Contact",R.drawable.ic_start1, OrderStatus.COMPLETED));
  }
}
