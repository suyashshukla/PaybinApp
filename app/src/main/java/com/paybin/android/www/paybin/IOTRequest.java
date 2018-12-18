package com.paybin.android.www.paybin;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class IOTRequest {

    static URL url;

    public static void makeRequest(String bolt){


        try {
            url = new URL(bolt);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }



}
