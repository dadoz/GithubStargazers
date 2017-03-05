package com.application.subitoit.githubstargazers.utils;

import android.app.Application;
import android.util.SparseArray;

import com.application.subitoit.githubstargazers.application.StargazersApplication;

/**
 * Created by davide on 05/03/2017.
 */

public class Utils {
    /**
     *
     * @return
     */
    public static SparseArray<String> buildParams(String owner, String repo) {
        SparseArray<String> params = new SparseArray<>();
        params.put(0, owner);
        params.put(1, repo);
        return params;
    }

}
