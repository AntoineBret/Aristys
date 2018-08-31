package com.aristys.aristysapp.ui.skill;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.model.Skill;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class SkillFragment extends Fragment {

  private RecyclerView SkillRecyclerView;
  private SkillOurskillViewAdapter SkillAdapter;
  public List<Skill> skillList;

  public static SkillFragment newInstance() {
    return new SkillFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_skill, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    skillList = new ArrayList<>();

    SkillRecyclerView = (RecyclerView) getView().findViewById(R.id.skill_recyclerView);
    SkillRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    SkillRecyclerView.setHasFixedSize(true);
    SkillRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

    SkillAdapter = new SkillOurskillViewAdapter(getContext(), skillList);
    SkillRecyclerView.setAdapter(SkillAdapter);

    initializeData();
  }

  private void initializeData() {
    int[] covers = new int[]{
      R.drawable.css3,
      R.drawable.html5,
      R.drawable.js,
      R.drawable.muse,
      R.drawable.wordpress,
      R.drawable.prestashop,
      R.drawable.symfony,
      R.drawable.java,
    };

    Skill s = new Skill("CSS3", covers[0]);
    skillList.add(s);

    s = new Skill("HTML5", covers[1]);
    skillList.add(s);

    s = new Skill("JavaScript", covers[2]);
    skillList.add(s);

    s = new Skill("Adobe Muse", covers[3]);
    skillList.add(s);

    s = new Skill("WordPress", covers[4]);
    skillList.add(s);

    s = new Skill("PrestaShop", covers[5]);
    skillList.add(s);

    s = new Skill("Synfony", covers[6]);
    skillList.add(s);

    s = new Skill("Java", covers[7]);
    skillList.add(s);


    SkillAdapter.notifyDataSetChanged();
  }
}

