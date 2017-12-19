package com.aristys.aristysapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.adapter.StartDesignViewAdapter;
import com.aristys.aristysapp.model.Design;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class StartDesignFragment extends Fragment {

    private RecyclerView DesignRecyclerView;
    private StartDesignViewAdapter DesignAdapter;
    public List<Design> designList;

    public static StartDesignFragment newInstance() {
        return new StartDesignFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_startdesign, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        designList = new ArrayList<>();

        DesignRecyclerView = (RecyclerView) getView().findViewById(R.id.design_recyclerView);
        DesignRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        DesignRecyclerView.setHasFixedSize(true);
        DesignRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

        DesignAdapter = new StartDesignViewAdapter(getContext(), designList);
        DesignRecyclerView.setAdapter(DesignAdapter);

        initializeData();
    }

    private void initializeData() {
        int[] covers = new int[]{
                R.drawable.horizon,
                R.drawable.hope,
                R.drawable.fusion,
                R.drawable.origin,
                R.drawable.extra,
                R.drawable.wedding,
        };

        Design d = new Design("Template Horizon", covers[0]);
        designList.add(d);

        d = new Design("Template Hope", covers[1]);
        designList.add(d);

        d = new Design("Template Fusion", covers[2]);
        designList.add(d);

        d = new Design("Template Origin", covers[3]);
        designList.add(d);

        d = new Design("Template Extra", covers[4]);
        designList.add(d);

        d = new Design("Template Wedding", covers[5]);
        designList.add(d);

        DesignAdapter.notifyDataSetChanged();
    }
}

