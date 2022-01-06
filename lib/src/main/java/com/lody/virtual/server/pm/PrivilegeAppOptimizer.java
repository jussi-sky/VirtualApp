package com.lody.virtual.server.pm;

import android.content.Intent;

import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.server.am.VActivityManagerService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Lody
 */

public class PrivilegeAppOptimizer {

    private static final PrivilegeAppOptimizer sInstance = new PrivilegeAppOptimizer();
    private final List<String> privilegeApps = new ArrayList<>();

    private PrivilegeAppOptimizer() {
        Collections.addAll(privilegeApps, StubManifest.PRIVILEGE_APPS);
    }

    public static PrivilegeAppOptimizer get() {
        return sInstance;
    }

    public List<String> getPrivilegeApps() {
        return Collections.unmodifiableList(privilegeApps);
    }

    public void addPrivilegeApp(String packageName) {
        privilegeApps.add(packageName);
    }

    public void removePrivilegeApp(String packageName) {
        privilegeApps.remove(packageName);
    }

    public boolean isPrivilegeApp(String packageName) {
        return privilegeApps.contains(packageName);
    }

    public void performOptimizeAllApps() {
        for (String pkg : privilegeApps) {
            performOptimize(pkg, VUserHandle.USER_ALL);
        }
    }

    public boolean performOptimize(String packageName, int userId) {
        if (!isPrivilegeApp(packageName)) {
            return false;
        }
        VActivityManagerService.get().sendBroadcastAsUserWithPackage(
                new Intent(Intent.ACTION_BOOT_COMPLETED, null), new VUserHandle(userId), packageName);
        return true;
    }

}
