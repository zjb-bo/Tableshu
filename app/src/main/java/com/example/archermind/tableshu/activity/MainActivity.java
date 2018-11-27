package com.example.archermind.tableshu.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.archermind.tableshu.R;
import com.example.archermind.tableshu.adapter.GridViewAdapter;
import com.example.archermind.tableshu.logic.NumCreateTool;
import com.example.archermind.tableshu.logic.RuleCheck;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView mGridView;
    TextView mTextView;
    TextView mTvCurrent;
    int count = 5;
    GridViewAdapter mGridViewAdapter;

    int mItemHeight;
    private List<Integer> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }


    private void initView() {
        mGridView = (GridView) findViewById(R.id.grid_view);
        mTvCurrent = (TextView) findViewById(R.id.tv_current);
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

    private void initData() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer integer = mDatas.get(position);
                RuleCheck.getInstance().eachClickRuleCheck(integer, new RuleCheck.RuleCheckResultListener() {
                    @Override
                    public void fail() {
                        showFinishDialog("You Loosed!", "You have do wrong click, have retry !");
                    }

                    @Override
                    public void success() {
                        showWinDialog("You Win!!!", " Configures You!");
                    }
                });
                mTvCurrent.setText(String.valueOf(integer));
            }
        });
    }

    private void showFinishDialog(String title, String msg) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setCancelable(false)
                .setMessage(msg)
                .setPositiveButton("Retry Go", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameRestart();
                    }
                })
                .setNegativeButton("Exit Bye", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }

    private void showWinDialog(String title, String msg) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setCancelable(false)
                .setMessage(msg)
                .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nextCar();
                    }
                })
                .setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameRestart();
                    }
                })
                .create()
                .show();
    }

    private void nextCar() {
        count ++;
        gameRestart();

    }

    private void gameRestart(){
        RuleCheck.getInstance().resetData();
        RuleCheck.getInstance().setCar(count);
        mTvCurrent.setText("GO");
        mItemHeight = mGridView.getMeasuredHeight() / count;
        mGridViewAdapter.setItemHeight(mItemHeight);
        setData();
    }

    private void setData() {
        mDatas = NumCreateTool.getInstance().createRandomNum(count);
        mGridViewAdapter.setData(mDatas);
    }


    private void setSize() {
        int measured = mGridView.getMeasuredWidth();
        ViewGroup.LayoutParams layoutParams = mGridView.getLayoutParams();
        layoutParams.height = measured;
        mGridView.setLayoutParams(layoutParams);
        mItemHeight = measured / count;
    }
}
