package com.xiaoniu.finance.googledownload;

import android.app.Application;
import android.content.Context;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/6/7
 */

public class GoogleApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
