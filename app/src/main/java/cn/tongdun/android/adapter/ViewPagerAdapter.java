package cn.tongdun.android.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import cn.tongdun.android.fragments.DeviceIdFragment;
import cn.tongdun.android.fragments.DeviceInfoFragment;

/**
 * @description: ViewPager2 Adapter
 * @author: wuzuchang
 * @date: 2023/2/28
 */
public class ViewPagerAdapter extends FragmentStateAdapter {

    private int mItemCount;

    public ViewPagerAdapter(int itemCount, @NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        mItemCount = itemCount;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new DeviceIdFragment();
        } else if (position == 1) {
            return new DeviceInfoFragment();
        }
        return new DeviceIdFragment();
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }
}
