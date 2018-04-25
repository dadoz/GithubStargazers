package com.application.subitoit.githubstargazers.data;

import com.application.subitoit.githubstargazers.data.local.Local;
import com.application.subitoit.githubstargazers.data.local.StargazerLocalDataSource;
import com.application.subitoit.githubstargazers.data.remote.Remote;
import com.application.subitoit.githubstargazers.data.remote.StargazerNetworkDataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class StargzerRepositoryModule {

    @Provides
    @Local
    StargazerDataSource provideStargazerLocalDataSource() {
        return new StargazerLocalDataSource();
    }
    @Provides
    @Remote
    StargazerDataSource provideStargazerRemoteDataSource() {
        return new StargazerNetworkDataSource();
    }
}
