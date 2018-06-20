package com.example.modela.presenter;

import android.graphics.Paint;

import com.example.common.BaseMVP.BasePresenter;
import com.example.modela.model.CallBackLisenter;
import com.example.modela.model.RequestBiz;
import com.example.modela.model.RequestIp;
import com.example.modela.view.oneView;

/**
 * Created by admin on 2018/4/2.
 */

public class ModelApresenter extends BasePresenter<oneView> {
        private RequestBiz biz;

    public ModelApresenter() {
        this.biz = new RequestIp();
    }
    public  void onResume(){
        biz.getDataInModel(new CallBackLisenter() {
            @Override
            public void sueeccd(Object o) {
                mView.hideLoading();
                mView.setListItem(o);
            }

            @Override
            public void Error(String  s) {
            mView.showMessage(s);
            }
        });
    }
}
