package com.xiaoniu.finance.googledownload.base;

import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static com.xiaoniu.finance.googledownload.base.LoadMoreHolder.STATE_LOADMORE;

/**
 * 文件描述：    所有的页面都是ListView 抽取Adapter
 * Created by  xn069392
 * 创建时间    2018/6/7
 * 1.把子类的holder搞过来 第一步处理的是 al 这个数据   这个 子类创建对应的adapter 传递一个T类型的数据 然后通过构造  super 吧数据给到父类
 * 2.因为这个是基类  ViewHolder 也是不是具体的，也要搞个基类
 * 3.分下这个 设置adapter的过程
 * 4.吧事件交给holder 完成
 */

public abstract class ListBaseAdapter<T> extends android.widget.BaseAdapter {
    private List<T> al;
    private View mConvertView;
    public static final int TYPE_ITEM_LOAD_MORE = 0;
    public static final int TYPE_ITEM_NORMAL = 1;
    private LoadMoreHolder mLoadMoreHolder;
    private Handler mHandler = new Handler();

    private boolean isLoadingMore;

    public ListBaseAdapter(List<T> t) {
        al = t;
    }

    @Override
    public int getCount() {
        return al.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return TYPE_ITEM_LOAD_MORE;
        }
        return TYPE_ITEM_NORMAL;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
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

//        ViewHolder mHolder;
        BaseViewHolder mHolder;

        if (convertView == null) {
            //a.创建对象
//            mHolder = new ViewHolder();
            if (position == al.size()) {
                //如果是最后一个
                mHolder = getLoadMoreHolder();
            } else {
                mHolder = getBaseHolder();
            }
            //b.填充布局
//            convertView = View.inflate(getContext(), R.layout.list_item_layout, null);
            convertView = mHolder.inflateView();
            //c.找资源ID
//            mHolder.findViewID();
//            mHolder.tv = convertView.findViewById(R.id.text_des);
            convertView.setTag(mHolder);
        } else {
            mHolder = (BaseViewHolder) convertView.getTag();
        }
        //  d.设置具体内容
//        mHolder.tv.setText(al.get(position));
//        mHolder.setInfo(al.get(position));    设置一个具体的数据类型
        if (position == getCount()-1) {
            mLoadMoreHolder.setInfo(STATE_LOADMORE);

            //如果当前页面支持加载更多，开启加载数据
            if(supportLoadMore()) {
                loadMore();
            }

        } else {
            T t = al.get(position);
            mHolder.setInfo(t);
        }
        return convertView;
    }

    protected  void loadMore(){
        if(isLoadingMore){
            return;
        }
        isLoadingMore = true;
        //开始加载更多时需要刷新一次UI
        mLoadMoreHolder.setInfo(LoadMoreHolder.STATE_LOADMORE);
        //加载更多
        new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO:1.当前是在基类中，定义加载更多的行为
                List<T> moreData = null;
                int state = LoadMoreHolder.STATE_LOADMORE;
                try {
                    moreData = onLoadMore();
                    SystemClock.sleep(2000);
                    //2.添加数据,List<T>;
                    if (moreData != null) {
                        al.addAll(moreData);
                        //c.不满页，也是加载完成了
                        if (moreData.size() < getPageSize()) {
                            state = LoadMoreHolder.STATE_NONE;
                        }
                    } else {
                        //moreData为空
                        //a.虽然为空，但不是异常 TODO://
                        //b.没有更多数据
                        state = LoadMoreHolder.STATE_NONE;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    state = LoadMoreHolder.STATE_RETRY;
                }


                //3.刷新UI，第一个是刷新数据，第二个是刷新loadMoreHolder
                final int finalState = state;
                final List<T> finalMoreData = moreData;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //刷新listview数据，重新执行一轮getview()
                        //确保moreData有数据才能刷新
                        if (finalMoreData != null && finalMoreData.size() > 0) {
                            notifyDataSetChanged();
                        }
                        //            holder.setData();
                        //TODO:state赋值
                        mLoadMoreHolder.setInfo(finalState);
                        isLoadingMore = false;
                    }
                });

            }
        }).start();

    }

    protected abstract List<T> onLoadMore();


    /**
     * 当前页面是否支持加载更多功能
     * @return
     */
    protected boolean supportLoadMore() {
        return false;
    }
    public abstract BaseViewHolder getBaseHolder();

    public BaseViewHolder getLoadMoreHolder() {
        mLoadMoreHolder = new LoadMoreHolder();
        return mLoadMoreHolder;
    }

    public int getPageSize() {
        return 10;
    }
}
