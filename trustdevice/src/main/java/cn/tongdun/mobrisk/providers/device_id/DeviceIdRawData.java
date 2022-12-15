package cn.tongdun.mobrisk.providers.device_id;

import android.text.TextUtils;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import cn.tongdun.mobrisk.providers.RawData;

/**
 * @description: DeviceIdRawData
 * @author: wuzuchang
 * @date: 2022/12/6
 */
@Deprecated(since = "pro no such class")
public class DeviceIdRawData extends RawData<String> {
    private String deviceId;
    private String androidId;
    private String gsfId;
    private String mediaDrmId;
    private String vbMetaDigest;

    public DeviceIdRawData(String deviceId, String android_id, String gsf_id, String mediaDrm_id, String vb_meta_digest) {
        this.deviceId = deviceId;
        this.androidId = android_id;
        this.gsfId = gsf_id;
        this.mediaDrmId = mediaDrm_id;
        this.vbMetaDigest = vb_meta_digest;
    }

    private String getDeviceId() {
        return deviceId;
    }

    private String getAndroidId() {
        return androidId;
    }

    private String getGsfId() {
        if (TextUtils.isEmpty(gsfId) || "null".equals(gsfId)) {
            return "";
        }
        return gsfId;
    }

    private String getMediaDrmId() {
        return mediaDrmId;
    }

    private String getVbMetaDigest() {
        return vbMetaDigest;
    }


    @Override
    public List<Pair<String, String>> loadData() {
        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("device id", getDeviceId()));
        data.add(new Pair<>("android id", getAndroidId()));
        data.add(new Pair<>("gsf id", getGsfId()));
        data.add(new Pair<>("media drm id", getMediaDrmId()));
        data.add(new Pair<>("vbmeta digest", getVbMetaDigest()));
        return data;
    }
}
