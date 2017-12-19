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
import com.aristys.aristysapp.adapter.OtherSafetyViewAdapter;
import com.aristys.aristysapp.model.Print;
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
      R.drawable.safety_one,
      R.drawable.safety_one,
      R.drawable.safety_one,
      R.drawable.safety_one,

    };

    Safety s = new Safety("Presta 1","Presta 1 Subtitle", "", covers[0]);
    safetyList.add(s);

    s = new Safety("Presta 2","Presta 2 Subtitle","", covers[1]);
    safetyList.add(s);

    s = new Safety("Presta 3","Presta 3 Subtitle","", covers[1]);
    safetyList.add(s);

    s = new Safety("Presta 4","Presta 4 Subtitle","", covers[1]);
    safetyList.add(s);

    SafetyAdapter.notifyDataSetChanged();
  }
}

