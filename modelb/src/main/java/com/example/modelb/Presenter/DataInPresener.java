package com.example.modelb.Presenter;

import com.example.common.BaseMVP.BasePresenter;
import com.example.modelb.Model.CallBackLisenters;
import com.example.modelb.Model.GetData;
import com.example.modelb.Model.GetDataModel;
import com.example.modelb.View.ViewTwo;

/**
 * Created by admin on 2018/4/2.
 */

public class DataInPresener extends BasePresenter<ViewTwo> {
    private GetDataModel dataModel;
    public DataInPresener() {
        dataModel = new GetData();
    }
    public void onResume() {
        dataModel.GetDataInModel(new CallBackLisenters() {
            @Override
            public void Succeed(Object o) {
                mView.hideLoading();
                mView.setListItem(o);
            }
            @Override
            public void Error(String s) {
                mView.showMessage(s);
            }
        });
    }
}
