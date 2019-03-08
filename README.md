## 供大家学习的一个保活库 ,总结牛人的多种保活方案 ,实测开启无声的音频方案最有效果 ,app被杀拉活无望 !!!!! 
### 用户手动设置（测试用户手动打开这三权限，app会被拉活）
* 跳转到自动启界面，允许自启动
* 忽略电池优化
* 通知栏权限
### 注意android系统8.0+ 后台不允许startService() ,需要用startForegroundService() https://blog.csdn.net/kdsde/article/details/82143866
    * 通知栏必须加 channel https://blog.csdn.net/guolin_blog/article/details/79854070



## 双进程讣告
### 1. https://github.com/xingda920813/HelloDaemon/blob/master
### 2. 继承 AbsWorkService, 实现 6 个抽象方法

```
/**
 * 是否 任务完成, 不再需要服务运行?
 * @return 应当停止服务, true; 应当启动服务, false; 无法判断, null.
 */
Boolean shouldStopService();

/**
 * 任务是否正在运行?
 * @return 任务正在运行, true; 任务当前不在运行, false; 无法判断, null.
 */
Boolean isWorkRunning();

void startWork();

void stopWork();

//Service.onBind(Intent intent)
@Nullable IBinder onBind(Intent intent, Void unused);

//服务被杀时调用, 可以在这里面保存数据.
void onServiceKilled();
```

别忘了在 Manifest 中注册这个 Service.

### 3. 自定义 Application

在 Application 的 `onCreate()` 中, 调用

```
DaemonEnv.initialize(
  Context app,  //Application Context.
  Class<? extends AbsWorkService> serviceClass, //刚才创建的 Service 对应的 Class 对象.
  @Nullable Integer wakeUpInterval);  //定时唤醒的时间间隔(ms), 默认 6 分钟.

Context.startService(new Intent(Context app, Class<? extends AbsWorkService> serviceClass));
```

别忘了在 Manifest 中通过 android:name 使用这个自定义的 Application.

### 4. API 说明

#### 启动 Service:

```
Context.startService(new Intent(Context c, Class<? extends AbsWorkService> serviceClass))
```

#### 停止 Service:

在 ? extends AbsWorkService 中, 添加 `stopService()` 方法:

1.操作自己维护的 flag, 使 `shouldStopService()` 返回 `true`;

2.调用自己的方法或第三方 SDK 提供的 API, 停止任务;

3.调用 ```AbsWorkService.cancelJobAlarmSub()``` 取消 Job / Alarm / Subscription.

需要停止服务时, 调用 ? extends AbsWorkService 上的 `stopService()` 即可.

#### 处理白名单:

以下 API 全部位于 IntentWrapper 中:  

```
List<IntentWrapper> getIntentWrapperList();

//弹出 android.support.v7.AlertDialog, 引导用户将 App 加入白名单.
void whiteListMatters(Activity a, String reason);

//防止华为机型未加入白名单时按返回键回到桌面再锁屏后几秒钟进程被杀.
//重写 MainActivity.onBackPressed(), 只保留对以下 API 的调用.
void onBackPressed(Activity a);
```

#### 为节省用户的电量, 当不再需要服务运行时, 可以调用 ```AbsWorkService.cancelJobAlarmSub()``` 取消定时唤醒的 Job / Alarm / Subscription, 并调用 `stopService()` 停止服务.

详见代码及注释。

## 帐号同步
* SyncAdapterManager.init(this);
## 无声音频
* startService( new Intent(getApplicationContext(),AbnormalService.class);
## 百度全家桶
## 一个像素惨案
## 通知栏监听服务
## 华为小米推送
