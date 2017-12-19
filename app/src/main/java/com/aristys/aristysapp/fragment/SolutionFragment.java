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
import com.aristys.aristysapp.adapter.OtherPrintViewAdapter;
import com.aristys.aristysapp.adapter.WebSolutionViewAdapter;
import com.aristys.aristysapp.model.Print;
import com.aristys.aristysapp.model.Solution;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class SolutionFragment extends Fragment {

  private RecyclerView SolutionRecyclerView;
  private WebSolutionViewAdapter SolutionAdapter;
  public List <Solution> solutionList;

  public static SolutionFragment newInstance() {
    return new SolutionFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_solution, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    solutionList = new ArrayList <>();

    SolutionRecyclerView = (RecyclerView) getView().findViewById(R.id.solution_recyclerView);
    SolutionRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    SolutionRecyclerView.setHasFixedSize(true);
    SolutionRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

    SolutionAdapter = new WebSolutionViewAdapter(getContext(), solutionList);
    SolutionRecyclerView.setAdapter(SolutionAdapter);

    initializeData();
  }

  private void initializeData() {
    int[] covers = new int[]{
      R.drawable.solution_one,
      R.drawable.solution_two,
      R.drawable.solution_three,
      R.drawable.solution_project,

    };

    Solution s = new Solution("Votre site web personnalisé !", "Valoriser votre société sur le web", "Il s'agit d'une formule accessible et rapide pour assoir votre présence sur le web avec la possibilité de mettre en place votre Chatbot ! L’objectif est d’avoir un site web plus attractif pour votre société et d’inviter les internautes à mieux vous connaitre donc à mieux vous apprécier !", covers[0]);
    solutionList.add(s);

    s = new Solution("Avantages AristysSOLUTION", "Une formule en cohérence avec votre entreprise", "La réalisation de votre site internet sur-mesure vous permet de créer un site internet unique en terme de fonctionnalités. De cette manière, vous pouvez présenter très précisément vos produits ou services ou encore automatiser certaines actions.", covers[1]);
    solutionList.add(s);

    s = new Solution("Un accompagnement personnalisé ", "Nous immerger dans votre business pour le succès de votre site internet", "Votre projet sera suivi par un chef de projet unique. Son objectif est de vous conseiller en vous accompagnant dans chaque étape de votre site internet sur-mesure.", covers[2]);
    solutionList.add(s);

    s = new Solution("Déroulement de votre projet", "Une agence qui accompagne et conseille sur votre projet", "Analyse de votre activité et définition de la stratégie digitale, conception et développement de l'ergonomie du site internet, formation à l'utilisation de l'administration : avec Aristys soyez rassuré, on s'occupe de tout", covers[3]);
    solutionList.add(s);

    SolutionAdapter.notifyDataSetChanged();
  }
}

