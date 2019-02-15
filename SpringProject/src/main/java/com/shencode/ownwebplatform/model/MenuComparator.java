package com.shencode.ownwebplatform.model;

import com.shencode.ownwebplatform.entity.Menu;

import java.util.Comparator;

public class MenuComparator implements Comparator<Menu> {
    @Override
    public int compare(Menu o1, Menu o2) {
        Integer pid1=o1.getPid();
        Integer pid2=o2.getPid();
        if(pid1==null&&pid2==null)return 0;
        if(pid1==null)return -1;
        if(pid2==null)return 1;
        if(pid1==pid2){
            Integer order1=o1.getShowOrder();
            Integer order2=o2.getShowOrder();
            if(order1==null&&order2==null)return 0;
            if(order1==null)return -1;
            if(order2==null)return 1;
            return order1.compareTo(order2);
        }else{
            return pid1.compareTo(pid2);
        }
    }
}
