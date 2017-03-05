package com.application.subitoit.githubstargazers.presenter;

import java.util.List;

/**
 * Created by davide on 05/03/2017.
 */
public interface StargazerView {
    void bindData(List<?> items, int i);
    void onRetrieveDataError(String error);
}
