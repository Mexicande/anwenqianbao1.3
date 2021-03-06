package cn.com.stableloan.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;


import com.gyf.barlibrary.ImmersionBar;
import com.umeng.analytics.MobclickAgent;

import java.lang.ref.WeakReference;

import cn.com.stableloan.api.Urls;
import cn.com.stableloan.ui.activity.safe.FingerActivity;
import cn.com.stableloan.utils.SPUtils;
import cn.com.stableloan.utils.SharedPreferencesUtil;
import cn.com.stableloan.utils.fingerprint.FingerprintIdentify;


/**
 * 闪屏页
 *
 */
public class SplashActivity extends AppCompatActivity {

    private SwitchHandler mHandler = new SwitchHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!this.isTaskRoot()) {
            Intent intent = getIntent();
            if (intent != null) {
                String action = intent.getAction();
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                    finish();
                    return;
                }
            }
        }
        ImmersionBar.with(this).transparentBar()
                .init();
        setWelcome();
    }


    private static class SwitchHandler extends Handler {
        private FingerprintIdentify mFingerprintIdentify;

        private WeakReference<SplashActivity> mWeakReference;

        SwitchHandler(SplashActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            SplashActivity activity = mWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case 1:
                        String userphone = (String) SPUtils.get(activity, Urls.lock.USER_PHONE, "1");
                        String token = (String) SPUtils.get(activity, Urls.lock.TOKEN, "0");
                        if (token == null || "0".equals(token)) {
                            MainActivity.launch(activity);
                            activity.finish();
                        } else {
                            int loginCode = (int) SPUtils.get(activity, userphone + Urls.lock.LOGIN, 5);

                            switch (loginCode) {
                                case Urls.lock.PW_VERIFICATION:
                                    activity.startActivity(new Intent(activity, Verify_PasswordActivity.class).putExtra("from", "splash"));
                                    activity.finish();
                                    break;
                                case Urls.lock.GESTURE_VERIFICATION:
                                    activity.startActivity(new Intent(activity, GestureLoginActivity.class).putExtra("from", "splash"));
                                    activity.finish();
                                    break;
                                case Urls.lock.GESTURE_FINGER:
                                    mFingerprintIdentify = new FingerprintIdentify(activity);

                                    //硬件设备是否已录入指纹
                                    boolean registeredFingerprint = mFingerprintIdentify.isRegisteredFingerprint();
                                    //指纹功能是否可用
                                    boolean fingerprintEnable = mFingerprintIdentify.isFingerprintEnable();
                                    if (registeredFingerprint && fingerprintEnable) {
                                        Intent intent=new Intent(activity,FingerActivity.class);
                                        intent.putExtra("from","splash");
                                        activity.startActivity(intent);
                                        activity.finish();
                                    } else {
                                        activity.startActivity(new Intent(activity, Verify_PasswordActivity.class).putExtra("from", "splash"));
                                        activity.finish();
                                    }
                                    break;
                                default:
                                    MainActivity.launch(activity);
                                    activity.finish();
                                    break;
                            }
                        }
                        break;
                    default:
                        break;

                }
            }
        }
    }
    private void setWelcome( ){
        boolean isFirstOpen = SharedPreferencesUtil.getBoolean(this, SharedPreferencesUtil.FIRST_OPEN, true);
        // 如果是第一次启动，则先进入功能引导页
        if (isFirstOpen) {
            GuideActivity.launch(this);
            finish();
        }else {
            mHandler.sendEmptyMessageDelayed(1, 1000);
        }

    }
    /**
     * startActivity屏蔽物理返回按钮
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);

    }
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
