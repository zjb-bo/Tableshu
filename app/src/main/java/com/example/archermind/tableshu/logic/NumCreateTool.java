package com.example.archermind.tableshu.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by archermind on 18-11-26.
 */

public class NumCreateTool {

    public static volatile NumCreateTool numLogic;

    private NumCreateTool() {
    }

    public static NumCreateTool getInstance(){
        if(numLogic == null){
            synchronized (NumCreateTool.class){
                if(numLogic == null){
                    numLogic = new NumCreateTool();
                }
            }
        }
        return numLogic;
    }

    public List<Integer> createRandomNum(int count){
        List<Integer> list = new ArrayList<>();
        for (int i1 = 1; i1 <= count * count; i1++) {
            list.add(i1);
        }
        Collections.shuffle(list);
        return list;
    }



}
