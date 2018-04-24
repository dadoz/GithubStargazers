package com.application.subitoit.githubstargazers.data.local;


import com.application.subitoit.githubstargazers.data.model.Stargazer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * In Ram memory storage
 */
public class StargazerLocalDataSource {
    private List list = new ArrayList();

    public Observable<List<Stargazer>> getStargazer(String owner, String repo) {
        return Observable.just(list);
    }

    public void setStargazer(List stargazerList) {
        list.addAll(stargazerList);
    }
}
