package com.application.subitoit.githubstargazers;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ProgressBar;

import com.application.subitoit.githubstargazers.adapter.StargazerListAdapter;
import com.application.subitoit.githubstargazers.application.StargazersApplication;
import com.application.subitoit.githubstargazers.presenter.StargazerPresenter;
import com.application.subitoit.githubstargazers.presenter.StargazerView;
import com.application.subitoit.githubstargazers.utils.Utils;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StargazersListActivity extends AppCompatActivity implements StargazerView {
    private String owner;
    private String repo;
    @BindView(R.id.stargazerRecyclerViewId)
    RecyclerView recyclerView;
    @BindView(R.id.stargazerProgressbarId)
    ProgressBar progressBar;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stargazers_list);
        unbinder = ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        repo = ((StargazersApplication) getApplication()).getRepo();
        owner = ((StargazersApplication) getApplication()).getOwner();
        onInitView();
    }


    @Override
    protected void onDestroy() {
//        if (unbinder != null)
//            unbinder.unbind();
        super.onDestroy();
    }

    /**
     * iit view and retrieve stargazers data
     */
    private void onInitView() {
        new StargazerPresenter().init(new WeakReference<>(this),
                new WeakReference<>(this), Utils.buildParams(owner, repo));
    }

    @Override
    public void bindData(List<?> items, int i) {
        progressBar.setVisibility(View.GONE);
        initRecyclerView(items);
    }


    @Override
    public void onRetrieveDataError(String error) {
        //TODO implement it - show a view maybe
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        Snackbar.make(findViewById(R.id.activity_main), R.string.retrieve_error, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * init recycler view binding data by adapter
     * @param items
     */
    private void initRecyclerView(List<?> items) {
        if (items.size() == 0)
            return;
        recyclerView.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new StargazerListAdapter(items));
    }

}
