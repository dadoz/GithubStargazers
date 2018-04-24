package com.application.subitoit.githubstargazers.stargazerlist;

import android.util.SparseArray;

import com.application.subitoit.githubstargazers.BasePresenter;

import java.util.List;

public interface StargazerContract {

    interface StargazerView {
        void onRenderData(List<?> items);
        void onError(String error);
        void showStandardLoading();
        void hideStandardLoading();
    }
    interface StargazerPresenterInterface extends BasePresenter {
        void unsubscribe();
        void retrieveItems(SparseArray<String> params);
        void bindView(StargazerView view);
        void deleteView();
    }
}