package com.xiaoniu.finance.googledownload.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.xiaoniu.finance.googledownload.R;
import com.xiaoniu.finance.googledownload.base.BaseViewHolder;
import com.xiaoniu.finance.googledownload.base.ListBaseAdapter;
import com.xiaoniu.finance.googledownload.base.LoadDataFragment;
import com.xiaoniu.finance.googledownload.bean.BB;
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

public class BaiDuFragment extends LoadDataFragment {
    private ArrayList<BB.DataBean.ListBean> al = new ArrayList<>();
    private ListView mListView;

    @Override
    protected View successView() {
        mListView = new ListView(getContext());
        mListView.setBackgroundColor(Color.GRAY);
        mListView.setAdapter(new BaiDuAdapter(al));
        return mListView;
    }



   private class BaiDuAdapter extends ListBaseAdapter {

        public BaiDuAdapter(List t) {
            super(t);
        }

       @Override
       protected List onLoadMore() {
           Log.e(TAG, "onLoadMore: " );
           return null;
       }

       @Override
        public BaseViewHolder getBaseHolder() {
            return new BaiDuHolder();
        }
    }

    private class BaiDuHolder implements BaseViewHolder<BB.DataBean.ListBean> {

        private View mConvertView;
        private Button mButton;

        @Override
        public View inflateView() {
            mConvertView = View.inflate(getContext(), R.layout.list_item_button_layout, null);
            mButton = mConvertView.findViewById(R.id.text_des);
            return mConvertView;
        }

        @Override
        public void setInfo(BB.DataBean.ListBean integer) {
            mButton.setText("我来了 " + integer.statusText);
        }

    }

    @Override
    protected int request() {
//        BB aLiRequest = (BB) BaseRequest.alirequest("http://172.20.17.97/baidu.json");
//        if (aLiRequest != null) {
//            al.addAll(aLiRequest.data.list);
//        } else {
//            //TODO 网络请求出异常了
//        }
        BaseRequest<BB> aliBeanBaseRequest = new BaseRequest<>(new BB());
        BB alirequest = aliBeanBaseRequest.alirequest("http://172.20.17.97/baidu.json");
        al.addAll(alirequest.data.list);
        return ViewContainer.SUCCESS_STATE;
    }


}
