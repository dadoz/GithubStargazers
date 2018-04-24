package com.application.subitoit.githubstargazers.data.remote.services;


import com.application.subitoit.githubstargazers.data.model.Stargazer;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StargazerService {
    @GET("repos/{owner}/{repo}/stargazers")
    Observable<List<Stargazer>> getStargazers(@Path("owner") String owner, @Path("repo") String repo);
}
