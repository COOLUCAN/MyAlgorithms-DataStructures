package com.algorithmica.datastructures.priorityqueue;

import java.util.ArrayList;

/**
 * Created by ADMIN-COOLUCAN on 6/30/2016.
 */
public class HeapPQueue<T> implements IPriorityQueue {

    ArrayList<T> arrList;
    int size;

    HeapPQueue(){
        arrList = new ArrayList<T>();
        size=0;
    }

    @Override
    public void add(Object o) {
        if(arrList.isEmpty()){
            arrList.add((T)o);
            size++;
            return;
        }
        arrList.add((T)o);
        while (!arrList.isEmpty()){
            int parent = (size-1)/2;
            int res = ((Comparable)arrList.get(parent)).compareTo(o);
            if(res >1){
                T s = arrList.get(parent);
                arrList.set(parent,(T)o);



            }

        }
    }

    @Override
    public Object removeMin() {
        return null;
    }

    @Override
    public void display() {

    }
    public boolean isEmpty(){
        if(size==0)
            return true;
        return false;
    }

}
