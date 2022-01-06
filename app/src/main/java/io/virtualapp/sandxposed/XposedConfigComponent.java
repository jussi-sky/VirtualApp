package io.virtualapp.sandxposed;


import com.scorpion.sandxposed.XposedConfig;
import com.scorpion.sandxposed.XposedModuleProfile;

public class XposedConfigComponent implements XposedConfig {
    @Override
    public boolean xposedEnabled() {
        return XposedModuleProfile.isXposedEnable();
    }

    @Override
    public void enableXposed(boolean enable) {
        XposedModuleProfile.enbaleXposed(enable);
    }

    @Override
    public boolean moduleEnable(String pkg) {
        return XposedModuleProfile.isModuleEnable(pkg);
    }

    @Override
    public void enableModule(String pkg, boolean enable) {
        XposedModuleProfile.enableModule(pkg, enable);
    }
}
