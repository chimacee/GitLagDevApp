package com.example.android.gitlagdevapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by tonero hp02 on 20-Aug-17.
 */

public class DeveloperLoader extends AsyncTaskLoader<ArrayList<Developer>> {

    private String mUrl;

    public DeveloperLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Developer> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        return DeveloperExtractData.initiateConnection(mUrl);
    }
}
