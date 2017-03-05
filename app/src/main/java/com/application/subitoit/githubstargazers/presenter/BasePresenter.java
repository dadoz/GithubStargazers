package com.application.subitoit.githubstargazers.presenter;

import java.util.List;

/**
 * Created by davide on 05/03/2017.
 */
public interface BasePresenter {
    void onFinishedRetrieveItems(List<?> items);
    void unsubscribe();
    void onError(String error);
}
