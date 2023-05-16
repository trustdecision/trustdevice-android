package cn.tongdun.android.activitys


import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import butterknife.BindView
import cn.tongdun.android.adapters.ViewPagerAdapter
import cn.tongdun.android.base.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.trustdevice.android.R


class MainActivity : BaseActivity() {

    @BindView(R.id.tab_layout)
    lateinit var mTabLayout: TabLayout

    @BindView(R.id.viewpager)
    lateinit var mViewPager: ViewPager2

    private val mTabList: MutableList<String> = ArrayList()
    var mTabLayoutMediator: TabLayoutMediator? = null

    override val contentViewResId: Int
        get() = R.layout.activity_main

   override fun initData() {
        mTabList.add("Device id")
        mTabList.add("Device info")
    }

    override fun initView() {
        val viewPagerAdapter =
            ViewPagerAdapter(mTabList.size, getSupportFragmentManager(), getLifecycle())
        mViewPager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        mViewPager.adapter = viewPagerAdapter
        mTabLayoutMediator = TabLayoutMediator(
            mTabLayout, mViewPager
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
