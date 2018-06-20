package com.example.modelb.Model;

import com.example.common.BaseMVP.BaseModel;
import com.example.common.Bean.MyBean;
import com.example.common.RetrofitUtils.ApiClientUtils;
import com.example.common.RetrofitUtils.ApiService;

import rx.Subscriber;

/**
 * Created by admin on 2018/4/2.
 */

public class GetData extends BaseModel implements GetDataModel  {
    protected ApiService apiStores;
    @Override
    public void GetDataInModel(final CallBackLisenters lisenters) {
        apiStores = ApiClientUtils.retrofit(ApiService.API_SERVER_URLTWO).create(ApiService.class);
        addSubscription(apiStores.getDataRxJava(), new Subscriber<MyBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                lisenters.Error(e.toString());
            }
            @Override
            public void onNext(MyBean myBean) {
                lisenters.Succeed(myBean);
            }
        });
    }
}
