package cn.tongdun.android.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.mmkv.MMKV;
import com.trustdevice.android.R;

import butterknife.BindView;
import butterknife.OnClick;
import cn.tongdun.android.base.BaseActivity;
import cn.tongdun.mobrisk.TDRisk;


public class SplashActivity extends BaseActivity{
    @BindView(R.id.bt_agree)
    Button btAgree;
    @BindView(R.id.wv_webView)
    WebView wvWebView;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        goToMainActivity();
    }

    @Override
    protected void initView() {
        WebSettings webSettings = wvWebView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//Setting js can directly open the window, such as window.open(), the default is false
        webSettings.setJavaScriptEnabled(true);//Whether to allow js execution, the default is false. When set to true, it will remind you that it may cause XSS vulnerabilities
        webSettings.setSupportZoom(true);//Whether it can be zoomed, the default is true
        webSettings.setBuiltInZoomControls(true);//Whether to display the zoom button, the default is false
        webSettings.setUseWideViewPort(true);//Set this property to scale at any scale. big view mode
        webSettings.setLoadWithOverviewMode(true);//Solve the web page adaptation problem together with setUseWideViewPort(true)
        webSettings.setDomStorageEnabled(true);//DOM Storage
        wvWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Toast.makeText(SplashActivity.this, url, Toast.LENGTH_SHORT).show();
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(SplashActivity.this, "error!!!", Toast.LENGTH_SHORT).show();
            }
        });
        wvWebView.loadUrl("file:///android_asset/PrivacyPolicy.html");
    }

    @OnClick({R.id.bt_agree,R.id.bt_reject})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_agree:
                MMKV.defaultMMKV().encode("isAgreePrivacyAgreement", true);
                TDRisk.init(this);
                goToMainActivity();
                break;
            case R.id.bt_reject:
                this.finish();
                break;
        }
    }

    public void goToMainActivity(){
        boolean isAgree = MMKV.defaultMMKV().decodeBool("isAgreePrivacyAgreement");
        if (isAgree) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
}