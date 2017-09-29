package com.example.jh.owspace.view.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by Administrator on 2017/9/28.
 */

public class FixedImageView extends AppCompatImageView {
    // 屏幕高度
    private int mScreenHeight;

    public FixedImageView(Context context) {
        super(context);
    }

    public FixedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 初始化
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mScreenHeight = getScreenWidthHeight(context)[1];
    }

    private int[] getScreenWidthHeight(Context context) {
        int[] arrayOfInt = new int[2];
        if (context == null)
            return arrayOfInt;
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int i = localDisplayMetrics.widthPixels;
        int j = localDisplayMetrics.heightPixels;
        arrayOfInt[0] = i;
        arrayOfInt[1] = j;
        return arrayOfInt;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int i = View.MeasureSpec.getSize(widthMeasureSpec);
        View.MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(i, this.mScreenHeight);
    }
}
