package com.application.subitoit.githubstargazers.stargazerlist;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class StargazerPresenterModule {
    @Binds
    abstract StargazerContract.StargazerPresenterInterface stargazerPresenter(StargazerPresenter presenter);
}
