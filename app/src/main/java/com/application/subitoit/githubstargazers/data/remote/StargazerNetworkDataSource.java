package com.application.subitoit.githubstargazers.data.remote;

import com.application.subitoit.githubstargazers.data.model.Stargazer;
import com.application.subitoit.githubstargazers.data.remote.services.RetrofitServiceRx;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by davide-syn on 4/24/18.
 */

public class StargazerNetworkDataSource {
    /**
     *
     * @param owner
     * @param repo
     * @return
     */
    public Observable<List<Stargazer>> getStargazer(String owner, String repo) {
        return new RetrofitServiceRx().getStagazerRetrofit()
                .getStargazers(owner, repo);
    }
}
