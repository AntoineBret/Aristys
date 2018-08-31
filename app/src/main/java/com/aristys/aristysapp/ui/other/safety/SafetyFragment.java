package com.aristys.aristysapp.ui.other.safety;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.Safety;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class SafetyFragment extends Fragment {

  private RecyclerView SafetyRecyclerView;
  private OtherSafetyViewAdapter SafetyAdapter;
  public List<Safety> safetyList;

  public static SafetyFragment newInstance() {
    return new SafetyFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_safety, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    safetyList = new ArrayList<>();

    SafetyRecyclerView = (RecyclerView) getView().findViewById(R.id.safety_recyclerView);
    SafetyRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    SafetyRecyclerView.setHasFixedSize(true);
    SafetyRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

    SafetyAdapter = new OtherSafetyViewAdapter(getContext(), safetyList);
    SafetyRecyclerView.setAdapter(SafetyAdapter);

    initializeData();
  }

  private void initializeData() {
    int[] covers = new int[]{
      R.drawable.safety_conseil,
      R.drawable.safety_training,
      R.drawable.safety_rooteed,
      R.drawable.safety_orisk,

    };

    Safety s = new Safety("Services de conseil","Que faire, quand, pourquoi, comment", "Services de Conseil pour vous aider à établir ou améliorer votre posture globale de cybersécurité.", covers[0]);
    safetyList.add(s);

    s = new Safety("Formation","Sensibilisez et formez vos équipes","Sensibilisez et formez vos équipes à la cybersécurité pour connaître la menace et mieux vous défendre.", covers[1]);
    safetyList.add(s);

    s = new Safety("Audit et test","Testez votre sécurité, simulez les menaces","Évaluatez et testez la sécurité de vos infrastructures et vos actifs numériques.", covers[2]);
    safetyList.add(s);

    s = new Safety("Sécurité infogérée","Votre équipe de sécurité sur mesure","Monitoring, Détection & Réponse à Incident pour vous aider à identifier et déjouer les menaces et gérer votre risque cyber.", covers[3]);
    safetyList.add(s);

    SafetyAdapter.notifyDataSetChanged();
  }
}

