package com.xiaoniu.finance.googledownload.base;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoniu.finance.googledownload.R;

import static com.xiaoniu.finance.googledownload.GoogleApplication.getContext;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/6/8
 */

class LoadMoreHolder implements BaseViewHolder<Integer> {
    private View mConvertView;
    public static final int STATE_LOADMORE = 2;
    public static  final int STATE_RETRY = 1;
    //不支持加载更多，或者当前页面所有数据已经全部加载
    public static final int STATE_NONE = 0;
    private TextView mMore;
    private TextView mEmpty;

    @Override
    public View inflateView() {
        mConvertView = View.inflate(getContext(), R.layout.loading_more_layout, null);
        mMore = mConvertView.findViewById(R.id.more);
        mEmpty = mConvertView.findViewById(R.id.empty);
        LinearLayout moreLayout = mConvertView.findViewById(R.id.load_more_layout);
        return mConvertView;
    }

    @Override
    public void setInfo(Integer state) {
        if(state == STATE_RETRY){
            mMore.setVisibility(View.VISIBLE);
            mEmpty.setVisibility(View.GONE);
        }else if(state == STATE_LOADMORE){
            mMore.setVisibility(View.GONE);
            mEmpty.setVisibility(View.VISIBLE);
        }else{
            mMore.setVisibility(View.GONE);
            mEmpty.setVisibility(View.GONE);
        }
    }
}
