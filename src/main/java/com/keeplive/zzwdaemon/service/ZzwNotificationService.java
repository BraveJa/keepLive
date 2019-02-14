package com.keeplive.zzwdaemon.service;

import android.annotation.TargetApi;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class ZzwNotificationService extends NotificationListenerService {

	public ZzwNotificationService() {
		Log.i("ZzwNotificationService", "ZzwNotificationService: ");
	}

	@Override
	public void onNotificationPosted(StatusBarNotification sbn) {
		Log.i("ZzwNotificationService", "onNotificationPosted: ");
	}

	@Override
	public void onNotificationRemoved(StatusBarNotification sbn) {
		Log.i("ZzwNotificationService", "onNotificationRemoved: ");
	}

}
