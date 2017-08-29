package com.example.android.gitlagdevapp;

/**
 * Created by tonero hp02 on 20-Aug-17.
 */

import java.io.Serializable;

@SuppressWarnings("serial")         //With this notation we are going to hide compiler warning.
public class Developer implements Serializable {

    private String image;
    private String username;
    private String url;

    /**
     * Return name of the developer.
     *
     * @return
     */
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Return url of the developer.
     *
     * @return
     */
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Return image of the developer.
     *
     * @return
     */
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }


}
