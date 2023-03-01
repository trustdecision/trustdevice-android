package cn.tongdun.android.activity;

import android.widget.TextView;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.trustdevice.android.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.tongdun.android.adapter.ViewPagerAdapter;
import cn.tongdun.android.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager2 mViewPager;

    private final List<String> mTabList = new ArrayList<>();
    private TabLayoutMediator mTabLayoutMediator;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mTabList.add("Device id");
        mTabList.add("Device info");
    }

    @Override
    protected void initView() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mTabList.size(), getSupportFragmentManager(), getLifecycle());
        mViewPager.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayoutMediator = new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> {
            TextView tabView = new TextView(MainActivity.this);
            tabView.setText(mTabList.get(position));
            tab.setCustomView(tabView);
        });
        // binding
        mTabLayoutMediator.attach();
    }

    @Override
    protected void onDestroy() {
        mTabLayoutMediator.detach();
        super.onDestroy();
    }
}