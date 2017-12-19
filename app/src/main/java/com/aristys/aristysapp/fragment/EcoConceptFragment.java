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
import com.aristys.aristysapp.adapter.EcoConceptViewAdapter;
import com.aristys.aristysapp.model.Eco;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class EcoConceptFragment extends Fragment {

  private RecyclerView EcoRecyclerView;
  private EcoConceptViewAdapter EcoAdapter;
  public List<Eco> ecoList;

  public static EcoConceptFragment newInstance() {
    return new EcoConceptFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_eco_concept, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ecoList = new ArrayList<>();

    EcoRecyclerView = (RecyclerView) getView().findViewById(R.id.eco_concept_recyclerView);
    EcoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    EcoRecyclerView.setHasFixedSize(true);
    EcoRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

    EcoAdapter = new EcoConceptViewAdapter(getContext(), ecoList);
    EcoRecyclerView.setAdapter(EcoAdapter);

    initializeData();
  }

  private void initializeData() {
    int[] covers = new int[]{
      R.drawable.eco_one,
      R.drawable.eco_conception,
      R.drawable.eco_three,
      R.drawable.eco_four,
    };

    Eco e = new Eco("Éco-conception Web","Des solutions permettant de diminuer l'impact écologique de nos sites internet", "Aristys-Web met en œuvre des solutions UX et Web design en partenariat avec Maison Innovergne, le collectif Conception Numérique Responsable Green IT spécialisé dans les enjeux du numérique durable et responsable, et le Pôle Éco-conception de Saint-Etienne", covers[0]);
    ecoList.add(e);

    e = new Eco("Qu’est-ce que l’éco-conception ?","Chaque octet a un impact sur le monde réel","L'éco-conception se base sur trois concepts :\n" +
      "\n" +
      "- la simplicité, en optimisant la qualité des fonctionnalités proposées par le site.\n" +
      "- la frugalité (sobriété), en réduisant la quantité de fonctionnalités proposées à son strict nécessaire.\n" +
      "- la pertinence, en s'assurant que la page web affichée est utile, accessible et rapide.", covers[1]);
    ecoList.add(e);

    e = new Eco("Pourquoi opter pour un site éco-conçu?", "Le web, une empreinte environnementale colossale", "Chaque années, le web représente :\n" +
        "- 1 037 TWh d’énergie, soit 40 centrales nucléaires ou 140 millions de français pendant 1 an ;\n" +
        "- 608 millions de tonnes de gaz à effet de serre, soit l’équivalent de 86 millions de français(e)s ;\n" +
        "- 8,7 milliards de m3 d’eau, soit la consommation annuelle de 160 millions de français.", covers[2]);
    ecoList.add(e);

    e = new Eco("Que proposons-nous?", "Une méthode pour évaluer le niveau d’éco-conception de votre site", "Aristys à mis en place, selon les recommandations de Grenn IT, les bonnes pratiques couvrant tout le cycler de vie d'un projet internet:  conception métier / fonctionnelle, graphique et technique, architecture, développement, optimisation, hébergement, etc. afin d'identifier et mettre facilement en œuvre les bonnes pratiques qui lui incombe.", covers[3]);
    ecoList.add(e);

    EcoAdapter.notifyDataSetChanged();
  }
}


