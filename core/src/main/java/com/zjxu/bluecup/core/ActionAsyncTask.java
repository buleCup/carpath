package com.zjxu.bluecup.core;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.zjxu.bluecup.api.Api;
import com.zjxu.bluecup.api.ApiResponse;

/**
 * 继承AsyncTask,简化异步线程
 * Created by bluecup on 15-12-2.
 *
 */
abstract public class ActionAsyncTask extends AsyncTask<Void,Void,Void> {

    private ActionCallBackListener mListener;//回调接口
    private ApiResponse response;//请求返回的数据对象

    /**
     * 构造函数
     * @param listener
     */
    public ActionAsyncTask(ActionCallBackListener listener) {
        mListener = listener;
    }


    /**
     * 线程异步
     * @param params
     * @return
     */
    @Override
    protected Void doInBackground(Void... params) {
        response = runForResult();
        return null;
    }

    /**
     *运行后参数返回
     */
    @Override
    protected void onPostExecute(Void aVoid) {

        if(response == null && mListener != null ){
            mListener.onFailure(-1,"网络请求失败");
            return;
        }

        if(response.isSuccess()) {
            mListener.onSuccess(response.getData());
        }else {
            mListener.onFailure(-1,response.getMsg());
        }
    }



    /**
     * 调用接口获取ApiResponse,由其子类完成
     * @return
     */
    public abstract ApiResponse runForResult();
}
