package com.example.common.RetrofitUtils;

import com.example.common.Bean.FunnyBean;
import com.example.common.Bean.MyBean;

import retrofit2.http.GET;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by admin on 2018/3/27.
 */

public interface ApiService {
    //https://www.apiopen.top/satinApi?type=1&page=1
    String API_SERVER_URL = "https://www.apiopen.top";
    //http://gank.io/api/data/福利/10/1
    String API_SERVER_URLTWO = "http://gank.io";
    @GET("/satinApi?type=1&page=1")
    Observable<FunnyBean> loadDataByRetrofitRxjava();
   @GET("/api/data/福利/20/1")
    Observable<MyBean> getDataRxJava();


}
