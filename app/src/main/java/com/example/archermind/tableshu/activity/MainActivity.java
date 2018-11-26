package com.example.archermind.tableshu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.archermind.tableshu.R;
import com.example.archermind.tableshu.adapter.GridViewAdapter;
import com.example.archermind.tableshu.logic.NumCreateTool;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView mGridView;
    TextView mTextView;
    int count = 8;
    GridViewAdapter mGridViewAdapter;
    int mItemHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mGridView = (GridView) findViewById(R.id.grid_view);
        mGridView.setNumColumns(count);
        mGridView.post(new Runnable() {
            @Override
            public void run() {
                setSize();
                mGridViewAdapter = new GridViewAdapter(mItemHeight);
                mGridView.setAdapter(mGridViewAdapter);
                setData();
            }
        });
    }


    private void setData() {
        List<Integer> datas = NumCreateTool.getInstance().createRandomNum(count);
        mGridViewAdapter.addData(datas);
    }


    private void setSize() {
        int measured = mGridView.getMeasuredWidth();
        ViewGroup.LayoutParams layoutParams = mGridView.getLayoutParams();
        layoutParams.height = measured;
        mGridView.setLayoutParams(layoutParams);
        mItemHeight = measured / count;
    }
}
