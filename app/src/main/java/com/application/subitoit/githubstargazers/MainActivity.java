package com.application.subitoit.githubstargazers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.application.subitoit.githubstargazers.stargazerlist.StargazersListActivity;
import com.application.subitoit.githubstargazers.ui.RepoOwnerDataView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RepoOwnerDataView repoOwnerDataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
        onInitView();
    }

    private void bindView() {
        repoOwnerDataView = findViewById(R.id.repoOwnerDataViewId);
    }

    /**
     * init view to handle button in custom view interaction
     */
    private void onInitView() {
        repoOwnerDataView.setFindButtonOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!repoOwnerDataView.isValidInputData()) {
            repoOwnerDataView.setErrorInputData();
            return;
        }

        StargazersApplication application = ((StargazersApplication) getApplication());
        application.setOwner(repoOwnerDataView.getOwner());
        application.setRepo(repoOwnerDataView.getRepo());
        startActivity(new Intent(this, StargazersListActivity.class));
    }
}
