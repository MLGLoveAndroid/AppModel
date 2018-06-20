package com.example.modelb.View;

import com.example.common.BaseMVP.BaseView;

/**
 * Created by admin on 2018/4/2.
 */

public interface ViewTwo extends BaseView {
    //获取Presenter层传过来的数据，更新UI
    void setListItem(Object o);
    void showMessage(String message);
}
