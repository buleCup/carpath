package com.zjxu.bluecup.core;

/**
 * 在界面层调用Action时,事件的回调接口
 * Created by bluecup on 15-12-2.
 */
public interface ActionCallBackListener<T> {

    /**
     * 请求成功时返回
     */
    public void onSuccess(T data);


    /**
     * 请求失败时返回
     */
    public void onFailure(int errorCode,String message);

}
