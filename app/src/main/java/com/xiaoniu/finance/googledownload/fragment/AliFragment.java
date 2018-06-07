package com.xiaoniu.finance.googledownload.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaoniu.finance.googledownload.R;
import com.xiaoniu.finance.googledownload.base.LoadDataFragment;
import com.xiaoniu.finance.googledownload.view.ViewContainer;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/6/7
 */

public class AliFragment extends LoadDataFragment {
    private ArrayList<String> al = new ArrayList<>();
    private ListView mListView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //TODO :临时处理第一次进页面不加载数据问题
        if(mListView ==null){
            Log.e(TAG, "onActivityCreated: 这个方法不会嗲用多次吧" );
            loadContainer();
        }
    }

    @Override
    protected View successView() {
        mockData();
        mListView = new ListView(getContext());
        mListView.setBackgroundColor(Color.GRAY);
        mListView.setAdapter(new ALiAdapter());
        return mListView;
    }

    /**
     * 模拟数据
     */
    private void mockData() {
        for (int i = 0; i < 100; i++) {
            al.add("数据 ：" + i + " @@");
        }
    }

    private class ALiAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return al.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mHolder;
            if (convertView == null) {
                mHolder = new ViewHolder();
                convertView = View.inflate(getContext(), R.layout.list_item_layout, null);
                mHolder.tv = convertView.findViewById(R.id.text_des);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) convertView.getTag();
            }
            mHolder.tv.setText(al.get(position));
            return convertView;
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

    private static class ViewHolder {
        TextView tv;
    }
}
