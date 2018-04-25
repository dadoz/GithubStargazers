package com.application.subitoit.githubstargazers.data;


import com.application.subitoit.githubstargazers.data.model.Stargazer;

import java.util.List;

import io.reactivex.Observable;

public interface StargazerDataSource {
    Observable<List<Stargazer>> getStargazer(String owner, String repo);
    void setStargazer(List<Stargazer> stargazers);
}
