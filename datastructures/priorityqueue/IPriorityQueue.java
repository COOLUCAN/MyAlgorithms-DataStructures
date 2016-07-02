package com.algorithmica.datastructures.priorityqueue;

/**
 * Created by ADMIN-COOLUCAN on 6/30/2016.
 */
public interface IPriorityQueue <T>{

    //Priority Queue is a ds which store the elements in ascending order.
    //Peek -- smallest element
    //
    public void add(T t);
    public T removeMin();
    public void display();
}
