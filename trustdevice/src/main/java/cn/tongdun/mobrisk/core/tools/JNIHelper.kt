package cn.tongdun.mobrisk.core.tools

import androidx.annotation.Keep

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/12
 */
@Keep
object JNIHelper {
    //detect debug
    external fun call0(): Int
    //detect hook
    external fun call1(): String
    //get property
    external fun call2(key: String, defaultValue: String): String
}