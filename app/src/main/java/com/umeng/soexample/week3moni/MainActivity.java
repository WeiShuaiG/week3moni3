package com.umeng.soexample.week3moni;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.soexample.week3moni.presenter.PresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Iview, XRecyclerView.LoadingListener {

    private XRecyclerView xrv_news;
    private String mUrls = "http://www.xieast.com/api/news/news.php?page=1";
    private int mIndex = 1;
    private List<User.DataBean> mListAll;
    private MyAdapter mAdapter;
    private PresenterImpl persenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xrv_news = findViewById(R.id.xrv_news);
        mListAll = new ArrayList<>();
        mAdapter = new MyAdapter(mListAll,this);
        persenter = new PresenterImpl(this);
        persenter.start(mUrls,mIndex);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayout.VERTICAL);
        xrv_news.setLayoutManager(manager);
        xrv_news.setAdapter(mAdapter);
        xrv_news.setLoadingListener(this);
        xrv_news.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        xrv_news.getDefaultRefreshHeaderView()
                .setRefreshTimeVisible(true);
    }



    @Override
    public void success(Object success) {
        User user = (User) success;
        List<User.DataBean> list = user.getData();
        mListAll.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void error(Object error) {

    }

    @Override
    public void onRefresh() {
        mIndex = 1;
        mListAll.clear();
        persenter.start(mUrls,1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                xrv_news.refreshComplete();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        mIndex++;

        persenter.start(mUrls,1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                xrv_news.refreshComplete();
            }
        },2000);

    }
}
