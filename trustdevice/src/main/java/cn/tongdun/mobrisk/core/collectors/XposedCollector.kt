package cn.tongdun.mobrisk.core.collectors

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/15
 */
interface XposedInterface {
    fun findXposed(): Boolean
}

class XposedCollector : XposedInterface {
    override fun findXposed(): Boolean {
        try {
            Class.forName(
                "de.robv.android.xposed.XposedBridge",
                true,
                ClassLoader.getSystemClassLoader()
            )
            return true
        } catch (ignore: Exception) {
        }
        return false
    }
}