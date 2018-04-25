package com.application.subitoit.githubstargazers.data.remote;

import com.application.subitoit.githubstargazers.data.StargazerDataSource;
import com.application.subitoit.githubstargazers.data.model.Stargazer;
import com.application.subitoit.githubstargazers.data.remote.services.RetrofitServiceRx;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by davide-syn on 4/24/18.
 */

@Singleton
public class StargazerNetworkDataSource implements StargazerDataSource {
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

    /**
     * TODO plese refactorize
     * @param stargazers
     */
    @Override
    public void setStargazer(List<Stargazer> stargazers) {
    }

    @Override
    public boolean hasStargazer() {
        return false;
    }
}
