package com.zjxu.bluecup.core;

import android.util.Log;

import com.zjxu.bluecup.api.Api;
import com.zjxu.bluecup.api.ApiImp;
import com.zjxu.bluecup.api.ApiResponse;

/**
 * Action的实现类
 * Created by bluecup on 15-12-2.
 */
public class AppActionImp implements AppAction {

    private Api mApi;

    //构造函数
    public AppActionImp() {
        this.mApi = new ApiImp();
    }

    /**
     * 登录事件的处理
     * @param userName
     * @param password
     * @param listener
     */
    @Override
    public void loginApp(final String userName,final String password, ActionCallBackListener<Void> listener) {

        //参数为空检测
        if(userName.isEmpty() || password.isEmpty()) {
            if(listener != null) {
                listener.onFailure(AppAction.PARAM_IS_EMPTY,"账号或密码为空");
            }
        }

        //参数长度检测
        if(userName.length()>20 || password.length()>20) {
            if(listener != null) {
                listener.onFailure(AppAction.PARAM_IS_OUT_OF_LENGTH,"账号或密码长度超出范围");
            }
        }


        //异步请求
        new ActionAsyncTask(listener){
            @Override
            public ApiResponse runForResult() {
                return mApi.loginApp(userName,password);
            }
        }.execute();

    }
}
