package io.virtualapp;

import com.lody.virtual.helper.utils.VLog;

public class FridaGadget {
    private static final String TAG = FridaGadget.class.getSimpleName();

    static {
        try {
            System.loadLibrary("ijkplayer");
        } catch (Throwable e) {
            VLog.e(TAG, VLog.getStackTraceString(e));
        }
    }

    public static void init() {
        VLog.d(TAG, "Init Frida Gadget", new Object[0]);
    }
}
