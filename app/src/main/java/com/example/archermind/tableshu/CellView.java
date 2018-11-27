package com.example.archermind.tableshu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;


/**
 * Created by bomei
 * date: 2018/11/27
 * 类描述：
 */

public class CellView extends android.support.v7.widget.AppCompatTextView{
    public CellView(Context context) {
        this(context, null);
    }

    public CellView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CellView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    

}
