package com.xiaoniu.finance.googledownload.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaoniu.finance.googledownload.R;
import com.xiaoniu.finance.googledownload.base.BaseViewHolder;
import com.xiaoniu.finance.googledownload.base.ListBaseAdapter;
import com.xiaoniu.finance.googledownload.base.LoadDataFragment;
import com.xiaoniu.finance.googledownload.bean.AliBean;
import com.xiaoniu.finance.googledownload.net.BaseRequest;
import com.xiaoniu.finance.googledownload.view.ViewContainer;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/6/7
 */

public class AliFragment extends LoadDataFragment {
    private ArrayList<AliBean.DataBean.ListBean> al = new ArrayList<>();
    private ListView mListView;
    private View mConvertView;
    private TextView mText;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //TODO :临时处理第一次进页面不加载数据问题
        if (mListView == null) {
            Log.e(TAG, "onActivityCreated: 这个方法不会嗲用多次吧");
            loadContainer();
        }
    }

    @Override
    protected View successView() {
//        mockData();
        mListView = new ListView(getContext());
        mListView.setBackgroundColor(Color.GRAY);
        mListView.setAdapter(new ALiAdapter(al));
        return mListView;
    }

   /* */

    /**
     * 模拟数据
     *//*
    private void mockData() {
        for (int i = 0; i < 100; i++) {
            al.add("数据 ：" + i + " @@");
        }
    }*/

    private class ALiAdapter extends ListBaseAdapter {

        public ALiAdapter(List t) {
            super(t);
        }

        @Override
        public BaseViewHolder getBaseHolder() {
            return new AliHolder();
        }
    }

    private class AliHolder implements BaseViewHolder<AliBean.DataBean.ListBean> {
        @Override
        public View inflateView() {
            mConvertView = View.inflate(getContext(), R.layout.list_item_layout, null);
            mText = mConvertView.findViewById(R.id.text_des);
            return mConvertView;
        }

        @Override
        public void setInfo(AliBean.DataBean.ListBean s) {
            mText.setText(s.name);
        }
    }

    @Override
    protected int request() {
        BaseRequest<AliBean> aliBeanBaseRequest = new BaseRequest<>(new AliBean());
        AliBean alirequest = aliBeanBaseRequest.alirequest("http://172.20.17.97/mycommonandtransfer.json");
//        AliBean alirequest = (AliBean) BaseRequest.alirequest("http://172.20.17.97/mycommonandtransfer.json");
        if (alirequest != null) {
            al.addAll(alirequest.data.list);
        } else {
            //TODO 网络请求出异常了
        }
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return ViewContainer.SUCCESS_STATE;
    }


}
