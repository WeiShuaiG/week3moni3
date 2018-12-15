package com.umeng.soexample.week3moni.presenter;

import com.umeng.soexample.week3moni.Iview;
import com.umeng.soexample.week3moni.callback.MyCallBack;
import com.umeng.soexample.week3moni.model.ModelImpl;

/**
 * Created by W on 2018/12/15.
 */

public class PresenterImpl implements Presenter {
    private ModelImpl model;
    private Iview iview;
    public PresenterImpl(Iview iview){
        this.iview = iview;
        model = new ModelImpl();
    }
    @Override
    public void start(String url, final int index) {
        model.getData(url, index, new MyCallBack() {
            @Override
            public void setSuccess(Object success) {
                iview.success(success);
            }

            @Override
            public void setError(Object error) {

                iview.error(error);
            }
        });
    }
}
