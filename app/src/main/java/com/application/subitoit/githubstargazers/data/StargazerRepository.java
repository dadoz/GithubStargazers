package com.application.subitoit.githubstargazers.data;

import com.application.subitoit.githubstargazers.data.local.Local;
import com.application.subitoit.githubstargazers.data.local.StargazerLocalDataSource;
import com.application.subitoit.githubstargazers.data.model.Stargazer;
import com.application.subitoit.githubstargazers.data.remote.Remote;
import com.application.subitoit.githubstargazers.data.remote.StargazerNetworkDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by davide-syn on 4/24/18.
 */

public class StargazerRepository {

    private final StargazerDataSource localDataSource;
    private final StargazerDataSource networkDataSource;

    @Inject
    StargazerRepository(@Local StargazerDataSource localDataSource, @Remote StargazerDataSource networkDataSource) {
        this.localDataSource = localDataSource;
        this.networkDataSource = networkDataSource;
    }

    /**
     * get cached or network data
     * @return
     */
    public Observable<List<Stargazer>> getStargazer(String owner, String repo) {
        if (localDataSource.getStargazer(owner, repo) != null) {
            //show data from cache
            return localDataSource.getStargazer(owner, repo);
        }

        //show data from netwkor and added on cache if some result
        return networkDataSource.getStargazer(owner, repo)
                .doOnNext(localDataSource::setStargazer);
    }

    public void refreshStargazer() {
        //TODO implement it
    }

    public void refreshCache() {
        //TODO implement it
    }

}
