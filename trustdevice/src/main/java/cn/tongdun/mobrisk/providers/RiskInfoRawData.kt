package cn.tongdun.mobrisk.providers

import cn.tongdun.mobrisk.core.tools.Constants

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
    val vpn: Boolean,
    val deviceInfoTampered: Boolean
) {

    override fun toString(): String {
        val risks = arrayOf(
            root.takeIf { it }?.let { Constants.KEY_ROOT },
            debug.takeIf { it }?.let { Constants.KEY_DEBUG },
            multiple.takeIf { it }?.let { Constants.KEY_MULTIPLE },
            xposed.takeIf { it }?.let { Constants.KEY_XPOSED },
            magisk.takeIf { it }?.let { Constants.KEY_MAGISK },
            hook.takeIf { it }?.let { Constants.KEY_HOOK },
            emulator.takeIf { it }?.let { Constants.KEY_EMULATOR },
            vpn.takeIf { it }?.let { Constants.KEY_VPN },
            deviceInfoTampered.takeIf { it }?.let { Constants.KEY_DEVICE_INFO_TAMPERED }
        )
        return risks.filterNotNull().joinToString()
    }
}