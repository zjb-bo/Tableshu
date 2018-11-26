package com.example.archermind.tableshu.adapter;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archermind on 18-11-26.
 */

public class GridViewAdapter extends BaseAdapter {

    private List<Integer> datas = new ArrayList<>();
    private int mItemHeight;

    public GridViewAdapter(int itemHeight) {
        mItemHeight = itemHeight;
    }

    public void addData(List<Integer> data) {
        datas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(parent.getContext());
        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(mItemHeight, mItemHeight);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setText(String.valueOf(datas.get(position)));
        return textView;
    }
}
