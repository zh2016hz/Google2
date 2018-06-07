package com.xiaoniu.finance.googledownload;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.xiaoniu.finance.googledownload.base.LoadDataFragment;
import com.xiaoniu.finance.googledownload.fragment.AliFragment;
import com.xiaoniu.finance.googledownload.fragment.BaiDuFragment;
import com.xiaoniu.finance.googledownload.fragment.TenCentFragment;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,TabLayout.OnTabSelectedListener {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tabLayout);


        //注册监听
        mViewPager.addOnPageChangeListener(this);
        mTabLayout.addOnTabSelectedListener(this);
        mTabLayout.addTab(mTabLayout.newTab().setText("阿里"));
        mTabLayout.addTab(mTabLayout.newTab().setText("腾讯"));
        mTabLayout.addTab(mTabLayout.newTab().setText("百度"));
        //添加适配器，在viewPager里引入Fragment
        mViewPager.setAdapter(mFragmentPagerAdapter);

    }

    private FragmentPagerAdapter  mFragmentPagerAdapter  =  new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            LoadDataFragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new AliFragment();
                    break;
                case 1:
                    fragment = new BaiDuFragment();
                    break;
                case 2:
                    fragment = new TenCentFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    };
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTabLayout.getTabAt(position).select();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
