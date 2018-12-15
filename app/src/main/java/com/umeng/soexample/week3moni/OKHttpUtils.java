package com.umeng.soexample.week3moni;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by W on 2018/12/15.
 */

public class OKHttpUtils {
    private OkHttpClient okHttpClient;


    //这是同步的get和post
    public String getSync(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        return okHttpClient.newCall(request).execute().body().string();
    }

    public String postSync(String url, String key, String value) throws IOException {
        RequestBody body = new FormBody.Builder().add(key, value).build();
        Request request = new Request.Builder().url(url).post(body).build();
        return okHttpClient.newCall(request).execute().body().string();
    }

    //异步的get和post

    public void getAsync(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void postAsync(String url, Callback callback) {
        RequestBody body = new FormBody.Builder().add("key", "value").build();
        Request request = new Request.Builder().url(url).post(body).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

}
