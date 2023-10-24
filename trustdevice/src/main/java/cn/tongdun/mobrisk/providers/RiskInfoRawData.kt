package cn.tongdun.mobrisk.providers

import android.text.TextUtils
import cn.tongdun.mobrisk.core.tools.Logger

/**
 * @description:RiskInfoRawData
 * @author: wuzuchang
 * @date: 2023/5/16
 */
data class RiskInfoRawData(
    val root: Boolean,
    val debug: Boolean,
    val multiple: Boolean,
    val xposed: Boolean,
    val magisk: Boolean,
    val hook: Boolean,
    val emulator: Boolean,
) {

    override fun toString(): String {
        val risks = arrayOf(
            root.takeIf { it }?.let { "root" },
            debug.takeIf { it }?.let { "debug" },
            multiple.takeIf { it }?.let { "multiple" },
            xposed.takeIf { it }?.let { "xposed" },
            magisk.takeIf { it }?.let { "magisk" },
            hook.takeIf { it }?.let { "hook" },
            emulator.takeIf { it }?.let { "emulator" }
        )
        return risks.filterNotNull().joinToString()
    }
}