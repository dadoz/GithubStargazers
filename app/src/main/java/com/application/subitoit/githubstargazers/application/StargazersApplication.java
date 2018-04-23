package com.application.subitoit.githubstargazers.application;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class StargazersApplication extends DaggerApplication {
    private String owner;
    private String repo;

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return null;
    }
}
