package com.example.archermind.tableshu.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.example.archermind.tableshu.Data;
import com.example.archermind.tableshu.R;
import com.example.archermind.tableshu.logic.NumCreateTool;
import com.example.archermind.tableshu.logic.RuleCheck;
import com.example.archermind.tableshu.logic.TimerLogic;
import com.example.archermind.tableshu.ui.DialogUI;
import com.example.archermind.tableshu.view.CellGroupView;
import com.example.archermind.tableshu.view.CellView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogUI.DialogListener {

    TextView mTvCurrent;
    TextView mCountTime;
    TextView mCarView;
    int countCar = 3;
    final int startCountCar = 2;

    private List<Integer> mDatas;
    private CellGroupView mCellGroupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setData();
        startTimer();
    }

    private void emitBarrage() {
        final TextView textView = new TextView(this);
        ((ViewGroup) getWindow().getDecorView()).addView(textView);

        int random = (int) (Math.random() * 10);
        int index = random % Data.danMu.length;

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
        layoutParams.setMargins(0, 300, 0, 0);
        textView.setLayoutParams(layoutParams);

        textView.setText(Data.danMu[index]);
        textView.setTextColor(Color.RED);
        textView.setTextSize(20);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 1000, 0, 0);
        textView.setAnimation(translateAnimation);
        translateAnimation.setDuration(5000);
        translateAnimation.start();
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ((ViewGroup) getWindow().getDecorView()).removeView(textView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void startTimer() {
        TimerLogic.getInstance().startTimer(new CountDownTimer((countCar * 5 + countCar)*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mCountTime.setText("倒计时："+(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                mCountTime.setText("倒计时：0s");
                DialogUI.showFinishDialog(MainActivity.this, "You Loosed!",
                        "Time Out!", MainActivity.this);
            }
        });
    }

    private void initView() {
        mTvCurrent = (TextView) findViewById(R.id.tv_current);
        mCountTime = (TextView) findViewById(R.id.countTimer);
        mCarView = (TextView) findViewById(R.id.carTextView);
        mCellGroupView = (CellGroupView) findViewById(R.id.cellviewgroup);
        mCellGroupView.removeAllViews();
    }

    @Override
    public void restartGame() {
        RuleCheck.getInstance().resetData();
        RuleCheck.getInstance().setCar(countCar);
        mTvCurrent.setText("GO");
        mCarView.setText("第"+(countCar - startCountCar)+"关");
        setData();
        startTimer();
    }

    @Override
    public void nextCar() {
        countCar++;
        restartGame();

    }

    private void setData() {
        RuleCheck.getInstance().resetData();
        RuleCheck.getInstance().setCar(countCar);
        changeData();
    }

    private void changeData() {
        mDatas = NumCreateTool.getInstance().createRandomNum(countCar);
        mCellGroupView.removeAllViews();
        mCellGroupView.setColumn(countCar);
        for (int i = 0; i < mDatas.size(); i++) {
            final CellView cellView = (CellView) LayoutInflater.from(this).inflate(R.layout.celleview_layout, mCellGroupView,false);
            cellView.setText(String.valueOf(mDatas.get(i)));
            mCellGroupView.addView(cellView);
            final int finalI = i;
            cellView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    changeData();  //点一次，变化一次
//                    if(finalI % 2 == 0){
                        emitBarrage();
//                    }
                    Integer integer = Integer.valueOf(cellView.getText().toString());
                    RuleCheck.getInstance().eachClickRuleCheck(integer, new RuleCheck.RuleCheckResultListener() {
                        @Override
                        public void fail() {
                            TimerLogic.getInstance().cancleTimer();
                            DialogUI.showFinishDialog(MainActivity.this, "失败了吧！",
                                    "哈哈，撑死你也就第"+(countCar - startCountCar)+"关了！", MainActivity.this);
                        }

                        @Override
                        public void success() {
                            TimerLogic.getInstance().cancleTimer();
                            DialogUI.showWinDialog(MainActivity.this, "你赢了！", "下一关让肉包子打你，无回！", MainActivity.this);
                        }
                    });
                    mTvCurrent.setText(String.valueOf(integer));
                }
            });
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

}
