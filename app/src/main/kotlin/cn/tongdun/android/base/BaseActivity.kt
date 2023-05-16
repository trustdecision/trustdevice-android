package cn.tongdun.android.base

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder

abstract class BaseActivity : AppCompatActivity() {
    private var unbinder: Unbinder? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewResId)
        if (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        unbinder = ButterKnife.bind(this)
        initData()
        initView()
    }

    protected abstract val contentViewResId: Int

    protected abstract fun initData()
    protected abstract fun initView()
    override fun onDestroy() {
        super.onDestroy()
        if (unbinder != null) {
            unbinder!!.unbind()
        }
    }
}