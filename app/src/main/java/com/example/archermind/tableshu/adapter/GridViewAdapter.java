package com.example.archermind.tableshu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.archermind.tableshu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archermind on 18-11-26.
 */

public class GridViewAdapter extends BaseAdapter {

    private List<Integer> datas = new ArrayList<>();
    private int mItemHeight;

    public void setItemHeight(int itemHeight) {
        mItemHeight = itemHeight;
    }

    public GridViewAdapter(int itemHeight) {
        mItemHeight = itemHeight;
    }

    public void setData(List<Integer> data) {
        datas.clear();
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
        if(convertView == null){
            ViewGroup.LayoutParams layoutParams =
                    new ViewGroup.LayoutParams(mItemHeight, mItemHeight);
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapater_item, parent, false);
            convertView.setLayoutParams(layoutParams);
        }
        ((TextView)convertView.findViewById(R.id.tv_item)).setText(String.valueOf(datas.get(position)));
        return convertView;
    }
}
