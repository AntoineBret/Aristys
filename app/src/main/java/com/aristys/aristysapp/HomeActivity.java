package com.aristys.aristysapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.aristys.aristysapp.fragment.CRMFragment;
import com.aristys.aristysapp.fragment.CompanyFragment;
import com.aristys.aristysapp.fragment.FindUsFragment;
import com.aristys.aristysapp.fragment.OurServiceFragment;
import com.aristys.aristysapp.fragment.OurWorkFragment;
import com.aristys.aristysapp.fragment.WebViewFragment;
import com.mikepenz.itemanimators.AlphaCrossFadeAnimator;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class HomeActivity extends AppCompatActivity {

  private static final int PROFILE_SETTING = 100000;

  private AccountHeader headerResult = null;
  private Drawer result = null;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    if (savedInstanceState == null) {
      Fragment f = CompanyFragment.newInstance();
      getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).addToBackStack(null).commit();
    }

    headerResult = new AccountHeaderBuilder()
      .withActivity(this)
      .withCompactStyle(false)
      .withHeaderBackground(R.drawable.drawer_header)
      .withSavedInstance(savedInstanceState)
      .build();

    result = new DrawerBuilder()
      .withActivity(this)
      .withHasStableIds(true)
      .withItemAnimator(new AlphaCrossFadeAnimator())
      .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
      .addDrawerItems(
        new PrimaryDrawerItem().withName(R.string.title_company).withDescription(R.string.desc_company).withIcon(R.drawable.ic_home).withIdentifier(1).withSelectable(true),
        new SectionDrawerItem().withName(R.string.drawer_section_business),
        new PrimaryDrawerItem().withName(R.string.title_business).withDescription(R.string.desc_business).withIcon(R.drawable.ic_service).withIdentifier(3).withSelectable(true),
        new PrimaryDrawerItem().withName(R.string.title_ourwork).withDescription(R.string.desc_ourwork).withIcon(R.drawable.ic_ourwork).withIdentifier(4).withSelectable(true),
        new PrimaryDrawerItem().withName(R.string.title_crm).withDescription(R.string.desc_crm).withIcon(R.drawable.ic_dashboard).withIdentifier(5).withSelectable(true),
        new SectionDrawerItem().withName(R.string.drawer_section_social),
        new PrimaryDrawerItem().withName(R.string.title_contact).withDescription(R.string.desc_contact).withIcon(R.drawable.ic_call_light).withIdentifier(7).withSelectable(true),
        new PrimaryDrawerItem().withName(R.string.title_twitter).withIcon(R.drawable.ic_twitter).withIdentifier(8).withSelectable(true),
        new PrimaryDrawerItem().withName(R.string.title_facebook).withIcon(R.drawable.ic_facebook).withIdentifier(9).withSelectable(true),
        new PrimaryDrawerItem().withName(R.string.title_linkedin).withIcon(R.drawable.ic_linkedin).withIdentifier(10).withSelectable(true))

      .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
        @Override
        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
          FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
          fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
          Fragment fragment = new Fragment();
          Bundle bundle = new Bundle();
          switch (position) {
            case 1:
              fragment = new CompanyFragment();
              break;
            case 3:
              fragment = new OurServiceFragment();
              break;
            case 4:
              fragment = new OurWorkFragment();
              break;
            case 5:
              fragment = new CRMFragment();
              break;
            case 7:
              fragment = new FindUsFragment();
              break;
            case 8:
              bundle.putString("url", "https://twitter.com/AristysWeb");
              fragment = new WebViewFragment();
              fragment.setArguments(bundle);
              break;
            case 9:
              bundle.putString("url", "https://www.facebook.com/aristysweb/posts");
              fragment = new WebViewFragment();
              fragment.setArguments(bundle);
              break;
            case 10:
              bundle.putString("url", "https://www.linkedin.com/company/15244012/admin/updates/");
              fragment = new WebViewFragment();
              fragment.setArguments(bundle);
              break;
          }
          fragmentTransaction.replace(R.id.frame_container, fragment);
          fragmentTransaction.commitAllowingStateLoss();
          return false;
        }
      })
      .withShowDrawerOnFirstLaunch(true)
      .build();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return true;
  }

  @Override
  public void onBackPressed() {
    if (result != null && result.isDrawerOpen()) {
      result.closeDrawer();
    } else {
      super.onBackPressed();
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    outState = result.saveInstanceState(outState);
    super.onSaveInstanceState(outState);
  }
}
