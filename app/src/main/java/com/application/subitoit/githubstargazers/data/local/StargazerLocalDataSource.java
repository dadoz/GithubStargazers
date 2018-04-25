package com.application.subitoit.githubstargazers.data.local;


import com.application.subitoit.githubstargazers.data.StargazerDataSource;
import com.application.subitoit.githubstargazers.data.model.Stargazer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * In Ram memory storage
 */
@Singleton
public class StargazerLocalDataSource implements StargazerDataSource {
    private List list = new ArrayList();

    public Observable<List<Stargazer>> getStargazer(String owner, String repo) {
        return Observable.just(list);
    }

    @Override
    public boolean hasStargazer() {
        return list.size() != 0;
    }

    public void setStargazer(List stargazerList) {
        list.addAll(stargazerList);
    }
}
