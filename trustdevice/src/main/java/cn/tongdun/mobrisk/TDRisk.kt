package cn.tongdun.mobrisk

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import cn.tongdun.mobrisk.core.FMCore
import org.json.JSONObject


/**
 * @description: fingerprint entry class
 * @author: wuzuchang
 * @date: 2023/5/12
 */
object TDRisk {

    @JvmStatic
    fun init(context: Context) {
        val builder = Builder()
        initWithOptions(context, builder)
    }

    @JvmStatic
    fun initWithOptions(context: Context, builder: Builder) {
        FMCore.instance.init(context,builder.build())
    }

    @JvmStatic
    fun getBlackbox(): JSONObject {
        return FMCore.instance.getDeviceInfo()
    }

    class Builder {
        private var partnerCode: String? = null
        private var callback: TDRiskCallback? = null
        fun partner(partner: String): Builder {
            partnerCode = partner
            return this
        }

        fun callback(TDRiskCallback: TDRiskCallback): Builder {
            callback = TDRiskCallback
            return this
        }

        fun build(): TDRiskOption {
            val options = TDRiskOption()
            options.partnerCode = partnerCode
            options.callback = callback
            return options
        }
    }

}