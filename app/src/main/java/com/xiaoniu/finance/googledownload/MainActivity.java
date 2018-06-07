package com.xiaoniu.finance.googledownload;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xiaoniu.finance.googledownload.base.BaseFragment;
import com.xiaoniu.finance.googledownload.base.LoadDataFragment;
import com.xiaoniu.finance.googledownload.fragment.AliFragment;
import com.xiaoniu.finance.googledownload.fragment.BaiDuFragment;
import com.xiaoniu.finance.googledownload.fragment.TenCentFragment;
import com.xiaoniu.finance.googledownload.manager.FragmentManager;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {
    private static final String TAG = "AAA";
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private LoadDataFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tabLayout);


        //注册监听  再添加tab之前调用 不然第一个不会选中
        mViewPager.addOnPageChangeListener(this);
        mTabLayout.addOnTabSelectedListener(this);
//        mTabLayout.addTab(mTabLayout.newTab().setText("阿里"));  这个是一个google的坑，
        mTabLayout.addTab(mTabLayout.newTab().setText("阿里"),true);
        mTabLayout.addTab(mTabLayout.newTab().setText("腾讯"));
        mTabLayout.addTab(mTabLayout.newTab().setText("百度"));
        mTabLayout.addTab(mTabLayout.newTab().setText("百度1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("百度2"));
        //添加适配器，在viewPager里引入Fragment
        mViewPager.setAdapter(mFragmentPagerAdapter);

    }

/*    private FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
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
                case 3:
                    fragment = new TenCentFragment();
                    break;
                case 4:
                    fragment = new TenCentFragment();
                    break;

            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }
    };*/

    /**
     * 第二种方案：使用FragmentPagerStateAdapter  这个适合页面较多，系统默认不做缓存，然后，自己做缓存
     */
    private FragmentStatePagerAdapter mFragmentPagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        public static final String TAG = "getItem";
        private BaseFragment mFragment;

        @Override
        public Fragment getItem(int position) {
            Log.e(TAG, "getItem: " + position);

            BaseFragment fragment = FragmentManager.getFragment(position);
            if (fragment != null) {
                Log.e(TAG, "getItem: 获取缓存   fragment");
                mFragment = fragment;
            } else {
                switch (position) {
                    case 0:
                        mFragment = new AliFragment();
                        break;
                    case 1:
                        mFragment = new BaiDuFragment();
                        break;
                    case 2:
                        mFragment = new TenCentFragment();
                        break;
                    case 3:
                        mFragment = new TenCentFragment();
                        break;
                    case 4:
                        mFragment = new TenCentFragment();
                        break;
                }
                Log.e(TAG, "getItem: 创建新的fragment");
                FragmentManager.addFragment(position, mFragment);
            }
            return mFragment;
        }

        @Override
        public int getCount() {
            return 5;
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTabLayout.getTabAt(position).select();
        //当页面被选中时候才进行数据请求 ，当然在这里调用Container的那个不可能new 一个对象，所以我想拿到实例 也不好拿
        //吧那边封装一个方法，然后掉方法
//        LoadDataFragment.loadContainer();      这样调用又要吧那边搞成static的
// TODO :如果封装成static 调用 需要把成员变量也搞成static 这样分析 这个Activity 和那边的那个Fragment 有什么关系呢  缓存能拿到不
        mFragment = (LoadDataFragment) FragmentManager.getFragment(position);
        mFragment.loadContainer();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
        Log.e(TAG, "打印当前tab 的位置  ：  "+tab.getPosition() );
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
