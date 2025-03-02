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
        val xposedClasses = arrayOf(
            "de.robv.android.xposed.XposedBridge",
            "de.robv.android.xposed.IXposedHookLoadPackage",
            "de.robv.android.xposed.DexposedBridge"
        )
        for (className in xposedClasses) {
            try {
                Class.forName(className, true, ClassLoader.getSystemClassLoader())
                return true
            } catch (ignore: Exception) {
            }
        }
        return false
    }
}