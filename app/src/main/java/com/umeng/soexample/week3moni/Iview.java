package com.umeng.soexample.week3moni;

/**
 * Created by W on 2018/12/15.
 */

public interface Iview<T> {
    void success(T success);
    void error(T error);
}
