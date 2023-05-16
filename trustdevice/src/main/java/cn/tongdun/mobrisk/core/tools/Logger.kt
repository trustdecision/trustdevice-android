package cn.tongdun.mobrisk.core.tools

import android.util.Log

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/12
 */
object Logger {
    private const val LOG_TAG = "TrustDevice"

    fun d(message: String) {
        Log.d(LOG_TAG, message)
    }

    fun e(message: String) {
        Log.e(LOG_TAG, message)
    }
}