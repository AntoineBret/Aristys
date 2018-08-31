package com.aristys.aristysapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.aristys.aristysapp.ui.start.contact.StartContactFragment;
import com.aristys.aristysapp.ui.start.design.StartDesignFragment;
import com.aristys.aristysapp.ui.start.progress.StartProgressFragment;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

public class StartActivity extends AppCompatActivity {

  MaterialViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);
    setTitle("");

      Window w = getWindow();
      w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


    mViewPager = (MaterialViewPager) findViewById(R.id.start_materialViewPager);
    final Toolbar toolbar = mViewPager.getToolbar();
    if (toolbar != null) {
      setSupportActionBar(toolbar);
    }

    mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

      @Override
      public Fragment getItem(int position) {
        switch (position % 3) {
          case 0:
            return StartProgressFragment.newInstance();
          case 1:
            return StartDesignFragment.newInstance();
          case 2:
            return StartContactFragment.newInstance();
          default:
            return StartProgressFragment.newInstance();
        }
      }

      @Override
      public int getCount() {
        return 3;
      }

      @Override
      public CharSequence getPageTitle(int position) {
        switch (position % 3) {
          case 0:
            return "Progress";
          case 1:
            return "Design";
          case 2:
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
              R.color.start_progress,
              ResourcesCompat.getDrawable(getResources(),R.drawable.skytwo, null));
          case 1:
            return HeaderDesign.fromColorResAndDrawable(
              R.color.start_design,
              ResourcesCompat.getDrawable(getResources(),R.drawable.skyone, null));
          case 2:
            return HeaderDesign.fromColorResAndDrawable(
              R.color.start_contact,
              ResourcesCompat.getDrawable(getResources(),R.drawable.start_contact, null));
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
    int fragments = getSupportFragmentManager().getBackStackEntryCount();
    if (fragments == 1) {
      finish();
    } else {
      if (getFragmentManager().getBackStackEntryCount() > 1) {
        getFragmentManager().popBackStack();
      } else {
        super.onBackPressed();
        overridePendingTransition(0, android.R.anim.slide_out_right);
      }
    }
  }
}
