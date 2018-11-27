package com.example.archermind.tableshu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.archermind.tableshu.R;

/**
 * Created by bomei
 * date: 2018/11/27
 * 类描述：
 */

public class FlashActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
    }

    public void onClick(View view) {
        MainActivity.start(this);
    }
}
