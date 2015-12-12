package com.zjxu.carpath.carpath.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zjxu.carpath.carpath.R;


public class MainActivity extends Activity implements View.OnClickListener {

    Context globalContext;
    EditText userName,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        globalContext = CarPathApplication.getGlobalContext();
    }

    //控件初始化
    public void initComponent(){
        userName = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(this);
    }


    //继承了View.OnClickListener的事件处理器
    @Override
    public void onClick(View v) {
        boolean success  =checkLogin();
        if (success){
            Intent ine = new Intent(this,HomeActivity.class);
            startActivity(ine);
            loginSuccess();
        }
    }

    /**登录检测
     *
     * @return success true : fail false
     */
    public boolean checkLogin(){
        //code
        return true;
    }

    public void loginSuccess(){
        SharedPreferences sp = getSharedPreferences("CarPath",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("islogin",true);
        editor.commit();
    }

    //暂时屏蔽菜单项
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
