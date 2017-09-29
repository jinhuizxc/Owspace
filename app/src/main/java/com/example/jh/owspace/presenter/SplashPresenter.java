package com.example.jh.owspace.presenter;

import com.example.jh.owspace.app.OwspaceApplication;
import com.example.jh.owspace.model.api.ApiService;
import com.example.jh.owspace.model.entity.SplashEntity;
import com.example.jh.owspace.util.NetUtil;
import com.example.jh.owspace.util.OkHttpImageDownloader;
import com.example.jh.owspace.util.TimeUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/28.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;
    private ApiService apiService;

    @Inject
    public SplashPresenter(SplashContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
        Logger.d("apiService:" + apiService);
    }

    @Override
    public void getSplash(String deviceId) {
        String client = "android";
        String version = "1.3.0";
        Long time = TimeUtil.getCurrentSeconds();
        apiService.getSplash(client, version, time, deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<SplashEntity>() {
                    @Override
                    public void onCompleted() {
                        Logger.e("load splash onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, "load splash failed:");
                    }

                    @Override
                    public void onNext(SplashEntity splashEntity) {
                        if (NetUtil.isWifi(OwspaceApplication.getInstance().getApplicationContext())) {
                            if (splashEntity != null) {
                                List<String> imgs = splashEntity.getImages();
                                for (String url : imgs) {
                                    OkHttpImageDownloader.download(url);
                                }
                            }
                        } else {
                            Logger.i("不是WIFI环境,就不去下载图片了");
                        }
                    }
                });
    }
}
