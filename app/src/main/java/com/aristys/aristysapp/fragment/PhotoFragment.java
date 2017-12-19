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
import com.aristys.aristysapp.adapter.OtherPhotoViewAdapter;
import com.aristys.aristysapp.adapter.StartDesignViewAdapter;
import com.aristys.aristysapp.model.Design;
import com.aristys.aristysapp.model.Photo;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class PhotoFragment extends Fragment {

  private RecyclerView PhotoRecyclerView;
  private OtherPhotoViewAdapter PhotoAdapter;
  public List<Photo> photoList;

  public static PhotoFragment newInstance() {
    return new PhotoFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_photo, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    photoList = new ArrayList<>();

    PhotoRecyclerView = (RecyclerView) getView().findViewById(R.id.photo_recyclerView);
    PhotoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    PhotoRecyclerView.setHasFixedSize(true);
    PhotoRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

    PhotoAdapter = new OtherPhotoViewAdapter(getContext(), photoList);
    PhotoRecyclerView.setAdapter(PhotoAdapter);

    initializeData();
  }

  private void initializeData() {
    int[] covers = new int[]{
      R.drawable.photo_one,
      R.drawable.photo_virtual,
    };

    Photo p = new Photo("Photos","Mettre en avant votre entreprise","Photos prises par une professionnelle de l'image pour mettre en avant votre entreprise. Toutes ces solutions, Aristys-Web vous les propose grâce aux talents de nos partenaires.", covers[0]);
    photoList.add(p);

    p = new Photo("Visites Virtuelles","Vision 360","La visite virtuelle est une technologie novatrice qui se développe de plus en plus ces dernières années, elle permet de développer l'accès - virtuel - au plus grand nombre à votre établissement ", covers[1]);
    photoList.add(p);


    PhotoAdapter.notifyDataSetChanged();
  }
}

