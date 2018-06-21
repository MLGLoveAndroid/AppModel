# AppModel 是一个组件化Demo 里面包含 ButterKnife，ARouter页面跳转管理 MVP,Retrofit,Rxjava,EventBus等

其中App里面是项目的管理主包管理各个Model

组件化也就是将model单独运行，其中管理这个的
这个布尔值为false时App是一个整体，为true是model可以单独运行，这样方便测试。
在各个model.gradle中配置
如果是单独运行的话是application，如果是整体就是library

if (isModule.toBoolean()) {
    apply plugin: 'com.android.application'
} else {
    apply plugin: 'com.android.library'
    apply plugin: 'com.jakewharton.butterknife'
}

isModule = false 统一管理这个是在  gradle.properties中配置

首先介绍一下ARouter
是阿里的路由页面跳转管理
简单看一下

注册：

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

使用：

    @OnClick({R.id.buta, R.id.butb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buta:
                    ARouter.getInstance().build(ONEACTIVITY).navigation();
                break;
            case R.id.butb:
                ARouter.getInstance().build(TWOACTIVITY).navigation();
                break;
            case R.id.butc:
                break;
        }
    }
}


@Route(path = "/one/activity")
public class OneActivity extends BaseMvpActivity<oneView, ModelApresenter> implements oneView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);
    }
}

ARouter这个依赖：
     compile 'com.alibaba:arouter-api:1.3.1'
     annotationProcessor 'com.alibaba:arouter-compiler:1.1.4'
跳那里就依赖那里。
在model。gradle中配置这个：
  defaultConfig {
        minSdkVersion 15
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [moduleName: project.getName()]
            }
        }
    }

组件化，还有一点，因为需要的是model单独运行，所以需要多建立一个AndroidManifest.xml
其中有application是model单独运行时的 application的 AndroidManifest.xml，没有的则是为 library的 AndroidManifest.xml
 <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:name="debug.TwoApp"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".View.TwoActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
    
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.modelb">

    <application android:theme="@style/AppTheme">
        <activity
            android:name=".View.TwoActivity"
            android:screenOrientation="portrait"></activity>
        <activity android:name=".View.ImageActivity"></activity>
    </application>

</manifest>


这样话不出意外就可以了！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！

ButterKnife
依赖：
compile 'com.jakewharton:butterknife:8.8.1'
 annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
黄油刀，依赖注解找ID
在主model中的可以直接用的，在library中可不行，需要配置：

在library的gradle配置：
if (isModule.toBoolean()) {
    apply plugin: 'com.android.application'
} else {
        apply plugin: 'com.android.library'
    apply plugin: 'com.jakewharton.butterknife'
}
在AppModel的gradle配置：
   dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'
        classpath 'com.jakewharton:butterknife-gradle-plugin:8.4.0'
    }
    尽量使用8.4.0，别的可能有问题
    最主要的是在 在library中使用的是R2.
    
    @BindView(R2.id.mvp_loading)
    ProgressBar mvp_loading;
    @BindView(R2.id.mvp_listview)
    ListView mvp_listview;
    private FunnyBean myBean;
    
    算是一个组件化的小问题！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
    
    Retrofit,Rxjava,EventBus,这几个很常用简单说说：
    
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

统一管理接口数据：
一个简单没用封装的Rerofit
public class ApiClientUtils {
    public static Retrofit retrofit(String url){
        Retrofit mRetrofit;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        
        return mRetrofit;
    }
}


EventBus:
依赖：
  compile 'org.greenrobot:eventbus:3.0.0'
非常好用的数据传输库：

简单使用：
  传输使用，如果页面没用被创建
  EventBus.getDefault().postSticky(new EnvetImage(imageurl));
  传输使用，如果页面被创建
  EventBus.getDefault().post(new EnvetImage(imageurl));
接受也是一样的：
先注册：
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
            EventBus.getDefault().register(this);
    }
    解除：
 @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(ImageActivity.this);
    }
接受数据是这样的：
sticky = true 粘性事件：页面是否被创建

    @Subscribe(threadMode = ThreadMode.MAIN ,sticky = true )
    public void onEnvetImage(EnvetImage event) {
        Toast.makeText(ImageActivity.this, event.getImage(), Toast.LENGTH_SHORT).show();
        Glide.with(ImageActivity.this).load(event.getImage()).into(photoview);
    }








