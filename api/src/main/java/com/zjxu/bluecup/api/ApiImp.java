package com.zjxu.bluecup.api;

import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现APi接口具体实现类
 * @version 1.0
 * Created by bluecup on 15-11-29.
 */
public class ApiImp implements Api{

    private static final String TIME_OUT_RESULT = "";
    private static final String TIME_OUT_MSG = "服务器连接超时";
    /**
     *
     * @param loginName 登录名
     * @param password 密码
     * @return
     */
    @Override
    public ApiResponse<Void> loginApp(String loginName, String password) {


        //创建一个参数集合
        Map<String,String> paramMap =  new HashMap<String,String>();
        paramMap.put("userName",loginName);
        paramMap.put("pwd", password);

        Type type = new TypeToken<ApiResponse<Void>>(){}.getType();

        try {
            ApiResponse re = HttpRequest.getInstance().httpPost(paramMap,type,ApiUrl.API_LOGIN);
            return re;
        } catch (IOException e) {
            return new ApiResponse<Void>(TIME_OUT_RESULT,TIME_OUT_MSG);
        }

    }

}
