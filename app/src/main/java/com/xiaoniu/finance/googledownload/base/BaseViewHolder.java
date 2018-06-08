package com.xiaoniu.finance.googledownload.base;

import android.view.View;

/**
 * 文件描述：    基类的holder
 * Created by  xn069392
 * 创建时间    2018/6/7
 */

public interface BaseViewHolder<T> {
    View inflateView();

    void setInfo(T t);
}
