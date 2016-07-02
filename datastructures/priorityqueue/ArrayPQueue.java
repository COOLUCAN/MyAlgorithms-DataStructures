package com.algorithmica.datastructures.priorityqueue;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by ADMIN-COOLUCAN on 6/30/2016.
 */

//Array based Implementation
    //add--O(n)
    //removeMin -- O(1)
public class ArrayPQueue<T> implements IPriorityQueue {

    T[] array;
    int noOfEles;
    ArrayPQueue(){
        //Defaultly implementing for 20 eles
        array = (T[])new Object[20];
        noOfEles =0;
    }
    @Override
    public void add(Object o) {
        if(isEmpty()){
            array[noOfEles++]= (T)o;
            return;
        }
        if(noOfEles==20)
            return;
        for (int i = noOfEles-1; i >=0 ; i--) {

            int x=((Comparable)(array[i])).compareTo((T)o);
            if(x<0){
                array[i+1]= array[i];
            }else{
                array[i+1]= (T)o;
                noOfEles++;
                break;
            }
        }
    }

    public boolean isEmpty(){
        if(noOfEles==0)
            return true;
        return false;
    }
    @Override
    public T removeMin() {
        return array[--noOfEles];
    }
    @Override
    public void display() {
        for (int i = 0; i < noOfEles; i++) {
            System.out.print(array[i]+ "\t");
        }
    }
}
