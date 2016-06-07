package com.example.admin.letianweather.util;

/**
 * Created by admin on 2016/6/2.
 */
public interface HttpCallbackListener {


    void onFinish(String response);

    void onError(Exception e);
}
