package com.umeng.soexample.week3moni.callback;

/**
 * Created by W on 2018/12/15.
 */

public interface MyCallBack<T> {
    void setSuccess(T success);
    void setError(T error);
}
