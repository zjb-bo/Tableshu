package com.example.archermind.tableshu.logic;

/**
 * Created by archermind on 18-11-26.
 */

public class RuleCheck {

    public static volatile RuleCheck mRuleCheck;

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



}
