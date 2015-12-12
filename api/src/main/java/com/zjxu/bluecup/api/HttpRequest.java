package com.zjxu.bluecup.api;

import android.util.Log;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 封装网络请求包括GET和POST等方法
 * Created by bluecup on 15-12-2.
 */
public class HttpRequest {
    //http://10.120.135.4/gps/index.php/Act/user/login?username=123456&pwd=123456
    private static final String SERVER_URL = "http://10.120.135.4/gps/index.php/Act/";//服务器地址
    private static final String REQUEST_MOTHOD = "POST";//发送方式
    private static final int TIME_OUT = 5000;
    private String ENCODETYPE = "UTF-8";//字符编码
    private static HttpRequest request;

    /**
     * 获取HttpRequest对象
     * @return
     */
    public static HttpRequest getInstance() {
        if(request == null) {
            return new HttpRequest();
        }
        return request;
    }

    /**
     * POST网络请求
     */
    public  <T> T httpPost(Map<String,String> strParam,Type typeOfT,String apiStr) throws IOException {


        String data = joinStringParam(strParam);//拼装参数

        HttpURLConnection connection = getConnection(apiStr);//获取连接
        connection.setRequestProperty("Content-Length", String.valueOf(data.getBytes().length));
        connection.connect();//建立连接
        OutputStream os = connection.getOutputStream();
        os.write(data.getBytes());
        os.flush();
        if (connection.getResponseCode() == 200) {
            // 获取响应的输入流对象
            InputStream is = connection.getInputStream();
            // 创建字节输出流对象
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 定义读取的长度
            int len = 0;
            // 定义缓冲区
            byte buffer[] = new byte[1024];
            // 按照缓冲区的大小，循环读取
            while ((len = is.read(buffer)) != -1) {
                // 根据读取的长度写入到os对象中
                baos.write(buffer, 0, len);
            }
            // 释放资源
            is.close();
            baos.close();
            connection.disconnect();
            // 返回字符串
            final String result = new String(baos.toByteArray());
            Gson gson = new Gson();
            return gson.fromJson(result, typeOfT);
        } else {
            connection.disconnect();
            return null;
        }
    }

    /**
     * 获取POST的Connection对象
     * @param apiStr
     * @return
     */
    private HttpURLConnection getConnection(String apiStr) {
        HttpURLConnection connection = null;
        // 初始化connection
        try {
            // 根据地址创建URL对象
            URL url = new URL(SERVER_URL+apiStr);
            // 根据URL对象打开链接
            connection = (HttpURLConnection) url.openConnection();
            // 设置请求的方式
            connection.setRequestMethod(REQUEST_MOTHOD);
            // 发送POST请求必须设置允许输入，默认为true
            connection.setDoInput(true);
            // 发送POST请求必须设置允许输出
            connection.setDoOutput(true);
            // 设置不使用缓存
            connection.setUseCaches(false);
            // 设置请求的超时时间
            connection.setReadTimeout(TIME_OUT);
            connection.setConnectTimeout(TIME_OUT);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Response-Type", "json");
            connection.setChunkedStreamingMode(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
    /**
     * 拼装字符串数据
     */
    private String  joinStringParam(Map<String,String> params) {

        //字符串构造对象
        StringBuilder builder = new StringBuilder();

        //拼装
        for(String s : params.keySet()) {
            builder.append(s+"=");
            try {
                builder.append(URLEncoder.encode(params.get(s), ENCODETYPE));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            builder.append("&");
        }
        return builder.substring(0,builder.length()-1);
    }

}
