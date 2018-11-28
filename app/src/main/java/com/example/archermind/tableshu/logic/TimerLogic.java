package com.example.archermind.tableshu.logic;

import android.os.CountDownTimer;

/**
 * Created by archermind on 18-11-28.
 */

public class TimerLogic {

    private static volatile TimerLogic timerLogic;
    private CountDownTimer mTimer;

    private TimerLogic() {
    }

    public static TimerLogic getInstance() {
        if (timerLogic == null) {
            synchronized (TimerLogic.class) {
                if (timerLogic == null) {
                    timerLogic = new TimerLogic();
                }
            }
        }
        return timerLogic;
    }


    public void startTimer(CountDownTimer timer) {
        mTimer = timer;
        mTimer.start();
    }


    public void cancleTimer(){
        if (mTimer != null) {
            mTimer.cancel();
        }
    }
}
