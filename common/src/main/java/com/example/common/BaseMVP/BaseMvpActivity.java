package com.example.common.BaseMVP;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by admin on 2018/3/27.
 */

public abstract class BaseMvpActivity <V,T extends BasePresenter<V>> extends AppCompatActivity {
    //BasePresenter的初始对象，绑定Presenter生命周期
    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }
//开始绑定页面可见
    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V)this);
    }
//绑定销毁解除
    @Override
    protected void onDestroy() {
        presenter.dettach();
        super.onDestroy();
    }

    public abstract T initPresenter();

}
