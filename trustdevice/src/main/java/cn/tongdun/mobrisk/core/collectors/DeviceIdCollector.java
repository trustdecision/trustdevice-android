package cn.tongdun.mobrisk.core.collectors;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaDrm;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import java.util.UUID;

import cn.tongdun.mobrisk.core.utils.MessageDigestUtils;

/**
 * @description: Device id
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class DeviceIdCollector {

    private static final String URI_GSF_CONTENT_PROVIDER = "content://com.google.android.gsf.gservices";
    private static final String GSF_ID_KEY = "android_id";
    private ContentResolver mContentResolver;
    private String androidId;
    private String gsfId;
    private String mediaDrmId;

    public DeviceIdCollector(ContentResolver contentResolver) {
        mContentResolver = contentResolver;
    }

    public String getDeviceId() {

        if (androidId == null) {
            androidId = getAndroidId();
        }
        if (!TextUtils.isEmpty(androidId)) {
            return MessageDigestUtils.hash("SHA-256", androidId);
        }
        if (gsfId == null) {
            gsfId = getGsfId();
        }
        if (!TextUtils.isEmpty(gsfId)) {
            return MessageDigestUtils.hash("SHA-256", gsfId);
        }
        if (mediaDrmId != null) {
            return mediaDrmId;
        }
        return MessageDigestUtils.hash("SHA-256", getMediaDrmId() + getVbMetaDigest());
    }

    public String getAndroidId() {
        if (androidId != null) {
            return androidId;
        }
        try {
            androidId = Settings.Secure.getString(mContentResolver, Settings.Secure.ANDROID_ID);
        } catch (Exception ignored) {
        }
        return androidId;
    }

    public String getMediaDrmId() {
        if (mediaDrmId != null) {
            return mediaDrmId;
        }
        mediaDrmId = "";
        MediaDrm drm = null;
        try {
            UUID uuid = new UUID(0xEDEF8BA979D64ACEL, 0xA3C827DCD51D21EDL);
            drm = new MediaDrm(uuid);
            byte[] bytes = drm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID);
            mediaDrmId = MessageDigestUtils.hash("SHA-256", bytes);
        } catch (Throwable ignored) {
        } finally {
            if (drm != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    drm.close();
                } else {
                    drm.release();
                }
            }
        }
        return mediaDrmId;
    }

    public String getGsfId() {
        if (gsfId != null) {
            return gsfId;
        }
        gsfId = "";
        try {
            Uri uri = Uri.parse(URI_GSF_CONTENT_PROVIDER);
            Cursor cursor = mContentResolver.query(uri, null, null, new String[]{GSF_ID_KEY}, null);
            if (cursor == null) {
                return null;
            }
            if (!cursor.moveToFirst() || cursor.getColumnCount() < 2) {
                cursor.close();
                return null;
            }
            try {
                gsfId = cursor.getString(1);
                cursor.close();
            } catch (Throwable e) {
                cursor.close();
            }
        } catch (Throwable e) {
        }
        return gsfId;
    }

    public String getVbMetaDigest(){
        SystemPropertyInfoCollector systemPropertyInfoCollector = new SystemPropertyInfoCollector();
        return systemPropertyInfoCollector.getMetaDigest();
    }
}
