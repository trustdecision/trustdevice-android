package cn.tongdun.android.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.tongdun.android.fragments.DeviceIdFragment
import cn.tongdun.android.fragments.DeviceInfoFragment


class ViewPagerAdapter(
    private val itemCount: Int,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return DeviceIdFragment()
        } else if (position == 1) {
            return DeviceInfoFragment()
        }
        return DeviceIdFragment()
    }

    override fun getItemCount(): Int {
        return itemCount
    }
}
