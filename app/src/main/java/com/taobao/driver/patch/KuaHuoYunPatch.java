package com.taobao.driver.patch;

import com.taobao.android.dexposed.DexposedBridge;
import com.taobao.android.dexposed.XC_MethodHook;
import com.taobao.patch.IPatch;
import com.taobao.patch.PatchParam;

/**
 * Created by lining on 15/8/14.
 */
public class KuaHuoYunPatch implements IPatch {

    @Override
    public void handlePatch(final PatchParam arg0) throws Throwable {
        Class<?> cls = null;
        try {
            cls = arg0.context.getClassLoader().loadClass("com.kuaihuoyun.android.user.service.MQTTConfig");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Target class, method with parameter types, followed by the hook callback (XC_MethodHook).
        DexposedBridge.findAndHookMethod(cls, "getLinkType", new XC_MethodHook() {

            // To be invoked before Activity.onCreate().
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                // 推送方案使用原先的MQTT一套
                param.setResult(0);
            }

            // To be invoked after Activity.onCreate()
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {

            }
        });

    }

}
