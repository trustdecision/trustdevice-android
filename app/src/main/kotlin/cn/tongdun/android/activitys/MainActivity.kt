package cn.tongdun.android.activitys


import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import cn.tongdun.android.adapters.ViewPagerAdapter
import cn.tongdun.android.base.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.trustdevice.android.demo.R
import com.trustdevice.android.demo.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mTabList: MutableList<String> = ArrayList()
    private var mTabLayoutMediator: TabLayoutMediator? = null
    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun initData() {
        mTabList.add(getString(R.string.tab_title_device_id))
        mTabList.add(getString(R.string.tab_title_device_info))
    }

    override fun initView() {
        val viewPagerAdapter =
            ViewPagerAdapter(mTabList.size, supportFragmentManager, lifecycle)
        binding.viewpager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        binding.viewpager.adapter = viewPagerAdapter
        mTabLayoutMediator = TabLayoutMediator(
            binding.tabLayout, binding.viewpager
        ) { tab: TabLayout.Tab, position: Int ->
            val tabView = TextView(this)
            tabView.text = mTabList[position]
            tabView.setTextColor(getResources().getColor(R.color.normal_text))
            tab.customView = tabView
        }
        // binding
        mTabLayoutMediator!!.attach()
    }

    override fun onDestroy() {
        mTabLayoutMediator!!.detach()
        super.onDestroy()
    }
}
