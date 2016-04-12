package com.xiaodu.simplebehavior;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lipeng21 on 2016/4/11.
 */
public class SimpleTextViewBehavior extends SimpleViewBehavior<TextView>{

    private float mTargetTextSize;
    private float mStartTextSize;

    private int mTargetTextColor;
    private int mStartTextColor;

    /**
     * Creates a new behavior whose parameters come from the specified context and
     * attributes set.
     *
     * @param context the application environment
     * @param attrs   the set of attributes holding the target and animation parameters
     */
    public SimpleTextViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EasyCoordinatorView);
        mTargetTextSize = a.getDimension(R.styleable.EasyCoordinatorView_targetTextSize, UNSPECIFIED_INT);
        mTargetTextColor = a.getColor(R.styleable.EasyCoordinatorView_targetTextColor, UNSPECIFIED_INT);
    }

    @Override
    protected void prepare(CoordinatorLayout parent, TextView child, View dependency) {
        super.prepare(parent, child, dependency);
        mStartTextSize = child.getTextSize();
        mStartTextColor = child.getCurrentTextColor();
    }

    @Override
    public void updateViewWithPercent(TextView child, float percent) {
        super.updateViewWithPercent(child, percent);

        float newTextSize = (mTargetTextSize == UNSPECIFIED_INT ? mStartTextSize : mStartTextSize + (mTargetTextSize - mStartTextSize) * percent);

        child.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);


        if (mTargetTextColor != UNSPECIFIED_INT && mStartTextColor != 0) {
            ArgbEvaluator evaluator = new ArgbEvaluator();
            int color = (int) evaluator.evaluate(percent, mStartTextColor, mTargetTextColor);
            child.setTextColor(color);
        }
    }
}
