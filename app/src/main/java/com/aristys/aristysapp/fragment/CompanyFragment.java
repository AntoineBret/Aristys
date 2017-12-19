package com.aristys.aristysapp.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.aristys.aristysapp.R;
import com.aristys.aristysapp.adapter.ExpandableRecyclerAdapter;
import com.aristys.aristysapp.interfaces.NetworkController;
import com.aristys.aristysapp.model.Post;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CompanyFragment extends Fragment {

  public static CompanyFragment newInstance() {
    return new CompanyFragment();
  }

    public RecyclerView RecycleView;
    private StaggeredGridLayoutManager gridLayoutManager;
    public List<Post> postsList = new ArrayList<Post>();
    private Menu menu;
    private ExpandableRecyclerAdapter adapter;
    private boolean isListView = false;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    RequestQueue requestQueue;
    String URL = "https://public-api.wordpress.com/rest/v1.1/sites/blogaristysweb.wordpress.com/posts/";

  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        RecycleView = (RecyclerView) rootView.findViewById(R.id.wprecycler_view);
        RecycleView.setHasFixedSize(true);

        requestData();

        adapter = new ExpandableRecyclerAdapter(getContext(), postsList);
        RecycleView.setAdapter(adapter);

      toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
      ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
      ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        /* Back arrow enable */
      ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
      setHasOptionsMenu(true);

        try {
            Glide.with(this).load(R.drawable.aristysblog_header).into((ImageView) rootView.findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootView;
    }

    public void requestData() {
        requestQueue = NetworkController.getInstance(getContext()).getRequestQueue();

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray ja = response.getJSONArray("posts");
                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject jsonObject = ja.getJSONObject(i);
                                Post post = Post.parse(jsonObject);
                                postsList.add(post);
                                gridLayoutManager = new StaggeredGridLayoutManager(2, 1);
                                RecycleView.setLayoutManager(gridLayoutManager);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        requestQueue.add(jor);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) getView().findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) getView().findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.company));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
        this.menu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_toggle:
                toggle();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggle() {
        if (isListView) {
            showGridView();
        } else {
            showListView();
        }
    }

    private void showListView() {
        gridLayoutManager.setSpanCount(1);
        MenuItem menuItem = menu.findItem(R.id.action_toggle);
        menuItem.setIcon(R.drawable.ic_action_grid);
        menuItem.setTitle(R.string.show_as_grid);
        isListView = true;
    }

    private void showGridView() {
        gridLayoutManager.setSpanCount(2);
        MenuItem menuItem = menu.findItem(R.id.action_toggle);
        menuItem.setIcon(R.drawable.ic_action_list);
        menuItem.setTitle(R.string.show_as_list);
        isListView = false;
    }
}
