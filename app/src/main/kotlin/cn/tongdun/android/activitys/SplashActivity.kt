package cn.tongdun.android.activitys

import android.annotation.SuppressLint
import android.content.Intent
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import cn.tongdun.android.base.BaseActivity
import cn.tongdun.mobrisk.TDRisk.init
import com.tencent.mmkv.MMKV
import com.trustdevice.android.demo.R
import com.trustdevice.android.demo.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity() : BaseActivity<ActivitySplashBinding>() {
    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun initData() {
        goToMainActivity()
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        val webSettings = binding.wvWebView.settings
        webSettings.apply {
            javaScriptCanOpenWindowsAutomatically =
                true //Setting js can directly open the window, such as window.open(), the default is false
            javaScriptEnabled =
                true //Whether to allow js execution, the default is false. When set to true, it will remind you that it may cause XSS vulnerabilities
            setSupportZoom(true) //Whether it can be zoomed, the default is true
            builtInZoomControls =
                true //Whether to display the zoom button, the default is false
            useWideViewPort =
                true //Set this property to scale at any scale. big view mode
            loadWithOverviewMode =
                true //Solve the web page adaptation problem together with setUseWideViewPort(true)
            domStorageEnabled = true //DOM Storage
        }
        binding.wvWebView.webViewClient = object : WebViewClient() {
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
        binding.wvWebView.loadUrl("file:///android_asset/PrivacyPolicy.html")

        binding.setClickListener { view ->
            when (view.id) {
                R.id.bt_agree -> {
                    MMKV.defaultMMKV().encode("isAgreePrivacyAgreement", true)
                    init(this)
                    goToMainActivity()
                }

                R.id.bt_reject -> finish()
            }
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