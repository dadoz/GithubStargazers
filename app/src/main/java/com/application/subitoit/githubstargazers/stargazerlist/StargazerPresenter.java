package com.application.subitoit.githubstargazers.stargazerlist;

import android.util.Log;
import android.util.SparseArray;

import com.application.subitoit.githubstargazers.data.StargazerRepository;
import com.application.subitoit.githubstargazers.di.ActivityScoped;
import com.application.subitoit.githubstargazers.stargazerlist.StargazerContract.StargazerPresenterInterface;
import com.application.subitoit.githubstargazers.stargazerlist.StargazerContract.StargazerView;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


@ActivityScoped
public class StargazerPresenter implements StargazerPresenterInterface {
    private static final String TAG = "StargazerPresenter";
    private static WeakReference<StargazerView> wifiDeviceNetworkView;
    private final StargazerRepository repository;
    private Disposable disposable;
    protected ProgressLoader loader;
    @Inject
    public StargazerPresenter(StargazerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void unsubscribe() {
        if (disposable != null)
            disposable.dispose();
    }

    @Override
    public void bindView(StargazerView view) {
        wifiDeviceNetworkView = new WeakReference<>(view);
        loader = new ProgressLoader(
                view::showStandardLoading,
                view::hideStandardLoading);
    }

    @Override
    public void deleteView() {
        wifiDeviceNetworkView.clear();
    }

    @Override
    public void retrieveItems(SparseArray<String> params) {
        Log.e(TAG, params.toString());
        Disposable subscirbe = repository.getStargazer(params.get(0), params.get(1))
                .compose(composeLoaderTransformer(loader))
                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> wifiDeviceNetworkView.get().onRenderData(items),
                        error -> wifiDeviceNetworkView.get().onError(error.getMessage()));
    }


    <T extends List>ObservableTransformer<T, T> composeLoaderTransformer(ProgressLoader loader) {
        return upstream -> upstream
                .doOnSubscribe(disposable -> loader.show.run())
                .doOnError(error -> loader.hide.run())
                .doOnNext(res -> loader.hide.run());
    }

    /**
     *
     */
    class ProgressLoader {
        Action show;
        Action hide;
        public ProgressLoader(Action show, Action hide) {
            this.show = show;
            this.hide = hide;
        }
    }

}
