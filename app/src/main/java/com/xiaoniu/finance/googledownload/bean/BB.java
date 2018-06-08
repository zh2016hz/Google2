package com.xiaoniu.finance.googledownload.bean;

import java.util.List;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/6/8
 */

public class BB {

    public String code;
    public DataBean data;
    public String message;
    public long serverTime;

    public static class DataBean {
        public int totalNum;
        public double holdingTotalMoney;
        public double collectTotalEarning;
        public List<ListBean> list;

        public static class ListBean {
            public int productId;
            public int investId;
            public String name;
            public int status;
            public String statusText;
            public double holdingMoney;
            public double expectEarning;
            public int refundType;
            public String refundRemark;
            public String refundTypeText;
            public String detailUrl;
        }
    }
}
