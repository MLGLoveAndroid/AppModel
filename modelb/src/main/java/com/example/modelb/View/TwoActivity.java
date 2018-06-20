package com.example.modelb.View;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.BaseMVP.BaseMvpActivity;
import com.example.common.Bean.MyBean;
import com.example.modelb.Adapter.EnvetImage;
import com.example.modelb.Adapter.RecAdapter;
import com.example.modelb.Presenter.DataInPresener;
import com.example.modelb.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

@Route(path = "/two/activity")
public class TwoActivity extends BaseMvpActivity<ViewTwo, DataInPresener> implements ViewTwo {
private static final String IMAGEURL = "/Image/activity";
    private RecyclerView recycler_view;
    private MyBean myBean;
    private ProgressBar mvp_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public DataInPresener initPresenter() {
        return new DataInPresener();
    }

    @Override
    public void showLoading() {
        mvp_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
mvp_loading.setVisibility(View.GONE);
    }

    @Override
    public void setListItem(Object o) {
        myBean = (MyBean) o;
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(staggeredGridLayoutManager);
        ArrayList<MyBean.ResultsBean> results = (ArrayList<MyBean.ResultsBean>) myBean.getResults();
        RecAdapter recAdapter = new RecAdapter(results, TwoActivity.this);
        recycler_view.setAdapter(recAdapter);
        recAdapter.setItemClickListener(new RecAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(String  imageurl, int position) {
                EventBus.getDefault().postSticky(new EnvetImage(imageurl));
                ARouter.getInstance().build(IMAGEURL).navigation();
            }
        });
    }
    @Override
    public void showMessage(String message) {

    }

    private void initView() {
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        mvp_loading = (ProgressBar) findViewById(R.id.mvp_loading);
    }

}
