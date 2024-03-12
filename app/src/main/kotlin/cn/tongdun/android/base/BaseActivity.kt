package cn.tongdun.android.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
    private lateinit var _binding: T
    protected val binding get() = _binding

    protected lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        _binding = getViewBinding()
        setContentView(_binding.root)

        if (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        initData()
        initView()
    }

    protected abstract fun getViewBinding(): T

    protected abstract fun initData()
    protected abstract fun initView()
    override fun onDestroy() {
        super.onDestroy()
    }
}