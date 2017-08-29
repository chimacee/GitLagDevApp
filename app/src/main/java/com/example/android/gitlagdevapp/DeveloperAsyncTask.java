package com.example.android.gitlagdevapp;

/**
 * Created by tonero hp02 on 20-Aug-17.
 */

import android.os.AsyncTask;
import java.util.ArrayList;

public class DeveloperAsyncTask extends AsyncTask<String, Void, ArrayList<Developer>> {

    private DeveloperAsyncResponse mDeveloperAsyncResponse = null;

    public DeveloperAsyncTask(DeveloperAsyncResponse developerAsyncResponse) {
        mDeveloperAsyncResponse = developerAsyncResponse;
    }

    @Override
    protected ArrayList<Developer> doInBackground(String... strings) {
        return DeveloperExtractData.initiateConnection(strings[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<Developer> developers) {
        if (developers == null) {
            return;
        }
        mDeveloperAsyncResponse.processFinish(developers);

    }

}
