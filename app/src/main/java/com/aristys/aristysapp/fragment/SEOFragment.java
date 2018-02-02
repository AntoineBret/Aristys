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
import com.aristys.aristysapp.adapter.SEOViewAdapter;
import com.aristys.aristysapp.model.SEO;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class SEOFragment extends Fragment {

  private RecyclerView SeoRecyclerView;
  private SEOViewAdapter SeoAdapter;
  public List<SEO> seoList;

  public static SEOFragment newInstance() {
    return new SEOFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_seo, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    seoList = new ArrayList<>();

    SeoRecyclerView = (RecyclerView) getView().findViewById(R.id.seo_recyclerView);
    SeoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    SeoRecyclerView.setHasFixedSize(true);
    SeoRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

    SeoAdapter = new SEOViewAdapter(getContext(), seoList);
    SeoRecyclerView.setAdapter(SeoAdapter);

    initializeData();
  }

  private void initializeData() {
    int[] covers = new int[]{
      R.drawable.seo_analysis,
      R.drawable.seo_requirement,
      R.drawable.seo_keywords,
      R.drawable.seo_contents,
      R.drawable.seo_optimization,
      R.drawable.seo_link,
      R.drawable.seo_reporting,
      R.drawable.seo_social,

    };

    SEO s = new SEO("Analyse du site","Obtenez la première page !", "Fort de notre savoir-faire et de notre expertise en référencement, nous sommes certains d'apporter une nouvelle visibilité à votre site internet.", covers[0]);
    seoList.add(s);

    s = new SEO("Exigence client","Parce que tout commence avec vos clients","Nous optimisons votre site internet selon des critères de performances identifiés et éprouvés afin de le rendre techniquement irréprochable. Au regard de l'environnement de votre site et de vos attentes, nous établissons la solution la plus efficace pour y répondre.", covers[1]);
    seoList.add(s);

    s = new SEO("Recherche des mots-clés","Aidez vos futurs clients à vous trouver avec les mots-clés de votre choix !","A travers sa quête de qualité et son expertise, Aristys a su apporter à l'intégralité de ses clients la visibilité qu'ils recherchaient. Le référencement naturel en 1ère page avec résultats garantis est notre spécialité. Quelle que soit la thématique de votre site et de sa concurrence, nous le référencerons en 1ère page de Google. ", covers[2]);
    seoList.add(s);

    s = new SEO("Ecriture du contenu","Répondez aux attentes de vos publics","On n’écrit pas pour le web comme on écrit pour un magazine papier. Pour être efficace et espérer avoir des lecteurs, un rédacteur web doit s’appuyer sur un certain nombre de règles de rédaction spécifiques au média web. But de la manœuvre : informer, convaincre et améliorer son référencement naturel. Le point sur les règles d’or de la rédaction web.", covers[3]);
    seoList.add(s);

    s = new SEO("Audit et optimisation du site","Une solution complète pour le référencement naturel","L'audit SEO de votre site est l'étape la plus importante avant d'entreprendre toute stratégie de positionnement en référencement naturel. L’audit permet d’identifier les éléments bloquants et d’apporter les solutions correctives pour poser les bonnes bases d’un référencement naturel sain et gagnant.", covers[4]);
    seoList.add(s);

    s = new SEO("Link building","Développez le nombre de liens pointant sur votre site","Dès que le secteur dans le quel on se trouve est un temps soit peu compétitif, le référencement se doit impérativement d’être complété par un sérieux travail de « link-building », cette élément est  celui auquel les moteurs donnent le plus d’importance est le réseau de back-links dont dispose un site A. Un back-link est un lien texte se trouvant sur un site et se dirigeant vers le site A.", covers[5]);
    seoList.add(s);

    s = new SEO("Reporting","Détecter les opportunités d’amélioration !","La phase de reporting est primordiale dans un projet web analytics. Elle sert à contrôler les tendances de trafic, d’événements, d’objectifs, de transactions, de tout ces indicateurs qui ont été considérés et réfléchis comme étant clés.", covers[6]);
    seoList.add(s);

    s = new SEO("Réseaux sociaux","Un atout pour votre entreprise","Facebook, LinkedIn, Viadeo... Les réseaux sociaux attirent de plus en plus de personnes sur Internet. Les entreprises doivent apprendre à s'en servir pour s'adresser à de nouvelles cibles. Ce nouveau canal de communication permet de renforcer la présence de votre société sur Internet, d'accroître votre nombre de clients et de recruter différemment vos futurs collaborateurs.", covers[7]);
    seoList.add(s);

    SeoAdapter.notifyDataSetChanged();
  }
}

