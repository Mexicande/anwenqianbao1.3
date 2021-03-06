package cn.com.stableloan.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.com.stableloan.R;

/**
 * Created by apple on 2017/5/25.
 */

public class Qr_Dialog extends Dialog {
    private Button yes;//确定按钮
    private Button no;//取消按钮
    private TextView titleTv;//消息标题文本
    private TextView messageTv;//消息提示文本
    private String titleStr;//从外界设置的title文本
    private String  base_image;//base串
    private ImageView qr_imagr;
    //确定文本和取消文本的显示内容
    private String yesStr, noStr;
 /*   private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
*/
  /*  *//**
     * 设置取消按钮的显示内容和监听
     *
     *//*
    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }

    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }*/

    public Qr_Dialog(Context context,String bas64) {
        super(context, R.style.DialogSlideAnimation);
        base_image=bas64;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code_dialog);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
       /* yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });*/
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
       /* //如果用户自定了title和message
        if (titleStr != null) {
            titleTv.setText(titleStr);
        }
        if (messageStr != null) {
            messageTv.setText(messageStr);
        }
        //如果设置按钮的文字
        if (yesStr != null) {
            yes.setText(yesStr);
        }
        if (noStr != null) {
            no.setText(noStr);
        }*/


    }

    /**
     * 初始化界面控件
     */
    private void initView() {
   /*    yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);
        titleTv = (TextView) findViewById(R.id.name);
        messageTv = (TextView) findViewById(R.id.description);*/
        qr_imagr= (ImageView) findViewById(R.id.qr_image);
        byte[] decode = Base64.decode(base_image,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        //save to image on sdcard
        qr_imagr.setImageBitmap(bitmap);
    }

    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
  /*  public void setTitle(String title) {
        titleStr = title;
    }

    *//**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     *//*
    public void setMessage(String message) {
        messageStr = message;
    }*/

   /* *//**
     * 设置确定按钮和取消被点击的接口
     *//*
    public interface onYesOnclickListener {
        public void onYesClick();
    }

    public interface onNoOnclickListener {
        public void onNoClick();
    }*/
}
