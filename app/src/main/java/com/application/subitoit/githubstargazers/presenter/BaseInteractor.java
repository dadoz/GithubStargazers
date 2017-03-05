package com.application.subitoit.githubstargazers.presenter;

import android.util.SparseArray;

import java.lang.ref.WeakReference;

/**
 * Created by davide on 05/03/2017.
 */
public interface BaseInteractor {
    void unbindSubscription();

    void retrieveItems(WeakReference<BasePresenter> listener, SparseArray<String> params);
}
