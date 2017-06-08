package cn.com.stableloan.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.stableloan.R;
import cn.com.stableloan.api.Urls;
import cn.com.stableloan.base.BaseActivity;
import cn.com.stableloan.bean.CodeMessage;
import cn.com.stableloan.bean.UserBean;
import cn.com.stableloan.model.UpdateInfoBean;
import cn.com.stableloan.utils.CaptchaTimeCount;
import cn.com.stableloan.utils.Constants;
import cn.com.stableloan.utils.EncryptUtils;
import cn.com.stableloan.utils.LogUtils;
import cn.com.stableloan.utils.RegexUtils;
import cn.com.stableloan.utils.SPUtils;
import cn.com.stableloan.utils.TinyDB;
import cn.com.stableloan.utils.ToastUtils;
import cxy.com.validate.IValidateResult;
import cxy.com.validate.Validate;
import cxy.com.validate.ValidateAnimation;
import cxy.com.validate.annotation.Index;
import cxy.com.validate.annotation.NotNull;
import cxy.com.validate.annotation.RE;
import ezy.boost.update.IUpdateAgent;
import ezy.boost.update.IUpdateParser;
import ezy.boost.update.IUpdatePrompter;
import ezy.boost.update.UpdateInfo;
import ezy.boost.update.UpdateManager;
import ezy.boost.update.UpdateUtil;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 登录注册页
 */

public class LoginActivity extends BaseActivity implements IValidateResult {
    /* @Bind(R.id.nts)
     NavigationTabStrip nts;*/

    @Bind(R.id.nts)
    TabLayout nts;

    @Index(1)
    @NotNull(msg = "手机号不能为空！")
    @RE(re = RE.phone, msg = "手机号格式不正确")
    @Bind(R.id.et_phone)
    EditText etPhone;



    @Index(2)
    @NotNull(msg = "不能为空！")
    @Bind(R.id.et_lock)
    EditText etLock;

    @Bind(R.id.bt_getCode)
    Button btGetCode;
    @Bind(R.id.login_button)
    Button loginButton;
    @Bind(R.id.register_button)
    Button registerButton;
    @Bind(R.id.tv_forget)
    TextView tvForget;

    private String phone;

    private String code;

    private boolean Flag=false;

    private CaptchaTimeCount captchaTimeCount;

    private String MessageCode = null;


    public static void launch(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }
    private CodeMessage message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        Validate.reg(this);

        captchaTimeCount = new CaptchaTimeCount(Constants.Times.MILLIS_IN_TOTAL, Constants.Times.COUNT_DOWN_INTERVAL, btGetCode, this);

    }

    private void initView() {

        etLock.addTextChangedListener(watcher);
        etPhone.addTextChangedListener(watcher1);

        nts.addTab(nts.newTab().setText("密码登陆"));
        nts.addTab(nts.newTab().setText("短信登陆"));
        nts.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getSwitch(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
       /* nts.setTitles("短信登陆","密码登陆");
        nts.setTabIndex(0, true);*/

        String url="http://www.shoujiweidai.com/update/versions.json";

        UpdateManager.create(this).setUrl(url).setNotifyId(998).setParser(new IUpdateParser() {
            @Override
            public UpdateInfo parse(String source) throws Exception {
                LogUtils.i("source",source);
                UpdateInfo info = new UpdateInfo();
                Gson gson=new Gson();
                UpdateInfoBean infoBean = gson.fromJson(source, UpdateInfoBean.class);
                info.hasUpdate = true;
                info.updateContent =infoBean.getUpdateContent();
                info.versionCode = infoBean.getVersionCode();
                info.versionName = infoBean.getVersionName();
                info.url = infoBean.getUrl();
                info.md5 = infoBean.getMd5();
                info.size = infoBean.getSize();
                info.isForce = false;
                info.isIgnorable = false;
                info.isAutoInstall=true;
                info.isSilent = false;
                return info;
            }
        }).setManual(true).check();

    }

    private void getSwitch(int position) {
            switch (position){
                case 0:
                    Flag=false;
                    etLock.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                    etPhone.setInputType(EditorInfo.TYPE_CLASS_PHONE);
                    etPhone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
                    etLock.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
                    btGetCode.setVisibility(View.GONE);
                    tvForget.setVisibility(View.VISIBLE);
                    etPhone.getText().clear();
                    etLock.getText().clear();
                    etPhone.setHint("手机号");
                    etLock.setHint("密码");
                    break;
                case 1:
                    Flag=true;
                    etPhone.setHint("请输入手机号码");
                    etLock.setHint("验证码");
                    etPhone.getText().clear();
                    etLock.getText().clear();
                    etPhone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});;
                    etLock.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});;
                    etLock.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                    etPhone.setInputType(EditorInfo.TYPE_CLASS_PHONE);
                    btGetCode.setVisibility(View.VISIBLE);
                    tvForget.setVisibility(View.GONE);
                    break;

            }

    }

    /**
     *密码
     */
    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
                    if(!etPhone.getText().toString().isEmpty()){
                        loginButton.setEnabled(true);
                        loginButton.setBackgroundResource(R.drawable.corner_btn);
                        loginButton.setTextColor(getResources().getColor(R.color.white));
                    }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!etPhone.getText().toString().isEmpty()&&!etLock.getText().toString().isEmpty()){
                loginButton.setEnabled(true);
            }else {
                loginButton.setEnabled(false);
                loginButton.setBackgroundResource(R.drawable.login_button_start);

            }
        }
    };
    /**
     * 手机号
     *
     */
    private TextWatcher watcher1 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            if(!etLock.getText().toString().isEmpty()){
                loginButton.setEnabled(true);
                loginButton.setBackgroundResource(R.drawable.login_button_up);

            }else {

            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!etPhone.getText().toString().isEmpty()&&!etLock.getText().toString().isEmpty()){
                loginButton.setEnabled(true);
                loginButton.setBackgroundResource(R.drawable.login_button_up);
                loginButton.setTextColor(getResources().getColor(R.color.white));
            }else {
                loginButton.setEnabled(false);
                loginButton.setBackgroundResource(R.drawable.login_button_start);
            }

        }
    };




    @OnClick({R.id.bt_getCode, R.id.login_button, R.id.register_button,R.id.tv_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_getCode:
                phone=etPhone.getText().toString();
                if(etPhone.getText().toString().isEmpty()){
                    ToastUtils.showToast(this,"请填写手机号码");
                }else if(RegexUtils.isMobileExact(etPhone.getText().toString())){
                    ToastUtils.showToast(this,"手机号格式");
                }else {
                    getCodeMessage();
                }
                break;
            case R.id.login_button:
                Validate.check(LoginActivity.this, LoginActivity.this);

                break;
            case R.id.tv_forget:
                ForgetWordActivity.launch(this);
                break;
            case R.id.register_button:
                UpdateUtil.clean(this);
                RegisterActivity.launch(this);
                 break;
        }
    }

    /**
     * 登陆
     *
     *
     */
    private void setLogin() {

            // 验证码登陆
        if(Flag){
            String tel = etPhone.getText().toString();
            String cd = etLock.getText().toString();
            if(!tel.isEmpty()&&phone.equals(tel)){
                if(message.getCheck()!=null){
                    if(!cd.isEmpty()&&cd.equals(message.getCheck())){
                        TinyDB tinyDB=new TinyDB(this);
                        UserBean userBean=new UserBean();
                        userBean.setNickname(phone);
                        tinyDB.putObject("user",userBean);
                        MainActivity.launch(this);
                        finish();
                    }else {
                        ToastUtils.showToast(this,"验证码错误");
                    }
                }else {
                    ToastUtils.showToast(this,"验证码不正确");
                }
            }else {
                ToastUtils.showToast(this,"手机号错误");
            }
        }else {
            //账号密码登陆

            //隐藏密码
            etLock.setTransformationMethod(PasswordTransformationMethod
                    .getInstance());
            String pass = etLock.getText().toString();
            if(!pass.isEmpty()){
                String md5ToString = EncryptUtils.encryptMD5ToString(pass);
                HashMap<String, String> params = new HashMap<>();
                params.put("userphone",etPhone.getText().toString());
                params.put("password",md5ToString);
                params.put("Status","0");
                JSONObject jsonObject = new JSONObject(params);
                OkGo.post(Urls.puk_URL+Urls.Login.LOGIN)
                        .tag(this)
                        .upJson(jsonObject.toString())
                        .execute( new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                LogUtils.i("注册",s);
                                if(s!=null){
                                    try {
                                        JSONObject object=new JSONObject(s);
                                        if("true".equals(object.getString("isSuccess"))){
                                            SPUtils.put(LoginActivity.this,"token",object.getString("token"));
                                            MainActivity.launch(LoginActivity.this);
                                            finish();
                                        }else {
                                            String string = object.getString("msg");
                                            ToastUtils.showToast(LoginActivity.this,string);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }else {
                                    ToastUtils.showToast(LoginActivity.this,"服务器异常,请稍后再试");

                                }
                            }
                        });
            }

        }

    }

    /**
     * 获取验证码
     *
     */
    private void getCodeMessage() {
        captchaTimeCount.start();

        OkGo.post(Urls.Login.SEND_MESSAGE)
                .tag(this)
                .params("userPhone",etPhone.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtils.i("login-code",s);
                        Gson gson=new Gson();
                        message = gson.fromJson(s, CodeMessage.class);
                        LogUtils.i(message.toString());
                        if(message.getStatus()==1){
                            ToastUtils.showToast(LoginActivity.this,message.getMsg());
                        }else {
                            ToastUtils.showToast(LoginActivity.this,message.getMsg());
                        }
                    }
                });


    }
    private long mLastBackTime = 0;
    @Override
    public void onBackPressed() {
        // finish while click back key 2 times during 1s.
        if ((System.currentTimeMillis() - mLastBackTime) < 1000) {
            finish();
        } else {
            mLastBackTime = System.currentTimeMillis();
            ToastUtils.showToast(this,"再按一次退出");
        }

    }

    @Override
    public void onValidateSuccess() {
        setLogin();

    }

    @Override
    public void onValidateError(String msg, EditText editText) {
        if (editText != null)
            editText.setFocusable(true);
        ToastUtils.showToast(this,msg);
    }

    @Override
    public Animation onValidateErrorAnno() {
        return ValidateAnimation.horizontalTranslate();
    }
}
