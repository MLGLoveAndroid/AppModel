package com.example.common.BaseMVP;


import com.example.common.RetrofitUtils.ApiClientUtils;
import com.example.common.RetrofitUtils.ApiService;

/**
 * Created by admin on 2018/3/27.
 */

public class BasePresenter<T> {
    //View层绑定Presenterc层
    public T mView;
   // protected ApiService apiStores;

    //View层开始绑定Presenter层
    public void attach(T mView){
        this.mView = mView;
       // apiStores = ApiClientUtils.retrofit().create(ApiService.class);
    }
//View层接触绑定View负值为null
    public void dettach(){
        mView = null;
    }

}
