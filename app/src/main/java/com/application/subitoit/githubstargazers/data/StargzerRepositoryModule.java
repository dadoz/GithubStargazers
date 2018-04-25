package com.application.subitoit.githubstargazers.data;

import com.application.subitoit.githubstargazers.data.local.Local;
import com.application.subitoit.githubstargazers.data.local.StargazerLocalDataSource;
import com.application.subitoit.githubstargazers.data.remote.Remote;
import com.application.subitoit.githubstargazers.data.remote.StargazerNetworkDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StargzerRepositoryModule {

    @Provides
    @Singleton
    @Local
    StargazerDataSource provideStargazerLocalDataSource() {
        return new StargazerLocalDataSource();
    }

    @Provides
    @Singleton
    @Remote
    StargazerDataSource provideStargazerRemoteDataSource() {
        return new StargazerNetworkDataSource();
    }
}
