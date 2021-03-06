package com.application.subitoit.githubstargazers.stargazerlist;

import android.util.Log;
import android.util.SparseArray;

import com.application.subitoit.githubstargazers.data.StargazerRepository;
import com.application.subitoit.githubstargazers.stargazerlist.StargazerContract.StargazerPresenterInterface;
import com.application.subitoit.githubstargazers.stargazerlist.StargazerContract.StargazerView;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


public class StargazerPresenter implements StargazerPresenterInterface {
    private static final String TAG = "StargazerPresenter";
    private static WeakReference<StargazerView> wifiDeviceNetworkView;
    private final StargazerRepository repository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected ProgressLoader loader;

    @Inject
    StargazerPresenter(StargazerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void unsubscribe() {
        if (compositeDisposable != null)
            compositeDisposable.dispose();
    }

    /**
     * bind view to presenter
     * @param view
     */
    @Override
    public void bindView(StargazerView view) {
        wifiDeviceNetworkView = new WeakReference<>(view);
        loader = new ProgressLoader(
                view::showStandardLoading,
                view::hideStandardLoading);
    }

    /**
     * delete view
     */
    @Override
    public void deleteView() {
        wifiDeviceNetworkView.clear();
    }

    /**
     * retrieve item obs
     * @param params
     */
    @Override
    public void retrieveItems(SparseArray<String> params) {
        Log.e(TAG, params.toString());
        compositeDisposable.add(repository
                .getStargazer(params.get(0), params.get(1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(composeLoaderTransformer(loader))
                .doOnError(Throwable::printStackTrace)
                .subscribe(
                        items -> wifiDeviceNetworkView.get().onRenderData(items),
                        error ->wifiDeviceNetworkView.get().onError(error.getMessage())));
    }


    /**
     * compose loader transformer
     * @param loader
     * @param <T>
     * @return
     */
    <T extends List>ObservableTransformer<T, T> composeLoaderTransformer(ProgressLoader loader) {
        return upstream -> upstream
                .doOnSubscribe(disposable -> loader.show.run())
                .doOnError(error -> loader.hide.run())
                .doOnNext(res -> loader.hide.run());
    }

    /**
     * progress loader
     */
    class ProgressLoader {
        Action show;
        Action hide;

        ProgressLoader(Action show, Action hide) {
            this.show = show;
            this.hide = hide;
        }
    }

}
