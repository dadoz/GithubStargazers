package com.application.subitoit.githubstargazers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class StargazersListActivity extends AppCompatActivity {
    //dadoz/SelectCardViewPrototype
    // API URL https://api.github.com/repos/{owner}/{repo}/stargazers
    //Object -> avatar and username
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stargazers_list);
    }
}
