package com.example.archermind.tableshu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


/**
 * Created by bomei
 * date: 2018/11/27
 * 类描述：
 */

public class CellView extends AppCompatTextView{
    Rect mRect = new Rect();
    int board = 4;//px
    Paint mPaint;

    public CellView(Context context) {
        this(context, null);
    }

    public CellView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CellView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(board);
        mRect.set(0, 0, getMeasuredWidth() , getMeasuredHeight());
        canvas.drawRect(mRect, mPaint);
    }

}
