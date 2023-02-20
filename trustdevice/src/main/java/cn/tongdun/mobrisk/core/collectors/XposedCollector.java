package cn.tongdun.mobrisk.core.collectors;


/**
 * @author collam
 * @date 2023/2/20
 */
public class XposedCollector {
    public boolean findXposed() {
        try {
            Class.forName("de.robv.android.xposed.XposedBridge",true,ClassLoader.getSystemClassLoader());
            return true;
        }
        catch (Exception ignore){
        }
        return false;
    }
}
