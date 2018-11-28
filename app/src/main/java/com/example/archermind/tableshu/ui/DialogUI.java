package com.example.archermind.tableshu.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

/**
 * Created by archermind on 18-11-28.
 */

public class DialogUI {

    public static void showFinishDialog(final Context context, String title, String msg, @NonNull final DialogListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setCancelable(false)
                .setMessage(msg)
                .setPositiveButton("Retry Go", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.restartGame();
                    }
                })
                .setNegativeButton("Exit Bye", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((Activity)context).finish();
                    }
                })
                .create()
                .show();
    }

    public static void showWinDialog(final Context context, String title, String msg, @NonNull final DialogListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setCancelable(false)
                .setMessage(msg)
                .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.nextCar();
                    }
                })
                .setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.restartGame();

                    }
                })
                .create()
                .show();
    }

    public interface DialogListener{
         void restartGame();
         void nextCar();
    }
}
