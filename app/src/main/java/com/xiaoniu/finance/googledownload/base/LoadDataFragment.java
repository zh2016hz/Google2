package com.xiaoniu.finance.googledownload.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoniu.finance.googledownload.view.ViewContainer;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/6/7
 */

public abstract class LoadDataFragment extends BaseFragment {

    private ViewContainer mViewContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //每个类 都继承 会调用多次
        if(mViewContainer == null){
            mViewContainer = new ViewContainer(getContext()) {
                @Override
                public int baseRequest() {
                    //因为这个也是fragment的基类，所以这个抽象的事件要传递给子类
                    return request();
                }

                @Override
                protected View showSuccessView() {
                    return successView();
                }
            };
        }
        return mViewContainer;
    }

    protected abstract View successView();

    protected abstract int request();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //因为每次生命周期执行时候就请求数据了，但是我想要做懒加载，这里注释掉

    }

    public void loadContainer() {
        mViewContainer.loadData();
    }
}
