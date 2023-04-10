package cn.tongdun.mobrisk.core.collectors;

import cn.tongdun.mobrisk.core.utils.JNIHelper;
import cn.tongdun.mobrisk.core.utils.Constants;

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
        int ret = (int) JNIHelper.callNative(Constants.JniCode.DEBUG, null);
        result |= ret;
        return result;
    }
}
