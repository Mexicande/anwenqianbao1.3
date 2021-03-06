package cn.com.stableloan.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.content.FileProvider;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.BaseRequest;

import java.io.File;
import java.text.DecimalFormat;

import cn.com.stableloan.AppApplication;
import cn.com.stableloan.R;
import okhttp3.Call;

/**
 * 第三方H5-apk下载
 * Created by apple on 2017/11/5.
 */

public class DownAPKService extends Service {
    private final int NotificationID = 0x10000;
    private NotificationManager mNotificationManager = null;
    private NotificationCompat.Builder builder;
    int oldRate = 0;

    // 文件保存路径(如果有SD卡就保存SD卡,如果没有SD卡就保存到手机包名下的路径)
    private String apkDir = "";
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initAPKDir();// 创建保存路径
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 接收Intent传来的参数:
        String apkUrl = intent.getStringExtra("apk_url");
        if(apkUrl !=null){
            downFile(apkUrl, apkDir);
        }
        return START_REDELIVER_INTENT;
    }
    /**
     * 创建路径的时候一定要用[/],不能使用[\],但是创建文件夹加文件的时候可以使用[\].
     * [/]符号是Linux系统路径分隔符,而[\]是windows系统路径分隔符 Android内核是Linux.
     */
    private void initAPKDir() {

        if (isHasSdcard())
            // 判断是否插入SD卡
        {
            apkDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Club/download/";
            // 保存到SD卡路径下
        }
        else{
            apkDir = getApplicationContext().getFilesDir().getAbsolutePath() + "/Club/download/";
            // 保存到app的包名路径下
        }
        File destDir = new File(apkDir);
        if (!destDir.exists()) {
            // 判断文件夹是否存在
            destDir.mkdirs();
        }
    }

    /**
     *
     * @Description:判断是否插入SD卡
     */
    private boolean isHasSdcard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    private void downFile(String fileUrl, String targetName) {

        OkGo.<File>get(fileUrl).execute(new com.lzy.okgo.callback.FileCallback(targetName,System.currentTimeMillis()+".apk") {
            @Override
            public void onSuccess(File file, Call call, okhttp3.Response response) {
                Intent installIntent = new Intent(Intent.ACTION_VIEW);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    installIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    LogUtils.d("content", AppApplication.getApp().getPackageName());
                    Uri fileUri = FileProvider.getUriForFile(DownAPKService.this,  AppApplication.getApp().getPackageName() + ".provider", file);
                    installIntent.setDataAndType(fileUri, "application/vnd.android.package-archive");
                } else {
                    Uri uri = Uri.fromFile(new File(file.getPath()));
                    installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
                }

                PendingIntent mPendingIntent = PendingIntent.getActivity(DownAPKService.this, 0, installIntent, 0);
                builder.setContentText("下载完成,请点击安装");
                builder.setContentIntent(mPendingIntent);
                builder .setChannelId(NotificationID+"");
                mNotificationManager.notify(NotificationID, builder.build());

                stopSelf();
                startActivity(installIntent);
                // 下载完成之后自动弹出安装界面
                mNotificationManager.cancel(NotificationID);
            }

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                System.out.println("开始下载文件");

                mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setSmallIcon(R.mipmap.iv_logo);
                builder.setTicker("正在下载新版本");
                builder.setContentTitle(getApplicationName());
                builder.setContentText("正在下载,请稍后...");
                builder.setNumber(0);
                builder.setAutoCancel(true);
                builder .setChannelId(NotificationID+"");

                mNotificationManager.notify(NotificationID, builder.build());
            }

            @Override
            public void onError(Call call, okhttp3.Response response, Exception e) {
                super.onError(call, response, e);
                System.out.println("文件下载失败");
                mNotificationManager.cancel(NotificationID);
                Toast.makeText(getApplicationContext(), "下载失败，请检查网络！", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                super.downloadProgress(currentSize, totalSize, progress, networkSpeed);
                int rate = Math.round(progress * 100);
                if(oldRate!=rate){
                    int x = Long.valueOf(currentSize).intValue();
                    int totalS = Long.valueOf(totalSize).intValue();
                    builder.setProgress(totalS, x, false);
                    builder.setContentInfo(getPercent(x, totalS));

                    mNotificationManager.notify(NotificationID, builder.build());
/*
                    System.out.println("文件下载中");
                    int totalS = Long.valueOf(totalSize).intValue();
                    builder.setProgress(totalS, rate, false);
                    builder.setContentInfo(getPercent(rate, totalS));
                    mNotificationManager.notify(NotificationID, builder.build());
*/

                    oldRate = rate;

                }



            }
        });


    }
    /**
     *
     * @param x
     *            当前值
     * @param total
     *            总值
     * [url=home.php?mod=space&uid=7300]@return[/url] 当前百分比
     * @Description:返回百分之值
     */
    private String getPercent(int x, int total) {
        String result = "";
        // 接受百分比的值
        double xDouble = x * 1.0;
        double tempresult = xDouble / total;
        // 百分比格式，后面不足2位的用0补齐 ##.00%
        DecimalFormat df1 = new DecimalFormat("0.00%");
        result = df1.format(tempresult);
        return result;
    }

    /**
     * @return
     * @Description:获取当前应用的名称
     */
    private String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }

        return (String) packageManager.getApplicationLabel(applicationInfo);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }
}
