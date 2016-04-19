package com.mdevtunisia.manouba.demo.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.util.Log;

import com.mdevtunisia.manouba.demo.util.StringUtil;

import java.io.IOException;

/**
 * Created by yermanov on 17/04/16.
 */
public class WebService {

    private static final String TAG = WebService.class.getSimpleName();

    /**
     * Retrieve person list
     *
     * @param url
     * @return json of person list
     */
    public static String getPersonList(String url) {

        if (StringUtil.isEmpty(url)) {
            Log.w(TAG, "url is empty!");
            return null;
        }

        String result = null;

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                result = response.body().string();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
