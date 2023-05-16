package cn.tongdun.android.beans

import android.graphics.drawable.Drawable


data class AppItemData(
    val icon: Drawable?,
    val appName: String,
    val packageName: String,
    val versionName: String
) {
}
