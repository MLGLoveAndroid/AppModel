package com.example.admin.appmodel.debug;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.App.BaseApp;
import com.example.common.BuildConfig;

import org.acra.ACRA;

/**
 * Created by admin on 2018/3/30.
 */

public class MainApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
        ACRA.init(this);
    }
}

