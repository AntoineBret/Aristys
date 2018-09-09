package com.aristys.aristysapp;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aristys.aristysapp.api.GoogleSignInSingleton;
import com.aristys.aristysapp.session.AuthManager;
import com.aristys.aristysapp.ui.home.company.CompanyFragment;
import com.aristys.aristysapp.ui.home.find_us.FindUsFragment;
import com.aristys.aristysapp.ui.home.our_work.OurWorkFragment;
import com.aristys.aristysapp.ui.home.service.OurServiceFragment;
import com.aristys.aristysapp.ui.home.webview.WebViewFragment;
import com.aristys.aristysapp.ui.partnair.OurPartnairFragment;
import com.aristys.aristysapp.utils.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.mikepenz.itemanimators.AlphaCrossFadeAnimator;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;

public class HomeActivity extends AppCompatActivity {

  private AccountHeader headerResult = null;
  private Drawer result = null;
  private AnimatedVectorDrawable mMenuDrawable;
  private AnimatedVectorDrawable mBackDrawable;
  private AuthManager authManager;

  GoogleSignInSingleton signInSingleton = GoogleSignInSingleton.getInstance(null);
  GoogleSignInAccount googleAccount = signInSingleton.getGoogleSignIn();

//  private IProfile profile = new ProfileDrawerItem()
//    .withName(googleAccount.getDisplayName())
//    .withEmail(googleAccount.getEmail())
//    .withIcon(googleAccount.getPhotoUrl())
//    .withIdentifier(Constants.menu_drawer.defaultProfile);

    private IProfile profile = new ProfileDrawerItem()
    .withName("")
    .withEmail("")
    .withIcon(R.drawable.ic_address)
    .withIdentifier(Constants.menu_drawer.defaultProfile);

  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    authManager = AuthManager.getInstance();
    authManager.onCreate(this, null);

    createAccountHeader(googleAccount);
    createDrawer();

    mMenuDrawable = (AnimatedVectorDrawable) getDrawable(R.drawable.ic_menu_animatable);
    mBackDrawable = (AnimatedVectorDrawable) getDrawable(R.drawable.ic_back_animatable);

    if (savedInstanceState == null) {
      Fragment f = CompanyFragment.newInstance();
      getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).addToBackStack(null).commit();
    }
  }

  private void createAccountHeader(GoogleSignInAccount googleAccount) {

    //initialize and create the image loader logic
    DrawerImageLoader.init(new AbstractDrawerImageLoader() {
      @Override
      public void set(ImageView imageView, Uri uri, Drawable placeholder) {
        Glide.
          with(imageView.getContext())
          .load(uri)
          .apply(new RequestOptions().placeholder(placeholder))
          .into(imageView);
      }

      @Override
      public void cancel(ImageView imageView) {
        Glide.with(imageView.getContext()).clear(imageView);
      }
    });

    headerResult = new AccountHeaderBuilder()
      .withActivity(this)
      .withTranslucentStatusBar(true)
      .withHeaderBackground(R.drawable.gradient_colorprimary)
      .addProfiles(profile, new ProfileSettingDrawerItem().withName(getString(R.string.navHeaderSignOut)).withIdentifier(Constants.menu_drawer.profileRemoveID))
      .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
        @Override
        public boolean onProfileChanged(View view, IProfile profile, boolean current) {
          //if the clicked item has the identifier 1 add a new profile ;)
          if (profile.getIdentifier() == Constants.menu_drawer.profileRemoveID) {
            authManager.signOut();
            getSharedPreferences(Constants.preference.preferenceKey, MODE_PRIVATE).edit().putBoolean(Constants.preference.preferenceFirstRun, true).commit();
            Intent i = new Intent(getApplicationContext(), StartSignInActivity.class);
            startActivity(i);
            finish();
            Toast.makeText(getApplicationContext(), R.string.debugSignOut, Toast.LENGTH_LONG).show();
          }
          //false if you have not consumed the event and it should close the drawer
          return false;
        }
      })
      .build();
  }

  private void createDrawer() {
    result = new DrawerBuilder()
      .withActivity(this)
      .withAccountHeader(headerResult)
      .withDisplayBelowStatusBar(true)
      .withTranslucentStatusBar(true)
      .withHasStableIds(true)
      .withItemAnimator(new AlphaCrossFadeAnimator())
      .addDrawerItems(
        new PrimaryDrawerItem().withName(R.string.title_company).withDescription(R.string.desc_company).withIcon(R.drawable.ic_home).withIdentifier(Constants.menu_drawer.blog).withSelectable(true),
        new SectionDrawerItem().withName(R.string.drawer_section_business),

        new PrimaryDrawerItem().withName(R.string.title_business).withDescription(R.string.desc_business).withIcon(R.drawable.ic_service).withIdentifier(Constants.menu_drawer.business).withSelectable(true),
        new PrimaryDrawerItem().withName(R.string.title_ourwork).withDescription(R.string.desc_ourwork).withIcon(R.drawable.ic_ourwork).withIdentifier(Constants.menu_drawer.work).withSelectable(true),
        new PrimaryDrawerItem().withName(R.string.title_community_partner).withDescription(R.string.desc_community_partner).withIcon(R.drawable.ic_partner).withIdentifier(Constants.menu_drawer.partnair).withSelectable(true),

        new SectionDrawerItem().withName(R.string.drawer_section_social),
        new PrimaryDrawerItem().withName(R.string.title_contact).withDescription(R.string.desc_contact).withIcon(R.drawable.ic_call_light).withIdentifier(Constants.menu_drawer.contact).withSelectable(true),
        new PrimaryDrawerItem().withName(R.string.title_twitter).withIcon(R.drawable.ic_twitter).withIdentifier(Constants.menu_drawer.twitter).withSelectable(true),
        new PrimaryDrawerItem().withName(R.string.title_facebook).withIcon(R.drawable.ic_facebook).withIdentifier(Constants.menu_drawer.facebook).withSelectable(true),
        new PrimaryDrawerItem().withName(R.string.title_linkedin).withIcon(R.drawable.ic_linkedin).withIdentifier(Constants.menu_drawer.linkedin).withSelectable(true))

      .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
        @Override
        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
          FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

          fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
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
              fragment = new OurPartnairFragment();
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
          fragmentTransaction.addToBackStack(null);
          fragmentTransaction.commit();
          return false;
        }
      })
      .withShowDrawerOnFirstLaunch(true)
      .build();
//    result.setSelection(Constants.CoachingSport, true);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      result.openDrawer();
      menuClick();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onBackPressed() {
    if (result != null && result.isDrawerOpen()) {
      result.closeDrawer();
    } else {
      int fragments = getSupportFragmentManager().getBackStackEntryCount();
      if (fragments == 1) {
        finish();
      } else {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
          getFragmentManager().popBackStack();
        } else {
          super.onBackPressed();
        }
      }
    }
  }

  private void menuClick() {
    if (result != null && result.isDrawerOpen()) {
      getSupportActionBar().setHomeAsUpIndicator(mMenuDrawable);
      mMenuDrawable.start();
    } else {
      getSupportActionBar().setHomeAsUpIndicator(mBackDrawable);
      mBackDrawable.start();
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    outState = result.saveInstanceState(outState);
    super.onSaveInstanceState(outState);
  }
}
