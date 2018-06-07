package com.xiaoniu.finance.googledownload.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoniu.finance.googledownload.base.LoadDataFragment;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/6/7
 */

public class BaiDuFragment extends LoadDataFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setText("爱好");
        textView.setTextColor(Color.GREEN);
        return textView;
    }
}
