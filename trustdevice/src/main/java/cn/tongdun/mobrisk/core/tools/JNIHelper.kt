package cn.tongdun.mobrisk.core.tools

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/12
 */
object JNIHelper {
    external fun detectDebug(): Int
    external fun detectHook(): String?
}