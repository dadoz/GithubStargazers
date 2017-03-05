package com.application.subitoit.githubstargazers;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.application.subitoit.githubstargazers.application.StargazersApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Unbinder unbinder;

    @BindView(R.id.findButtonId)
    Button findButton;
    @BindView(R.id.ownerTextInputLayoutId)
    TextInputLayout ownerTextInputLayout;
    @BindView(R.id.repoTextInputLayoutId)
    TextInputLayout repoTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.setDebug(true);
        unbinder = ButterKnife.bind(this);
        onInitView();
    }


    @Override
    protected void onDestroy() {
//        if (unbinder != null)
//            unbinder.unbind();
        super.onDestroy();
    }

    /**
     * init view to handle button in custom view interaction
     */
    private void onInitView() {
        findButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        StargazersApplication application = ((StargazersApplication) getApplication());
        application.setOwner(ownerTextInputLayout.getEditText().getText().toString());
        application.setRepo(repoTextInputLayout.getEditText().getText().toString());
        startActivity(new Intent(this, StargazersListActivity.class));
    }
}
