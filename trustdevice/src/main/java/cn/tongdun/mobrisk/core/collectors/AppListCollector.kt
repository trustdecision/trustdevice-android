package cn.tongdun.mobrisk.core.collectors

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.text.TextUtils

/**
 * @description: App List
 * @author: wuzuchang
 * @date: 2023/5/12
 */

interface AppListDataInterface {
    fun getAppList(): String
    fun getSystemAppList(): String
}

@SuppressLint("QueryPermissionsNeeded")
class AppListCollector(packageManager: PackageManager) : AppListDataInterface {
    private val appList: MutableList<String?> = ArrayList()
    private val systemAppList: MutableList<String?> = ArrayList()

    init {
        packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES)
            .filter {
                isSystemApp(it)
            }.map {
                appList.add(it.packageName)
            }
        packageManager.getInstalledPackages(PackageManager.GET_META_DATA).fold(
            Pair(systemAppList, appList)
        ) { (systemList, appList), packageInfo ->
            if (isSystemApp(packageInfo)) {
                systemList.add(packageInfo.packageName)
            } else {
                appList.add(packageInfo.packageName)
            }
            Pair(systemList, appList)
        }

    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun getAppList(): String {
        return TextUtils.join(",", appList)
    }

    override fun getSystemAppList(): String {
        return TextUtils.join(",", systemAppList)
    }

    private fun isSystemApp(packageInfo: PackageInfo): Boolean {
        val isSysApp =
            packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == ApplicationInfo.FLAG_SYSTEM
        val isSysUpd =
            packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP == ApplicationInfo.FLAG_UPDATED_SYSTEM_APP
        return isSysApp || isSysUpd
    }

}