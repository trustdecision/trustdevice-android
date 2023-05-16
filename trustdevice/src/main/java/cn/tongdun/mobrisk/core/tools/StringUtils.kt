package cn.tongdun.mobrisk.core.tools

/**
 * @description:
 * @author: wuzuchang
 * @date: 2023/5/15
 */


object StringUtils {

    fun splitNonRegex(input: String, delim: String?): List<String> {
        var inputString = input
        val list: MutableList<String> = ArrayList()
        if (inputString.isEmpty()) {
            return list
        }
        if (delim == null || delim.isEmpty()) {
            list.add(inputString)
            return list
        }
        if (inputString == delim) {
            return list
        }
        while (true) {
            val index: Int = inputString.indexOf(delim)
            if (index == -1) {
                if (inputString.isNotEmpty()) {
                    list.add(inputString)
                }
                return list
            }
            if (index != 0) {
                list.add(inputString.substring(0, index))
            }
            inputString = inputString.substring(index + delim.length)
        }
    }

    fun byteToHexString(data: ByteArray): String {
        val hex = StringBuilder()
        // Iterating through each byte in the array
        for (i in data) {
            hex.append(String.format("%02X", i))
        }
        return hex.toString()
    }
}