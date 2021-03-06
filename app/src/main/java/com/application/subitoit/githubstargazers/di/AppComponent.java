package com.application.subitoit.githubstargazers.di;

import android.app.Application;

import com.application.subitoit.githubstargazers.StargazersApplication;
import com.application.subitoit.githubstargazers.data.StargzerRepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
//TasksRepositoryModule.class,
@Singleton
@Component(modules = {
        StargzerRepositoryModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<StargazersApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);
        AppComponent build();
    }
}
