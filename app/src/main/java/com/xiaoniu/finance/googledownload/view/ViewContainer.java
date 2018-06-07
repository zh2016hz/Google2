package com.xiaoniu.finance.googledownload.view;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.xiaoniu.finance.googledownload.R;


/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/6/7
 */

public abstract class ViewContainer extends FrameLayout {
    private final Context mContext;
    public static final int LOADING_LAYOUT = 1;
    public static final int ERROR_LAYOUT = -1;
    public static final int SUCCESS_LAYOUT = 0;
    public static final int EMPTY_LAYOUT = 2;
    private int mCurrentState =99;  //这状态不付初始值 会出问题  因为你初始值就是0 和成功状态一样了
    private View mEmpty;
    private View mError;
    private View mLoading;
    private View mSuccess;
    private Handler mHandler = new Handler();

    public ViewContainer(@NonNull Context context) {
        this(context, null);
    }

    public ViewContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    /**
     * 填充几种状态的布局
     */
    private void init() {
        mEmpty = View.inflate(mContext, R.layout.empty_layout, null);
        addView(mEmpty);
        mLoading = View.inflate(mContext, R.layout.loading_layout, null);
        addView(mLoading);
        mError = View.inflate(mContext, R.layout.error_layout, null);
        addView(mError);
        checkView();
    }

    /**
     * 判断具体展示哪些页面
     */
    private void checkView() {
        mEmpty.setVisibility(mCurrentState == EMPTY_LAYOUT ? VISIBLE : GONE);
        mError.setVisibility(mCurrentState == ERROR_LAYOUT ? VISIBLE : GONE);
        mLoading.setVisibility(mCurrentState == LOADING_LAYOUT ? VISIBLE : GONE);

        //当请求成功，显示成功页面
        if (mSuccess == null && mCurrentState == SUCCESS_LAYOUT) {
            mSuccess = showSuccessView();
            addView(mSuccess);
        }
        if (mSuccess != null) {
            mSuccess.setVisibility(mCurrentState == SUCCESS_LAYOUT ? VISIBLE : GONE);
        }
    }


    /**
     * 请求数据
     */
    public void loadData() {
        mCurrentState = LOADING_LAYOUT;
        checkView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //在子线程中执行具体的请求
                mCurrentState = baseRequest();
                //根据这个 状态 然后再次调用检查显示什么UI
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        checkView();
                    }
                });
            }
        }).start();

    }

    protected abstract int baseRequest();

    protected abstract View showSuccessView();
}
