package com.aristys.aristysapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aristys.aristysapp.fragment.EcoAristysFragment;
import com.aristys.aristysapp.fragment.EcoConceptFragment;
import com.aristys.aristysapp.fragment.EcoContactFragment;
import com.aristys.aristysapp.fragment.EcoPartnerFragment;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

public class EcoActivity extends AppCompatActivity {

  MaterialViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_eco);
    setTitle("");

    mViewPager = (MaterialViewPager) findViewById(R.id.eco_materialViewPager);

    final Toolbar toolbar = mViewPager.getToolbar();
    if (toolbar != null) {
      setSupportActionBar(toolbar);
    }

    mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

      @Override
      public Fragment getItem(int position) {
        switch (position % 4) {
          case 0:
            return EcoConceptFragment.newInstance();
          case 1:
            return EcoAristysFragment.newInstance();
          case 2:
            return EcoPartnerFragment.newInstance();
          case 3:
            return EcoContactFragment.newInstance();
          default:
            return EcoConceptFragment.newInstance();
        }
      }

      @Override
      public int getCount() {
        return 4;
      }

      @Override
      public CharSequence getPageTitle(int position) {
        switch (position % 4) {
          case 0:
            return "Concept";
          case 1:
            return "Aristys-eco";
          case 2:
            return "Partenaires";
          case 3:
            return "Contact";
        }
        return "";
      }
    });

    mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
      @Override
      public HeaderDesign getHeaderDesign(int page) {
        switch (page) {
          case 0:
            return HeaderDesign.fromColorResAndDrawable(
              R.color.eco_concept,
              getResources().getDrawable(R.drawable.ecoconcept_header));
          case 1:
            return HeaderDesign.fromColorResAndDrawable(
              R.color.other_photo,
              getResources().getDrawable(R.drawable.eco_aristys_header));
          case 2:
            return HeaderDesign.fromColorResAndDrawable(
              R.color.other_safety,
              getResources().getDrawable(R.drawable.eco_partner_header));
          case 3:
            return HeaderDesign.fromColorResAndDrawable(
              R.color.other_mobile,
              getResources().getDrawable(R.drawable.mobile_header));
          case 4:
            return HeaderDesign.fromColorResAndDrawable(
              R.color.other_mobile,
              getResources().getDrawable(R.drawable.mobile_header));
        }
        return null;
      }
    });

    mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
    mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

    final View logo = findViewById(R.id.logo_white);
    if (logo != null) {
      logo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          mViewPager.notifyHeaderChanged();
          Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
        }
      });
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    overridePendingTransition(0, android.R.anim.slide_out_right);
  }
}
