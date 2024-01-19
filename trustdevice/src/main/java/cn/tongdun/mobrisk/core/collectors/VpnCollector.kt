package cn.tongdun.mobrisk.core.collectors

import android.content.Context
import cn.tongdun.mobrisk.core.tools.NetworkUtils


interface VpnInterface {
    fun detectVpn(context:Context?): Boolean
}

class VpnCollector : VpnInterface {
    override fun detectVpn(context:Context?): Boolean  {
        return NetworkUtils.contain(context,NetworkUtils.TYPE_VPN)
    }

}