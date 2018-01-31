package com.aristys.aristysapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aristys.aristysapp.fragment.MobileappFragment;
import com.aristys.aristysapp.fragment.OtherContactFragment;
import com.aristys.aristysapp.fragment.PhotoFragment;
import com.aristys.aristysapp.fragment.PrintFragment;
import com.aristys.aristysapp.fragment.SafetyFragment;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

public class OtherActivity extends AppCompatActivity {

  MaterialViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_other);
    setTitle("");

    mViewPager = (MaterialViewPager) findViewById(R.id.other_materialViewPager);
    final Toolbar toolbar = mViewPager.getToolbar();
    if (toolbar != null) {
      setSupportActionBar(toolbar);
    }

    mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

      @Override
      public Fragment getItem(int position) {
        switch (position % 5) {
          case 0:
            return PrintFragment.newInstance();
          case 1:
            return PhotoFragment.newInstance();
          case 2:
            return SafetyFragment.newInstance();
          case 3:
            return MobileappFragment.newInstance();
          case 4:
            return OtherContactFragment.newInstance();
          default:
            return PrintFragment.newInstance();
        }
      }

      @Override
      public int getCount() {
        return 5;
      }

      @Override
      public CharSequence getPageTitle(int position) {
        switch (position % 5) {
          case 0:
            return "Print";
          case 1:
            return "Photo";
          case 2:
            return "Sécurité";
          case 3:
            return "Mobile";
          case 4:
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
              R.color.other_print,
              getResources().getDrawable(R.drawable.print_header));
          case 1:
            return HeaderDesign.fromColorResAndDrawable(
              R.color.other_photo,
              getResources().getDrawable(R.drawable.photo_header));
          case 2:
            return HeaderDesign.fromColorResAndDrawable(
              R.color.other_safety,
              getResources().getDrawable(R.drawable.safety_header));
          case 3:
            return HeaderDesign.fromColorResAndDrawable(
              R.color.other_mobile,
              getResources().getDrawable(R.drawable.mobile_header));
          case 4:
            return HeaderDesign.fromColorResAndDrawable(
              R.color.white,
              getResources().getDrawable(R.drawable.other_contact_header));
        }
        return null;
      }
    });

    mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
    mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

    final View logo = findViewById(R.id.logo_white);
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
