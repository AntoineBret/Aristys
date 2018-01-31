package com.aristys.aristysapp.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.adapter.HeaderAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class FindUsFragment extends Fragment implements SlidingUpPanelLayout.PanelSlideListener, OnMapReadyCallback {

  public static FindUsFragment newInstance() {
    return new FindUsFragment();
  }

  private static final String TAG = "FindUsFragment";
  private MapView mapView;
  private HeaderAdapter mHeaderAdapter;
  private View mTransparentView;
  private View mWhiteSpaceView;
  private SlidingUpPanelLayout mLayout;
  private GoogleMap mGoogleMap;
  private Toolbar toolbar;

  private TextView addressTitle, addressSubtitle, scheduleTitle, scheduleSubtitle, phoneTitle, phoneSubtitle, websiteTitle, websiteSubtitle, shareTitle, shareSubtitle;
  private ImageView addressThumbnail, scheduleThumbnail, phoneThumbnail, websiteThumbnail, shareThumbnail;
  private LinearLayout phone_onclick, website_onclick, share_onclick;

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_findus, container, false);

    toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
    /* Change back_arrow icon to hamburger icon */
    toolbar.setNavigationIcon(R.drawable.ic_menu_vector);
    /* Change classic toolbar to action bar and apply the icon change */
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    /* Back arrow or hamburger icon enable = true, disable = false */
    ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    mLayout = (SlidingUpPanelLayout) rootView.findViewById(R.id.slidingLayout);
    mLayout.setAnchorPoint(0.80f);

    mapView = (MapView) rootView.findViewById(R.id.mapView);

    addressTitle = (TextView) rootView.findViewById(R.id.address_title);
    addressSubtitle = (TextView) rootView.findViewById(R.id.address_subtitle);
    addressThumbnail = (ImageView) rootView.findViewById(R.id.address_thumbnail);

    scheduleTitle = (TextView) rootView.findViewById(R.id.schedule_title);
    scheduleSubtitle = (TextView) rootView.findViewById(R.id.schedule_subtitle);
    scheduleThumbnail = (ImageView) rootView.findViewById(R.id.schedule_thumbnail);

    phoneTitle = (TextView) rootView.findViewById(R.id.phone_title);
    phoneSubtitle = (TextView) rootView.findViewById(R.id.phone_subtitle);
    phoneThumbnail = (ImageView) rootView.findViewById(R.id.phone_thumbnail);

    websiteTitle = (TextView) rootView.findViewById(R.id.website_title);
    websiteSubtitle = (TextView) rootView.findViewById(R.id.website_subtitle);
    websiteThumbnail = (ImageView) rootView.findViewById(R.id.website_thumbnail);

    shareTitle = (TextView) rootView.findViewById(R.id.share_title);
    shareSubtitle = (TextView) rootView.findViewById(R.id.share_subtitle);
    shareThumbnail = (ImageView) rootView.findViewById(R.id.share_thumbnail);

    phone_onclick = (LinearLayout) rootView.findViewById(R.id.phone_onclick);
    phone_onclick.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String number = "0769196962";
        Intent mcall = new Intent(Intent.ACTION_DIAL);
        mcall.setData(Uri.parse("tel:" + number));
        startActivity(mcall);
      }
    });

    website_onclick = (LinearLayout) rootView.findViewById(R.id.website_onclick);
    website_onclick.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String url = "http://aristys-web.com/";
        Intent intent1 = new Intent(Intent.ACTION_VIEW);
        intent1.setData(Uri.parse(url));
        startActivity(intent1);
      }
    });

    share_onclick = (LinearLayout) rootView.findViewById(R.id.share_onclick);
    share_onclick.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent sendEmail = new Intent(android.content.Intent.ACTION_SEND);
        sendEmail.setType("plain/text");
        sendEmail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"contact@aristys-web.com"});
        sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, "Carte de visite Aristys-Web");
        sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
          "Aristys-Web: " +'\n'
            + "Jean-Anaël GOBBE, Responsable d'Affaires: " + '\n'
            + "Téléphone: 0769196962 " + '\n'
            + "Adresse e-mail: contact@aristys-web.com" + '\n'
            + "Adresse: Pascalis, 10 Allée Evariste Galois, 63000 Clermont-Ferrand  " + '\n'
            + "Site web: aristys-web.com" + '\n');

        startActivity(Intent.createChooser(sendEmail, "Partager notre carte de visite via..."));
      }
    });
    return rootView;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    if (mapView != null) {
      mapView.onCreate(null);
      mapView.onResume();
      mapView.getMapAsync(this);
    }
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    MapsInitializer.initialize(getContext());
    mGoogleMap = googleMap;
    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    Marker mMarker = mGoogleMap.addMarker(new MarkerOptions()
      .position(new LatLng(45.7597932, 3.1315078000000085))
      .title("Aristys Web")
      .snippet(""));
    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mMarker.getPosition(), 14));
  }

  private void collapseMap() {
    if (mHeaderAdapter != null) {
      mHeaderAdapter.showSpace();
    }

    if (mGoogleMap != null) {
      mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(11f), 1000, null);
    }
  }

  private void expandMap() {
    if (mHeaderAdapter != null) {
      mHeaderAdapter.hideSpace();
    }

    if (mGoogleMap != null) {
      mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(14f), 1000, null);
    }
  }

  @Override
  public void onPanelSlide(View view, float v) {
  }

  @Override
  public void onPanelCollapsed(View view) {
    expandMap();
  }

  @Override
  public void onPanelExpanded(View view) {
    collapseMap();
  }

  @Override
  public void onPanelAnchored(View view) {

  }

  @Override
  public void onPanelHidden(View panel) {
  }

}


