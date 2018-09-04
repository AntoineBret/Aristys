package com.aristys.aristysapp.ui.partnair;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.adapter.RecyclerItemClickListener;
import com.aristys.aristysapp.model.Partnair;

import java.util.ArrayList;
import java.util.List;

public class OurPartnairFragment extends Fragment {

    public OurPartnairFragment() {
    }

    private RecyclerView mRecyclerView;
    private PartnairAdapter mAdapter;
    private List<Partnair> partnairList;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ourpartnair, container, false);

        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        /* Change back_arrow icon to hamburger icon */
        toolbar.setNavigationIcon(R.drawable.ic_menu_vector);
        /* Change classic toolbar to action bar and apply the icon change */
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        /* Back arrow or hamburger icon enable = true, disable = false */
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.work_recyclerview);

        partnairList = new ArrayList<>();
        mAdapter = new PartnairAdapter(getContext(), partnairList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String[] links = getResources().getStringArray(R.array.ourpartnair_link);
                Uri uri = Uri.parse(links[position]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        }));

        prepareCompany();

        return rootView;
    }

    private void prepareCompany() {
        int[] covers = new int[]{
                R.drawable.clubnetwork,
                R.drawable.digitalleague,
                R.drawable.maisoninnovergne,
                R.drawable.poleecoconception,
                R.drawable.logopce,
                R.drawable.wasteblasterz,

        };

        Partnair p = new Partnair("Club Network", covers[0]);
        partnairList.add(p);

        p = new Partnair("Digital League", covers[1]);
        partnairList.add(p);

        p = new Partnair("Maison Innovergne", covers[2]);
        partnairList.add(p);

        p = new Partnair("Pole éco-conception", covers[3]);
        partnairList.add(p);

        p = new Partnair("PCE", covers[4]);
        partnairList.add(p);

        p = new Partnair("Wasteblasterz", covers[5]);
        partnairList.add(p);
        mAdapter.notifyDataSetChanged();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        private GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}

