package com.example.archermind.tableshu.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

/**
 * Created by archermind on 18-11-28.
 */

public class DialogUI {
    private static Dialog dialog;

    public static void showFinishDialog(final Context context, String title, String msg, @NonNull final DialogListener listener) {
        if(dialog != null && dialog.isShowing()){
            return;
        }
        dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setCancelable(false)
                .setMessage(msg)
                .setPositiveButton("再战一场", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.restartGame();
                    }
                })
                .setNegativeButton("洗洗睡了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((Activity)context).finish();
                    }
                })
                .create();
        dialog.show();
    }

    public static void showWinDialog(final Context context, String title, String msg, @NonNull final DialogListener listener) {
        if(dialog != null && dialog.isShowing()){
            return;
        }
        dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setCancelable(false)
                .setMessage(msg)
                .setPositiveButton("战，下一场", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.nextCar();
                    }
                })
                .setNegativeButton("再战一场", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.restartGame();

                    }
                })
                .create();
        dialog.show();
    }

    public interface DialogListener{
         void restartGame();
         void nextCar();
    }
}
