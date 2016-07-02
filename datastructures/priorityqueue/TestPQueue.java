package com.algorithmica.datastructures.priorityqueue;

import java.util.Random;

/**
 * Created by ADMIN-COOLUCAN on 6/30/2016.
 */
public class TestPQueue {
    public static void main(String[] args) {
        test1();
    }


    public static void test1(){
        IPriorityQueue<Integer> pq = new ArrayPQueue<Integer>();
        Random r = new Random(40);
        for (int i = 0; i < 15 ; i++) {
            pq.add(r.nextInt(34)+1);
        }
        pq.display();
        System.out.println("Removed ele is :" +pq.removeMin());
        pq.display();
        System.out.println("Removed ele is :" +pq.removeMin());
        pq.display();
    }
}
