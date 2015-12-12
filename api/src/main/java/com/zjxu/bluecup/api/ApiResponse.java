package com.zjxu.bluecup.api;

/**
 * json返回统一对象
 * Created by bluecup on 15-11-29.
 */
public class ApiResponse<T> {

    private String result;//返回码
    private String msg;//返回信息
    private T data;//返回数据对象
    private String uid;//uid


    /**
     * 初始化result,msg
     */
    public ApiResponse(String result,String msg) {
        this.result = result;
        this.msg = msg;
    }

    /**
     * 判断是否成功获得结果
     */
    public boolean isSuccess() {
        return result.equals("0");
    }


    /**
     * set & get
     */
    public String getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public String getUid() {
        return uid;
    }

}
