package com.xiaoniu.finance.googledownload.base;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 文件描述：    所有的页面都是ListView 抽取Adapter
 * Created by  xn069392
 * 创建时间    2018/6/7
 * 1.把子类的holder搞过来 第一步处理的是 al 这个数据   这个 子类创建对应的adapter 传递一个T类型的数据 然后通过构造  super 吧数据给到父类
 * 2.因为这个是基类  ViewHolder 也是不是具体的，也要搞个基类
 * 3.分下这个 设置adapter的过程
 * 4.吧事件交给holder 完成
 */

public  abstract class ListBaseAdapter<T> extends android.widget.BaseAdapter {
    private List<T> al;

    public ListBaseAdapter(List<T> t) {
        al = t;
    }

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
//        ViewHolder mHolder;
        BaseViewHolder mHolder;
        if (convertView == null) {
            //a.创建对象
//            mHolder = new ViewHolder();
            mHolder = getBaseHolder();
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
        T t = al.get(position);
        mHolder.setInfo(t);
        return convertView;
    }

    public abstract BaseViewHolder getBaseHolder() ;

//    private static class ViewHolder {
//        TextView tv;
//    }
}
