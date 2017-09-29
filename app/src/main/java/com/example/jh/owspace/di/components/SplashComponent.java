package com.example.jh.owspace.di.components;



import com.example.jh.owspace.di.modules.SplashModule;
import com.example.jh.owspace.di.scopes.UserScope;
import com.example.jh.owspace.view.activity.SplashActivity;

import dagger.Component;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/25
 * owspace
 */
@UserScope
@Component(modules = SplashModule.class,dependencies = NetComponent.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
