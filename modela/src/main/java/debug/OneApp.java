package debug;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.App.BaseApp;

/**
 * Created by admin on 2018/3/30.
 */

public class OneApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
    }
}
