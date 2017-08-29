package com.example.android.gitlagdevapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Developer>> {


    private static final int DEVELOPER_LOADER_ID = 1;

    ListView listView;
    ArrayList<Developer> developers;
    DeveloperAdapter adapter;

    View noInternetScreenView;
    View noDataView;
    public static final String URL = "https://api.github.com/search/users?q=language:Java%20location:lagos";
    //public static final String URL = "https://api.github.com/search/users?q=language:Java%20location:lagos";
    View loadingScreenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        developers = new ArrayList<>();
        adapter = new DeveloperAdapter(this, R.layout.developer_list_item, developers);

        /*
        Initialising default Views
         */
        loadingScreenView = findViewById(R.id.loading_screen);
        noInternetScreenView = findViewById(R.id.no_internet_screen);
        noDataView = findViewById(R.id.no_data);

        listView.setAdapter(adapter);

        /*
        Getting Connectivity service.
         */
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(DEVELOPER_LOADER_ID, null, this);

        } else {
            /*
            This code will work when there is no internet connectivity.
             */
            listView.setVisibility(View.GONE);
            loadingScreenView.setVisibility(View.GONE);
            noDataView.setVisibility(View.GONE);
            noInternetScreenView.setVisibility(View.VISIBLE);
        }

        // listening to single list item on click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Launching new Activity on selecting a single List Item
                Intent intent = new Intent(getApplicationContext(), DeveloperDetailActivity.class);
                // sending data to new activity
                intent.putExtra("developerUsername", developers.get(i).getUsername() );
                intent.putExtra("developerUrl", developers.get(i).getUrl() );
                intent.putExtra("developerImage", developers.get(i).getImage());
                startActivity(intent);
           }
        });

    }

    @Override
    public Loader<ArrayList<Developer>> onCreateLoader(int i, Bundle bundle) {
        return new DeveloperLoader(this, URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Developer>> loader, ArrayList<Developer> developersList) {

        if (developersList.size() != 0) {
            developers.clear();
            developers.addAll(developersList);
            loadingScreenView.setVisibility(View.GONE);
            noDataView.setVisibility(View.GONE);
            noInternetScreenView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        } else {
            loadingScreenView.setVisibility(View.GONE);
            noInternetScreenView.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);
            noDataView.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Developer>> loader) {
        adapter.clear();
        developers.clear();
    }

}