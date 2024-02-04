package cn.tongdun.mobrisk.core.tools

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {
    const val TYPE_UNKNOWN = 0
    const val TYPE_MOBILE = 1
    const val TYPE_WIFI = 2
    const val TYPE_VPN = 3
    const val TYPE_ETHERNET = 4

    fun contain(context: Context?,type: Int):Boolean {
        return getNetworkTypes(context).contains(type)
    }

    @SuppressLint("MissingPermission")
    fun getNetworkTypes(context: Context?): List<Int> {

        val list = ArrayList<Int>()
        if (null == context) {
            return list
        }
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        try {
            val networks = cm.allNetworks
            if (networks.isEmpty()) {
                return list
            }

            for (network in networks) {
                var networkInfo = cm.getNetworkInfo(network)
                list.add(when(networkInfo?.type) {
                    ConnectivityManager.TYPE_MOBILE -> TYPE_MOBILE
                    ConnectivityManager.TYPE_WIFI -> TYPE_WIFI
                    ConnectivityManager.TYPE_ETHERNET -> TYPE_ETHERNET
                    ConnectivityManager.TYPE_VPN -> TYPE_VPN
                    else -> {
                        TYPE_UNKNOWN
                    }
                })

            }

        } catch (ignored: Throwable) {
        }
        return list
    }
}