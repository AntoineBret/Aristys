package com.aristys.aristysapp.ui.home.company;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.aristys.aristysapp.R;
import com.aristys.aristysapp.adapter.ExpandableRecyclerAdapter;
import com.aristys.aristysapp.model.Post;
import com.aristys.aristysapp.remote.ApiClient;
import com.aristys.aristysapp.remote.ApiEndPointInterface;
import com.aristys.aristysapp.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.aristys.aristysapp.utils.Constants.httpcodes.STATUS_UNAUTHORIZED;

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
    private ApiEndPointInterface apiInterface;
    private Post post;

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
        /* Change back_arrow icon to hamburger icon */
        toolbar.setNavigationIcon(R.drawable.ic_menu_vector);
        /* Change classic toolbar to action bar and apply the icon change */
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        /* Back arrow or hamburger icon enable = true, disable = false */
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            Glide.with(this).load(R.drawable.aristysblog_header).into((ImageView) rootView.findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootView;
    }

    public void requestData() {
        apiInterface = new ApiClient(getContext())
                .getClient()
                .create(ApiEndPointInterface.class);

        apiInterface.getPost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Post>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<Post> response) {
                        if ((response.code() == Constants.httpcodes.STATUS_OK) && (response.body() != null)) {
                            adapter.setData(postsList);
                            for (Post post : (Iterable<Post>) response.body().getPosts()){
                                postsList.add(post);
                                post.getImgURL();
                                gridLayoutManager = new StaggeredGridLayoutManager(2, 1);
                                RecycleView.setLayoutManager(gridLayoutManager);
                            }
                        } else if (response.code() == STATUS_UNAUTHORIZED) {
                            Toast.makeText(getContext(), "Error 500", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
