package cn.tongdun.mobrisk.core.collectors;

/**
 * @description: Debug Info
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class DebugInfoCollector {

    public boolean getDebug(){
       return android.os.Debug.isDebuggerConnected();
    }
}
