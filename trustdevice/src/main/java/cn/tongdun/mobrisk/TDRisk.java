package cn.tongdun.mobrisk;

import android.content.Context;

import org.json.JSONObject;

import cn.tongdun.mobrisk.core.FMCore;

/**
 * @description: fingerprint entry class
 * @author: wuzuchang
 * @date: 2022/12/5
 */
public class TDRisk {

    public static void init(final Context context) {
        initWithOptions(context, null);
    }

    public static void initWithOptions(final Context context, Builder builder) {
        if (context == null) {
            return;
        }
        FMCore.getInstance().init(context, builder == null ? null : builder.build());
    }

    public static JSONObject getBlackbox() {

        return FMCore.getInstance().getDeviceInfo();
    }

    public static class Builder {

        private String partnerCode;
        private TDRiskCallback callback;

        public Builder partner(String partner) {
            partnerCode = partner;
            return this;
        }

        public Builder callback(TDRiskCallback TDRiskCallback) {
            callback = TDRiskCallback;
            return this;
        }

        public TDRiskOption build() {
            TDRiskOption options = new TDRiskOption();
            options.setPartnerCode(partnerCode);
            options.setCallback(callback);
            return options;
        }
    }
}
