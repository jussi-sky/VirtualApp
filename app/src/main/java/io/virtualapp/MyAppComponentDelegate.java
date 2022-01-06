package io.virtualapp;

import android.app.Application;
import android.content.Context;
import com.lody.virtual.client.VClient;
import com.lody.virtual.helper.utils.VLog;
import com.scorpion.IHook.XposedBridge;
import com.scorpion.IHook.callbacks.XC_LoadPackage;
import com.scorpion.reverse.dingding.DingTalk;
import com.scorpion.splash.AppComponentDelegate;
import com.swift.sandhook.xposedcompat.utils.ProcessUtils;

public class MyAppComponentDelegate extends AppComponentDelegate {

    private static final String TAG = "AppComponentDelegate";
    boolean isMainProcess = false;
    public Application mApplication;
    Context mContext;

    public MyAppComponentDelegate(Context context) {
        super(context);
        this.mContext = context;
    }

    public boolean isMainProcess() {
        return this.isMainProcess;
    }

    public void setMainProcess(boolean mainProcess) {
        this.isMainProcess = mainProcess;
        boolean z = this.isMainProcess;
    }

    @Override
    public void beforeStartApplication(String packageName, String processName, Context context) {

    }

    @Override
    public void beforeApplicationCreate(String packageName, String processName, Application application) {

    }

    @Override
    public void afterApplicationCreate(String packageName, String processName, Application application) {

        if (packageName.equals("com.alibaba.android.rimet")) {
            VLog.d("VA-", "开始 hook 丁丁打卡", new Object[0]);
            DingTalk.hook(VClient.get().getClassLoader());
        }

        if(!packageName.equals("com.ford.cnevapp.debug")){
            FridaGadget.init();
            return;
        }

        if (processName.equals("com.ford.cnevapp.debug")) {
            FridaGadget.init();
        }

    }

    private XC_LoadPackage.LoadPackageParam getLoadPackageParam(Application application) {
        XC_LoadPackage.LoadPackageParam packageParam = new XC_LoadPackage.LoadPackageParam(XposedBridge.sLoadedPackageCallbacks);
        if (application != null) {
            if (packageParam.packageName == null) {
                packageParam.packageName = application.getPackageName();
            }
            if (packageParam.processName == null) {
                packageParam.processName = ProcessUtils.getProcessName(application);
            }
            if (packageParam.classLoader == null) {
                packageParam.classLoader = application.getClassLoader();
            }
            if (packageParam.appInfo == null) {
                packageParam.appInfo = application.getApplicationInfo();
            }
        }
        return packageParam;
    }
}
