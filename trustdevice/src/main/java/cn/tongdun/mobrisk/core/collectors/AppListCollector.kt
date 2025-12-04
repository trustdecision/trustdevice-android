package cn.tongdun.mobrisk.core.collectors

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.text.TextUtils
import cn.tongdun.mobrisk.core.tools.executeSafe

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
class AppListCollector(context: Context) : AppListDataInterface {
    private val appList: MutableList<String?> = ArrayList()
    private val systemAppList: MutableList<String?> = ArrayList()

    init {
        if (hasQueryAllPackagesPermission(context)) {
            val packageManager = context.packageManager
            executeSafe {
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
        }
    }

    private fun hasQueryAllPackagesPermission(context: Context): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            return true
        }
        return executeSafe({
            val packageInfo = context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_PERMISSIONS
            )
            packageInfo.requestedPermissions?.contains(android.Manifest.permission.QUERY_ALL_PACKAGES) == true
        }, false)
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun getAppList(): String {
        return TextUtils.join(",", appList)
    }

    override fun getSystemAppList(): String {
        return TextUtils.join(",", systemAppList)
    }

    private fun isSystemApp(packageInfo: PackageInfo): Boolean {
        val appInfo = packageInfo.applicationInfo ?: return false
        val isSysApp = appInfo.flags and ApplicationInfo.FLAG_SYSTEM == ApplicationInfo.FLAG_SYSTEM
        val isSysUpd = appInfo.flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP == ApplicationInfo.FLAG_UPDATED_SYSTEM_APP
        return isSysApp || isSysUpd
    }

}