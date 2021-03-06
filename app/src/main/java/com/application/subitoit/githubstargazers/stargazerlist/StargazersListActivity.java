package com.application.subitoit.githubstargazers.stargazerlist;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.subitoit.githubstargazers.R;
import com.application.subitoit.githubstargazers.adapter.StargazerListAdapter;
import com.application.subitoit.githubstargazers.StargazersApplication;
import com.application.subitoit.githubstargazers.stargazerlist.StargazerContract.StargazerView;
import com.application.subitoit.githubstargazers.ui.EmptyView;
import com.application.subitoit.githubstargazers.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * stargazer activity
 */
public class StargazersListActivity extends DaggerAppCompatActivity implements StargazerView {
    private String owner;
    private String repo;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    EmptyView emptyView;

    @Inject
    StargazerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stargazers_list);

        //TODO wth this is really terrible
        repo = ((StargazersApplication) getApplication()).getRepo();
        owner = ((StargazersApplication) getApplication()).getOwner();

        bindView();
        onInitView();
    }

    /**
     * TODO move to butterknife
     */
    private void bindView() {
        recyclerView =  findViewById(R.id.stargazerRecyclerViewId);
        progressBar = findViewById(R.id.stargazerProgressbarId);
        emptyView = findViewById(R.id.emptyViewId);
    }

    /**
     * iit view and retrieve stargazers data
     */
    private void onInitView() {
        initActionbar();
        presenter.bindView(this);
        presenter.retrieveItems(Utils.buildParams(owner, repo));
    }

    /**
     * TODO mv to base activity
     * actionbar set listener and back arrow
     */
    private void initActionbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * todo mv on base
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (presenter != null)
                    presenter.unsubscribe();
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRenderData(List<?> items) {
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        initRecyclerView(items);
    }


    @Override
    public void onError(String error) {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        Snackbar.make(findViewById(R.id.activity_main), R.string.retrieve_error,
                Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showStandardLoading() {
        Toast.makeText(this, "show loader", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideStandardLoading() {
        Toast.makeText(this, "hide loader", Toast.LENGTH_SHORT).show();
    }

    /**
     * init recycler view binding data by adapter
     * @param items
     */
    private void initRecyclerView(List<?> items) {
        if (items.size() == 0) {
            return;
        }
        recyclerView.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new StargazerListAdapter(items));
    }
}
