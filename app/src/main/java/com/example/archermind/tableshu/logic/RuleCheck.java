package com.example.archermind.tableshu.logic;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archermind on 18-11-26.
 */

public class RuleCheck {

    private static volatile RuleCheck mRuleCheck;
    private List<Integer> datas = new ArrayList<>();
    private int mCar = 5;

    private RuleCheck() {
    }

    public static RuleCheck getInstance(){
        if(mRuleCheck == null){
            synchronized (NumCreateTool.class){
                if(mRuleCheck == null){
                    mRuleCheck = new RuleCheck();
                }
            }
        }
        return mRuleCheck;
    }

    public void resetData(){
        datas.clear();
    }

    public void setCar(int car) {
        this.mCar = car;
    }

    public void eachClickRuleCheck(int data, @NonNull RuleCheckResultListener listener){
        datas.add(data);
        ruleCheck(data, listener);
    }

    private void ruleCheck(int data,@NonNull RuleCheckResultListener listener) {
        if(data != datas.size()){
            listener.fail();
        }
        if(mCar * mCar == datas.size()){
            listener.success();
        }
    }

    public interface RuleCheckResultListener {
        void fail();
        void success();
    }
}
