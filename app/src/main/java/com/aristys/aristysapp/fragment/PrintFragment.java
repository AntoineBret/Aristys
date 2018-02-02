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
import com.aristys.aristysapp.model.Print;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;


public class PrintFragment extends Fragment {

  private RecyclerView PrintRecyclerView;
  private OtherPrintViewAdapter PrintAdapter;
  public List<Print> printList;

  public static PrintFragment newInstance() {
    return new PrintFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_print, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    printList = new ArrayList<>();

    PrintRecyclerView = (RecyclerView) getView().findViewById(R.id.print_recyclerView);
    PrintRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    PrintRecyclerView.setHasFixedSize(true);
    PrintRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

    PrintAdapter = new OtherPrintViewAdapter(getContext(), printList);
    PrintRecyclerView.setAdapter(PrintAdapter);

    initializeData();
  }

  private void initializeData() {
    int[] covers = new int[]{
      R.drawable.print_logo,
      R.drawable.print_card,
      R.drawable.print_flyer,
      R.drawable.print_xl,
    };

    Print p = new Print("Logo","Création de logo sur mesure", "Créer un logo devient un jeu d'enfant avec Aristys ! Obtenez la reconnaissance que votre entreprise mérite en créant un logo remarquable que vos clients pourront facilement identifier.", covers[0]);
    printList.add(p);

    p = new Print("Carte de visite","Réalisez une carte aussi unique que votre entreprise.","Vous souhaitez créer ou renouveler vos cartes de visite ? Envoyez nous votre graphisme ou vos idées, choisissez le type de papier, le style et la quantité, nous nous chargeons du reste !", covers[1]);
    printList.add(p);

    p = new Print("Flyer et affiches","Création de flyer et affiches","La date de votre événement est fixée, mais comment le faire connaître, comment ramener un maximum de participants? La réponse est simple, inondez votre ville d’affiches et de flyer! Contactez nous !", covers[2]);
    printList.add(p);

    p = new Print("Support grande taille","Création pour support de grande taille"," Attirez plus de clients grâce à un panneau accrocheur ! Quelle que soit l'expérience graphique souhaité, nous nous chargeons de créer une identité visuelle unique pour votre affichage.", covers[3]);
    printList.add(p);

    PrintAdapter.notifyDataSetChanged();
  }
}

