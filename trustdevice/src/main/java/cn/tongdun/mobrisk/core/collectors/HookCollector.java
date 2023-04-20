package cn.tongdun.mobrisk.core.collectors;

import cn.tongdun.mobrisk.core.utils.JNIHelper;

/**
 * @description: detect hook
 * @author: wuzuchang
 * @date: 2023/4/19
 */
public class HookCollector {

    public HookCollector() {
    }

    public String detectHook() {
        return JNIHelper.detectHook();
    }
}
