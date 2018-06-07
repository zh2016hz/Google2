package com.xiaoniu.finance.googledownload.fragment;

import android.view.View;

import com.xiaoniu.finance.googledownload.base.LoadDataFragment;
import com.xiaoniu.finance.googledownload.view.ViewContainer;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/6/7
 */

public class BaiDuFragment extends LoadDataFragment {
    @Override
    protected View successView() {
        return null;
    }

    @Override
    protected int request() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ViewContainer.EMPTY_STATE;
    }
}
