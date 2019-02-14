package com.baidu.android.pushservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.keeplive.zzwdaemon.DaemonEnv;

/**
 * Created by Administrator on 2019/1/29.
 */

public class RegistrationReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (!DaemonEnv.sInitialized) return;
		DaemonEnv.startServiceMayBind(DaemonEnv.sServiceClass);
	}
}
