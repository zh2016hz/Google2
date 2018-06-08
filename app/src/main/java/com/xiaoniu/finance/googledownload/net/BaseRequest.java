package com.xiaoniu.finance.googledownload.net;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/6/8
 */

public class BaseRequest<T> {
    private T currentBean;
    public BaseRequest(T t) {
        currentBean = t;
    }

    public  T alirequest(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
//                .url("http://10.0.2.2/mycommonandtransfer.json")
                .url(url)
                .get()
//                .post(new FormBody.Builder()
//                        .add("key", "value")
//                        .build())
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            Gson gson = new Gson();
           T  obj = (T) gson.fromJson(response.body().string(),currentBean.getClass());
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
