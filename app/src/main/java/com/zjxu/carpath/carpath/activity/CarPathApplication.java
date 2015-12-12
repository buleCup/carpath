package com.zjxu.carpath.carpath.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by lang on 15/12/5.
 */
public class CarPathApplication extends Application {

    private static final CarPathApplication globalContext = new CarPathApplication();

    boolean islogin = false;

    public static CarPathApplication getGlobalContext(){
        return globalContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        if(checkLogin()){
            islogin = true;
            Intent ine = new Intent(CarPathApplication.this,HomeActivity.class);
            startActivity(ine);
        }
    }

    public boolean checkLogin(){
        SharedPreferences sp = getSharedPreferences("CarPath", MODE_PRIVATE);
        return sp.getBoolean("islogin",false);
    }
}
