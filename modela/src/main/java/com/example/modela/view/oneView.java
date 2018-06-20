package com.example.modela.view;

import com.example.common.BaseMVP.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/4/2.
 */

public interface oneView extends BaseView{
    //获取Presenter层传过来的数据，更新UI
    void setListItem(Object o);
    void showMessage(String message);
}
