package com.aristys.aristysapp.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aristys.aristysapp.EcoActivity;
import com.aristys.aristysapp.OtherActivity;
import com.aristys.aristysapp.R;
import com.aristys.aristysapp.SeoActivity;
import com.aristys.aristysapp.SkillActivity;
import com.aristys.aristysapp.StartActivity;
import com.aristys.aristysapp.UXActivity;
import com.aristys.aristysapp.WebActivity;
import com.aristys.aristysapp.adapter.RecyclerItemClickListener;
import com.aristys.aristysapp.adapter.ServiceAdapter;
import com.aristys.aristysapp.model.Service;
import com.bumptech.glide.Glide;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class OurServiceFragment extends Fragment  implements AppBarLayout.OnOffsetChangedListener {

  private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
  private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
  private static final int ALPHA_ANIMATIONS_DURATION              = 200;

  private boolean mIsTheTitleVisible          = false;
  private boolean mIsTheTitleContainerVisible = true;

  private LinearLayout mTitleContainer;
  private TextView mTitle;
  private AppBarLayout mAppBarLayout;
  private Toolbar toolbar;
  private RecyclerView mRecyclerView;
  private ServiceAdapter mAdapter;
  private List<Service> serviceList;
  private RecyclerView.LayoutManager mLayoutManager;
  private FragmentManager manager;
  private Intent intent;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_ourservice, container, false);

    toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
    /* Change back_arrow icon to hamburger icon */
    toolbar.setNavigationIcon(R.drawable.ic_menu_vector);
    /* Change classic toolbar to action bar and apply the icon change */
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    /* Back arrow or hamburger icon enable = true, disable = false */
    ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);



    mTitle = (TextView) rootView.findViewById(R.id.main_textview_title);
    mTitleContainer = (LinearLayout) rootView.findViewById(R.id.main_linearlayout_title);
    mAppBarLayout = (AppBarLayout) rootView.findViewById(R.id.main_appbar);

    mRecyclerView = (RecyclerView) rootView.findViewById(R.id.service_recyclerview);

    mAppBarLayout.addOnOffsetChangedListener(this);

    startAlphaAnimation(mTitle, 0, View.INVISIBLE);

    serviceList = new ArrayList<>();
    mAdapter = new ServiceAdapter(getContext(), serviceList);

    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
    mRecyclerView.setAdapter(mAdapter);

    mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
      @Override
      public void onItemClick(View view, int position) {
        switch (position) {
          case 0:
            intent = new Intent(getActivity(), WebActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            break;
          case 1:
            intent = new Intent(getActivity(), StartActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            break;
          case 2:
            intent = new Intent(getActivity(), SeoActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            break;
          case 3:
            intent = new Intent(getActivity(), UXActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            break;
          case 4:
            intent = new Intent(getActivity(), EcoActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            break;
          case 5:
            intent = new Intent(getActivity(), OtherActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            break;
          case 6:
            intent = new Intent(getActivity(), SkillActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            break;
        }
      }
    }));

    prepareService();

    return rootView;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_main, menu);
    super.onCreateOptionsMenu(menu,inflater);
  }

  @Override
  public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
    int maxScroll = appBarLayout.getTotalScrollRange();
    float percentage = (float) Math.abs(offset) / (float) maxScroll;

    handleAlphaOnTitle(percentage);
    handleToolbarTitleVisibility(percentage);
  }

  private void handleToolbarTitleVisibility(float percentage) {
    if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

      if(!mIsTheTitleVisible) {
        startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
        mIsTheTitleVisible = true;
      }

    } else {

      if (mIsTheTitleVisible) {
        startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
        mIsTheTitleVisible = false;
      }
    }
  }

  private void handleAlphaOnTitle(float percentage) {
    if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
      if(mIsTheTitleContainerVisible) {
        startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
        mIsTheTitleContainerVisible = false;
      }

    } else {

      if (!mIsTheTitleContainerVisible) {
        startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
        mIsTheTitleContainerVisible = true;
      }
    }
  }

  public static void startAlphaAnimation (View v, long duration, int visibility) {
    AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
      ? new AlphaAnimation(0f, 1f)
      : new AlphaAnimation(1f, 0f);

    alphaAnimation.setDuration(duration);
    alphaAnimation.setFillAfter(true);
    v.startAnimation(alphaAnimation);
  }

  private void prepareService() {
    int[] covers = new int[]{
      R.drawable.ic_presta_web,
      R.drawable.ic_presta_start,
      R.drawable.ic_presta_seo,
      R.drawable.ic_presta_ux,
      R.drawable.ic_presta_eco,
      R.drawable.ic_presta_other,
      R.drawable.ic_presta_skill,};

    Service s = new Service("WEB", "Sites web et boutiques en ligne", "", covers[0]);
    serviceList.add(s);

    s = new Service("START", "Solutions  pour les porteurs de projets", "", covers[1]);
    serviceList.add(s);

    s = new Service("SEO", "Solutions en référencement", "", covers[2]);
    serviceList.add(s);

    s = new Service("UX", "Solutions en design d'interface", "", covers[3]);
    serviceList.add(s);

    s = new Service("ECO", "Solutions en éco-conception web", "", covers[4]);
    serviceList.add(s);

    s = new Service("Autres prestations", "Tout ce qu'il vous faut pour réussir !", "", covers[5]);
    serviceList.add(s);

    s = new Service("Nos langages de programmation", "Pour chaque besoin, une compétence", "", covers[6]);
    serviceList.add(s);

    mAdapter.notifyDataSetChanged();
  }
}
