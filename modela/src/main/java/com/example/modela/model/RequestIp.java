package com.example.modela.model;

import com.example.common.BaseMVP.BaseModel;
import com.example.common.Bean.FunnyBean;
import com.example.common.RetrofitUtils.ApiClientUtils;
import com.example.common.RetrofitUtils.ApiService;

import rx.Subscriber;

/**
 * Created by admin on 2018/4/2.
 */

public class RequestIp extends BaseModel implements RequestBiz {
    protected ApiService apiStores;
    @Override
    public void getDataInModel(final CallBackLisenter lisenter ) {
        apiStores = ApiClientUtils.retrofit(ApiService.API_SERVER_URL).create(ApiService.class);
        addSubscription(apiStores.loadDataByRetrofitRxjava(), new Subscriber<FunnyBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            lisenter.Error(e.toString());
            }

            @Override
            public void onNext(FunnyBean funnyBean) {
                lisenter.sueeccd(funnyBean);
            }
        });
    }
}
