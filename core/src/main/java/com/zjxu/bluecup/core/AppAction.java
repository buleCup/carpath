package com.zjxu.bluecup.core;

/**
 * 接口里只声明和界面有关的参数,其余的参数在ApiImp中添加
 * 这一层主要用来检测参数是否为空,参数是否合理,和异步线程调用接口
 * 并含有callback接口用于返回请求结果
 * Created by bluecup on 15-12-2.
 */
public interface AppAction {

    public static final int PARAM_IS_EMPTY = 0x001;//参数为空
    public static final int PARAM_IS_ERROR = 0x002;//参数有误
    public static final int PARAM_IS_OUT_OF_LENGTH = 0x003;//参数长度超出

    /**
     *登录接口
     */
    public void loginApp(String userName,String password,ActionCallBackListener<Void> listener);


}
