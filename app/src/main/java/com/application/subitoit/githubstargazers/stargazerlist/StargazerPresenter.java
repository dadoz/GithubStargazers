package com.application.subitoit.githubstargazers.stargazerlist;

import android.util.Log;
import android.util.SparseArray;

import com.application.subitoit.githubstargazers.di.ActivityScoped;
import com.application.subitoit.githubstargazers.managers.RetrofitManager;
import com.application.subitoit.githubstargazers.stargazerlist.StargazerContract.StargazerPresenterInterface;
import com.application.subitoit.githubstargazers.stargazerlist.StargazerContract.StargazerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@ActivityScoped
public class StargazerPresenter implements StargazerPresenterInterface {
    private static final String TAG = "StargazerPresenter";
    private static WeakReference<StargazerView> wifiDeviceNetworkView;
    private Disposable disposable;

    @Inject
    public StargazerPresenter() {
    }

    @Override
    public void unsubscribe() {
        if (disposable != null)
            disposable.dispose();
    }

    @Override
    public void bindView(StargazerView view) {
        wifiDeviceNetworkView = new WeakReference<>(view);
    }

    @Override
    public void deleteView() {
        wifiDeviceNetworkView.clear();
    }

    @Override
    public void retrieveItems(SparseArray<String> params) {
        Log.e(TAG, params.toString());
        disposable = RetrofitManager.getInstance()
                .getService()
                .getStargazers(params.get(0), params.get(1))
                .filter(list -> list != null && list.size() != 0)
                .switchIfEmpty(Observable.just(new ArrayList<>()))
                .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> wifiDeviceNetworkView.get().onRenderData(items),
                        error -> wifiDeviceNetworkView.get().onError(error.getMessage()));
    }

}
