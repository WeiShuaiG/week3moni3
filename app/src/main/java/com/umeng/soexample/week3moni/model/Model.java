package com.umeng.soexample.week3moni.model;

import com.umeng.soexample.week3moni.callback.MyCallBack;

/**
 * Created by W on 2018/12/15.
 */

public interface Model {
    void getData(String url, int index, MyCallBack callBack);
}
