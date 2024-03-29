package cn.tongdun.mobrisk.core.collectors

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.media.MediaDrm
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import cn.tongdun.mobrisk.core.tools.Constants.SHA256
import cn.tongdun.mobrisk.core.tools.JNIHelper
import cn.tongdun.mobrisk.core.tools.executeSafe
import cn.tongdun.mobrisk.core.tools.hash
import java.util.*


/**
 * @description:DeviceId
 * @author: wuzuchang
 * @date: 2023/5/15
 */
interface DeviceIdInterface {
    fun getDeviceId(): String
    fun getAndroidId(): String?
    fun getMediaDrmId(): String?
    fun getGsfId(): String?
    fun getVbMetaDigest(): String?
    fun getGoogleAdid(context: Context): String
}

private const val URI_GSF_CONTENT_PROVIDER = "content://com.google.android.gsf.gservices"
private const val GSF_ID_KEY = "android_id"

class DeviceIdCollector(private val contentResolver: ContentResolver) : DeviceIdInterface {

    private var androidId: String? = null
    private var gsfId: String? = null
    private var mediaDrmId: String? = null

    override fun getDeviceId(): String {
        if (androidId == null) {
            androidId = getAndroidId()
        }
        if (!TextUtils.isEmpty(androidId)) {
            return androidId!!.hash(SHA256)
        }
        if (gsfId == null) {
            gsfId = getGsfId()
        }
        if (!TextUtils.isEmpty(gsfId)) {
            return gsfId!!.hash(SHA256)
        }
        if (mediaDrmId != null) {
            mediaDrmId = getMediaDrmId()
        }
        if (!TextUtils.isEmpty(mediaDrmId)) {
            return mediaDrmId!!
        }
        return getVbMetaDigest().hash(SHA256)
    }

    @SuppressLint("HardwareIds")
    override fun getAndroidId(): String? =
        if (androidId !== null) {
            androidId
        } else
            executeSafe({
                androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
                androidId
            }, null)

    override fun getMediaDrmId(): String? {
        if (mediaDrmId !== null) {
            return mediaDrmId
        }
        var drm: MediaDrm? = null
        try {
            val uuid = UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L)
            drm = MediaDrm(uuid)
            val bytes = drm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID)
            mediaDrmId = bytes.hash(SHA256)
        } catch (ignored: Throwable) {
        } finally {
            if (drm != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    drm.close()
                } else {
                    drm.release()
                }
            }
        }
        return mediaDrmId
    }

    override fun getGsfId(): String? {
        if (gsfId !== null) {
            return gsfId
        }
        executeSafe {
            val uri = Uri.parse(URI_GSF_CONTENT_PROVIDER)
            val cursor = contentResolver.query(uri, null, null, arrayOf(GSF_ID_KEY), null)
                ?: return@executeSafe
            if (!cursor.moveToFirst() || cursor.columnCount < 2) {
                cursor.close()
                return@executeSafe
            }
            try {
                gsfId = cursor.getString(1)
                cursor.close()
            } catch (e: Throwable) {
                cursor.close()
            }
        }
        return gsfId
    }

    override fun getVbMetaDigest(): String =
        executeSafe({ JNIHelper.call2("ro.boot.vbmeta.digest", "") }, "")


    override fun getGoogleAdid(context: Context): String =
        executeSafe({ AdvertisingIdClient.getId(context).toString() }, "")

}