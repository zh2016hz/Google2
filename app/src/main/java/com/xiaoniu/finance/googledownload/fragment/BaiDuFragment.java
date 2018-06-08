package com.xiaoniu.finance.googledownload.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.xiaoniu.finance.googledownload.R;
import com.xiaoniu.finance.googledownload.base.BaseViewHolder;
import com.xiaoniu.finance.googledownload.base.ListBaseAdapter;
import com.xiaoniu.finance.googledownload.base.LoadDataFragment;
import com.xiaoniu.finance.googledownload.view.ViewContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/6/7
 */

public class BaiDuFragment extends LoadDataFragment {
    private ArrayList<Integer> al = new ArrayList<>();
    private ListView mListView;

    @Override
    protected View successView() {
        mockData();
        mListView = new ListView(getContext());
        mListView.setBackgroundColor(Color.GRAY);
        mListView.setAdapter(new BaiDuAdapter(al));
        return mListView;
    }

    /**
     * 模拟数据
     */
    private void mockData() {
        for (int i = 0; i < 100; i++) {
            al.add(i);
        }
    }

   private class BaiDuAdapter extends ListBaseAdapter {

        public BaiDuAdapter(List t) {
            super(t);
        }

        @Override
        public BaseViewHolder getBaseHolder() {
            return new BaiDuHolder();
        }
    }

    private class BaiDuHolder implements BaseViewHolder<Integer> {

        private View mConvertView;
        private Button mButton;

        @Override
        public View inflateView() {
            mConvertView = View.inflate(getContext(), R.layout.list_item_button_layout, null);
            mButton = mConvertView.findViewById(R.id.text_des);
            return mConvertView;
        }

        @Override
        public void setInfo(Integer integer) {
            mButton.setText("我来了 " + integer);
        }

    }

    @Override
    protected int request() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ViewContainer.SUCCESS_STATE;
    }


}
