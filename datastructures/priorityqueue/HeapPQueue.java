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
        int current =size;
        while (!arrList.isEmpty()){
            int parent = (current-1)/2;
            int res = ((Comparable)arrList.get(parent)).compareTo(o);
            if(res > 0){
                T s = arrList.get(parent);
                arrList.set(parent,(T)o);
                arrList.set(current,s);
                current=parent;
                if(current==0)
                    break;
            }else {
                break;
            }
        }
        size++;
    }

    @Override
    public T removeMin() {
        T res = arrList.get(0);
        int current = 0;

        while (true) {
            int left = (current * 2) + 1;
            int right = (current * 2) + 2;

            if(left >= size && right >= size){
                arrList.remove(current);
                size--;
                break;
            }
            if(left >= size){
                arrList.set(current, arrList.get(left));
                arrList.remove(left);
                size--;
                break;
            }
            int x = ((Comparable) arrList.get(left)).compareTo(arrList.get(right));
            if (x < 0) {
                arrList.set(current, arrList.get(left));
                current = left;
            }
            else {
                arrList.set(current, arrList.get(right));
                current = right;
            }
        }
        return res;
    }

    @Override
    public void display() {
        for (T a: arrList ) {
            System.out.print(a+"\t");
        }
    }
    public boolean isEmpty(){
        if(size==0)
            return true;
        return false;
    }

    @Override
    public Object findMin() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
