package com.example.jh.owspace.app;

import android.app.Application;
import android.content.Context;

import com.example.jh.owspace.BuildConfig;
import com.example.jh.owspace.R;
import com.example.jh.owspace.di.components.DaggerNetComponent;
import com.example.jh.owspace.di.components.NetComponent;
import com.example.jh.owspace.di.modules.NetModule;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by Administrator on 2017/9/28.
 */

public class OwspaceApplication extends Application {

    private static OwspaceApplication instance;
    private NetComponent netComponent;

    public static OwspaceApplication get(Context context){
        return (OwspaceApplication)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 初始化logger
        initLogger();
        // 初始化网络
        initNet();
        // 初始化数据库
        initDatabase();
        // 初始化字体
        initTypeFace();
    }

    private void initTypeFace() {
        CalligraphyConfig calligraphyConfig =new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/PMingLiU.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
        CalligraphyConfig.initDefault(calligraphyConfig);
    }

    private void initDatabase() {

    }

    private void initNet() {
        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build();
    }

    private void initLogger() {
        LogLevel logLevel;
        if (!BuildConfig.API_ENV){
            logLevel = LogLevel.FULL;
        }else{
            logLevel = LogLevel.NONE;
        }
        Logger.init("GithubOwspace")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .logLevel(logLevel) ;       // default LogLevel.FULL
    }

    public static OwspaceApplication getInstance() {
        return instance;
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}
