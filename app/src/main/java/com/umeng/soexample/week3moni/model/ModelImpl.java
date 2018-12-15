package com.umeng.soexample.week3moni.model;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.umeng.soexample.week3moni.User;
import com.umeng.soexample.week3moni.callback.MyCallBack;

import java.io.IOException;
import java.util.jar.Manifest;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by W on 2018/12/15.
 */

public class ModelImpl implements Model {
    private MyCallBack callBack;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            User user = (User) msg.obj;
            callBack.setSuccess(user);
            callBack.setError(user);
        }
    };
    @Override
    public void getData(String url, int index, MyCallBack callBack) {
        this.callBack = callBack;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                User userBean = new Gson().fromJson(response.body().charStream(), User.class);
                Message message = new Message();
                message.obj = userBean;
                handler.sendMessage(message);
            }
        });

    }
}
