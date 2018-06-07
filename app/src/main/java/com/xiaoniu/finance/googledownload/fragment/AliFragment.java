package com.xiaoniu.finance.googledownload.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.xiaoniu.finance.googledownload.base.LoadDataFragment;
import com.xiaoniu.finance.googledownload.view.ViewContainer;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/6/7
 */

public class AliFragment extends LoadDataFragment {


    @Override
    protected View successView() {
        TextView textView = new TextView(getContext());
        textView.setText("ha哈哈哈.我市 埃里");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackground(new ColorDrawable(Color.GREEN));
        }
        return textView;
    }

    @Override
    protected int request() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ViewContainer.SUCCESS_LAYOUT;
    }
}
