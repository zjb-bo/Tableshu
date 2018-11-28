package com.example.archermind.tableshu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by archermind on 18-11-28.
 */

public class CellGroupView extends ViewGroup{
    private int mColumn = 5;
    private int mItemHeight = 0;
    private Paint mPaint;
    private Rect mRect = new Rect();
    private int board = 10;

    public CellGroupView(Context context) {
        this(context, null);
    }

    public CellGroupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CellGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(board*2);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        int measuredWidth = getMeasuredWidth() - board * 2;
        mItemHeight = measuredWidth / mColumn;
        int measureSpec = MeasureSpec.makeMeasureSpec(mItemHeight, MeasureSpec.EXACTLY);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(measureSpec, measureSpec);
        }
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int measuredHeight = child.getMeasuredHeight();
            l += measuredHeight;
            if(i % mColumn == 0 && i > 0){
                t = measuredHeight * (i / mColumn) + board;
                l = board;
            }else if(i == 0){
                l = board;
                t = board;
            }
            b = t + measuredHeight;
            r = l + measuredHeight;
            child.layout(l, t, r, b);
        }
    }

    public void setColumn(int column){
        this.mColumn = column;
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mRect.set(0, 0, getMeasuredHeight(), getMeasuredHeight());
        canvas.drawRect(mRect, mPaint);
        super.onDraw(canvas);
    }
}
