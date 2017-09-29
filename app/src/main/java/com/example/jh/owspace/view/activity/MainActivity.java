package com.example.jh.owspace.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jh.owspace.R;
import com.example.jh.owspace.model.entity.Event;
import com.example.jh.owspace.model.entity.Item;
import com.example.jh.owspace.presenter.MainContract;
import com.example.jh.owspace.util.tool.RxBus;
import com.example.jh.owspace.view.activity.BaseActivity;
import com.example.jh.owspace.view.fragment.LeftMenuFragment;
import com.example.jh.owspace.view.fragment.RightMenuFragment;
import com.example.jh.owspace.view.widget.VerticalViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends BaseActivity implements MainContract.View  {

    @Bind(R.id.view_pager)
    VerticalViewPager viewPager;
    private SlidingMenu slidingMenu;
    private LeftMenuFragment leftMenu;
    private RightMenuFragment rightMenu;

    private Subscription subscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // 初始化菜单
        initMenu();
        // 初始化页面
        initPage();
    }

    private void initPage() {
    }

    private void initMenu() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.setFadeEnabled(true);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.left_menu);
        leftMenu = new LeftMenuFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.left_menu, leftMenu).commit();
        slidingMenu.setSecondaryMenu(R.layout.right_menu);
        rightMenu = new RightMenuFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.right_menu, rightMenu).commit();
        subscription = RxBus.getInstance().toObservable(Event.class)
                .subscribe(new Action1<Event>() {
                    @Override
                    public void call(Event event) {
                        slidingMenu.showContent();
                    }
                });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showNoMore() {

    }

    @Override
    public void updateListUI(List<Item> itemList) {

    }

    @Override
    public void showOnFailure() {

    }

    @Override
    public void showLunar(String content) {

    }
}
