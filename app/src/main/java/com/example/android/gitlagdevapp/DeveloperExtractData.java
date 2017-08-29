package com.example.android.gitlagdevapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by tonero hp02 on 20-Aug-17.
 */

public class DeveloperExtractData {

    /**
     * This function initiates the process of making HTTP connection and returns ArrayList of developers.
     *
     * @param stringUrl
     * @return
     */

    public static ArrayList<Developer> initiateConnection(String stringUrl) {
        String jsonResponse = "";
        URL url = getURL(stringUrl);
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("DeveloperAsyncTask", "Error establishing Connection!!!");
        }
        ArrayList<Developer> developers = extractFromJson(jsonResponse);
        return developers;
    }

    /**
     * This function take a string url and the URL Object.
     *
     * @param stringUrl
     * @return
     */
    public static URL getURL(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("DeveloperExtractData", "URL Exception => Not able to convert to url object.");
        }
        return url;
    }

    /**
     * Returns jsonresponse in String format.
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("DeveloperExtractData", "Error response code : " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("DeveloperExtractData", "Error IOExeception");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Return String from inputstream
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return arrayList of Developer Objects.
     *
     * @param jsonData
     * @return
     */
    public static ArrayList<Developer> extractFromJson(String jsonData) {
        ArrayList<Developer> developers = new ArrayList<>();
        try {
            JSONObject baseObject = new JSONObject(jsonData);
            JSONArray itemsArray = new JSONArray(baseObject.getString("items"));
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject arrayObject = itemsArray.getJSONObject(i);

                Developer developer = new Developer();
                developer.setUsername(arrayObject.getString("login"));
                developer.setUrl(arrayObject.getString("html_url"));
                developer.setImage(arrayObject.getString("avatar_url"));
                developers.add(developer);

            }
        } catch (JSONException e) {
            Log.e("DeveloperExtractData", "JSON data extract error.");
        }
        return developers;
    }

}

