package com.xiaoniu.finance.googledownload.manager;

import android.util.SparseArray;

import com.xiaoniu.finance.googledownload.base.BaseFragment;


/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/6/6
 */

public class FragmentManager {
    private static SparseArray<BaseFragment> mArray = new SparseArray<>();

    private FragmentManager() {
    }

    private static FragmentManager sInstance;

    private static FragmentManager getInstance() {
        if (sInstance == null) {
            sInstance = new FragmentManager();
        }
        return sInstance;
    }


    /**
     * @param position
     * @param fragment
     */
    public static void addFragment(int position, BaseFragment fragment) {
        mArray.append(position, fragment);
    }

    public static BaseFragment getFragment(int position) {

        return mArray.get(position);

    }
}
