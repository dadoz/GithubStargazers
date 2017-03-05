package com.application.subitoit.githubstargazers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.application.subitoit.githubstargazers.application.StargazersApplication;
import com.application.subitoit.githubstargazers.views.RepoOwnerDataView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Unbinder unbinder;
//    @BindView(R.id.repoOwnerDataViewId)
    RepoOwnerDataView repoOwnerDataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        repoOwnerDataView = (RepoOwnerDataView) findViewById(R.id.repoOwnerDataViewId);
        onInitView();
    }


    @Override
    protected void onDestroy() {
        if (unbinder != null)
            unbinder.unbind();
        super.onDestroy();
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
