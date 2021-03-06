package cn.com.stableloan;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.multidex.MultiDex;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.meituan.android.walle.WalleChannelReader;
import com.qiniu.android.storage.UploadManager;
import com.rong360.app.crawler.CrawlerManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import cn.com.stableloan.utils.SPUtils;
import cn.com.stableloan.view.update.AppUpdateUtils;


/**
 * Created by apple on 2017/5/20.
 */

public class AppApplication extends Application {
    //dao


    private static AppApplication instance;

    public static SharedPreferences sp;

    public static UploadManager uploadManager;

    public static UploadManager getUploadManager(){
        return uploadManager;
    }

    /**
     * 启动照相Intent的RequestCode.自定义相机.
     */
    public static final int TAKE_PHOTO_CUSTOM = 100;
    /**
     * 启动照相Intent的RequestCode.系统相机.
     */
    public static final int TAKE_PHOTO_SYSTEM = 200;
    /**
     * 主线程Handler.
     */
    public static Handler mHandler;
    public static AppApplication sApp;
    String channel="test";
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this) ;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;

        mHandler = new Handler();

        initTypeface();
        OkGo.init(this);
        uploadManager=new UploadManager();
        instance = this;

        channel = WalleChannelReader.getChannel(this.getApplicationContext());

        String versionName = AppUpdateUtils.getVersionName(this);

        HttpHeaders headers = new HttpHeaders();
        headers.put("channel", channel);
        headers.put("os", versionName);
        OkGo.init(this);
        try {
            OkGo.getInstance()
                    .setCertificates()
                    .debug("OkGo", Level.INFO, true)
                    .setCacheMode(CacheMode.NO_CACHE)
                    //公共header不支持中文
                    .addCommonHeaders(headers);

        } catch (Exception e) {
            e.printStackTrace();
        }
        CrashReport.initCrashReport(getApplicationContext(), "e0e8b8baa1", false);

        //友盟
        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this,"5a14ef858f4a9d5bd300006c"
        ,channel));

        //只能被本应用访问
        sp = super.getSharedPreferences("eSetting", Context.MODE_PRIVATE);

        CrawlerManager.initSDK(this);

    }


    private void initTypeface(){
        try {
            Field field = Typeface.class.getDeclaredField("SERIF");
            field.setAccessible(true);
            field.set(null, Typeface.createFromAsset(getAssets(), "fonts/pingfang.ttf"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static AppApplication getApp(){
        return instance;
    }

    public static String getToken(){

        return (String) SPUtils.get(instance, "token", "1");

    }


    private static List<Activity> myActivity = new ArrayList<>();
    private static Map<String,Activity> destoryMap = new HashMap<>();
    public void addToList(Activity activity){
        myActivity.add(activity);
    }

    public static void addDestoryActivity(Activity activity,String activityName) {
        destoryMap.put(activityName,activity);
    }

    /**
     *销毁指定Activity
     */

    public static void destoryActivity(String activityName) {
        Set<String> keySet=destoryMap.keySet();
        for (String key:keySet){
            if(destoryMap.get(key)!=null){
                destoryMap.get(key).finish();
            }
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (Activity activity : myActivity){
            activity.finish();
        }
    }


}

