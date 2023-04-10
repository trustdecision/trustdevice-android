package cn.tongdun.mobrisk.core.collectors;

import cn.tongdun.mobrisk.core.utils.JNIHelper;

/**
 * @description: Debug Info
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class DebugInfoCollector {

    public int getDebug(){
        int result = 0;
        if (android.os.Debug.isDebuggerConnected()){
            result |= 0x1;
        }
        int ret = JNIHelper.detectDebug();
        result |= ret;
        return result;
    }
}
