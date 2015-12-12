package com.zjxu.bluecup.api;

/**
 * 申明接口方法
 * @version 1.0
 * Created by bluecup on 15-11-29.
 */
public interface Api {

    /**
     * 例如:登录方法
     *
     * @param loginName 登录名
     * @param password 密码
     * @return 成功时返回:{...}
     */
    public ApiResponse<Void> loginApp(String loginName,String password);

}
