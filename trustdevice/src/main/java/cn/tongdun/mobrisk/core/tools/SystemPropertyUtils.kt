package cn.tongdun.mobrisk.core.tools

import android.annotation.SuppressLint


/**
 * @description:get System Property
 * @author: wuzuchang
 * @date: 2023/5/15
 */
@SuppressLint("PrivateApi")
object SystemPropertyUtils {
    fun getProperty(propertyName: String?): String = executeSafe({
        val systemPropertyClass = Class.forName("android.os.SystemProperties")
        systemPropertyClass.getMethod("get", String::class.java)
            .invoke(systemPropertyClass, propertyName) as String
    }, "")
}