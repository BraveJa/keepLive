package com.keeplive.zzwdaemon.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.keeplive.zzwdaemon.DaemonEnv;
import com.keeplive.zzwdaemon.ScreenManager;
import com.keeplive.zzwdaemon.SystemUtils;
import com.zzwtec.phone.BuildConfig;

/**1像素Activity
 *
 * Created by jianddongguo on 2017/7/8.
 */

public class SinglePixelActivity extends AppCompatActivity {
    private static final String TAG = "SinglePixelActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(BuildConfig.DEBUG)
            Log.d(TAG,"onCreate--->启动1像素保活");
        Window mWindow = getWindow();
        mWindow.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams attrParams = mWindow.getAttributes();
        attrParams.x = 0;
        attrParams.y = 0;
        attrParams.height = 300;
        attrParams.width = 300;
        mWindow.setAttributes(attrParams);
        // 绑定SinglePixelActivity到ScreenManager
        ScreenManager.getScreenManagerInstance(this).setSingleActivity(this);
    }

    @Override
    protected void onDestroy() {
        if(BuildConfig.DEBUG)
            Log.d(TAG,"onDestroy--->1像素保活被终止");
        if(! SystemUtils.isAPPALive(this)){
            if (!DaemonEnv.sInitialized) return;
            DaemonEnv.startServiceMayBind(DaemonEnv.sServiceClass);
        }
        super.onDestroy();
    }
}
