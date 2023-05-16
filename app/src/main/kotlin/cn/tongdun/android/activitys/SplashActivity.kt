package cn.tongdun.android.activitys

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import butterknife.BindView
import butterknife.OnClick
import cn.tongdun.android.base.BaseActivity
import cn.tongdun.mobrisk.TDRisk.init
import com.tencent.mmkv.MMKV
import com.trustdevice.android.R

@SuppressLint("CustomSplashScreen")
class SplashActivity() : BaseActivity() {

    @BindView(R.id.bt_agree)
    lateinit var btAgree: Button

    @BindView(R.id.wv_webView)
    lateinit var wvWebView: WebView

    override val contentViewResId: Int
        get() = R.layout.activity_splash

    override fun initData() {
        goToMainActivity()
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        val webSettings = wvWebView.settings
        webSettings.javaScriptCanOpenWindowsAutomatically =
            true //Setting js can directly open the window, such as window.open(), the default is false
        webSettings.javaScriptEnabled =
            true //Whether to allow js execution, the default is false. When set to true, it will remind you that it may cause XSS vulnerabilities
        webSettings.setSupportZoom(true) //Whether it can be zoomed, the default is true
        webSettings.builtInZoomControls =
            true //Whether to display the zoom button, the default is false
        webSettings.useWideViewPort = true //Set this property to scale at any scale. big view mode
        webSettings.loadWithOverviewMode = true //Solve the web page adaptation problem together with setUseWideViewPort(true)
        webSettings.domStorageEnabled = true //DOM Storage
        wvWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                Toast.makeText(this@SplashActivity, url, Toast.LENGTH_SHORT).show()
                view.loadUrl(url)
                return true
            }

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                Toast.makeText(this@SplashActivity, "error!!!", Toast.LENGTH_SHORT).show()
            }
        }
        wvWebView.loadUrl("file:///android_asset/PrivacyPolicy.html")
    }

    @OnClick(R.id.bt_agree, R.id.bt_reject)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_agree -> {
                MMKV.defaultMMKV().encode("isAgreePrivacyAgreement", true)
                init(this)
                goToMainActivity()
            }
            R.id.bt_reject -> finish()
        }
    }

    private fun goToMainActivity() {
        val isAgree = MMKV.defaultMMKV().decodeBool("isAgreePrivacyAgreement")
        if (isAgree) {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}