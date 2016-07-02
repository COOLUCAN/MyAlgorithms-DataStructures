package com.algorithmica.datastructures.priorityqueue;

import java.util.Random;

/**
 * Created by ADMIN-COOLUCAN on 6/30/2016.
 */
public class TestPQueue {
    public static void main(String[] args) {
        test2();
    }


    public static void test1(){
        IPriorityQueue<Integer> pq = new ArrayPQueue<Integer>();
        Random r = new Random();
        for (int i = 0; i < 10 ; i++) {
            int s= r.nextInt(100)+1;
            System.out.println(s);
            pq.add(s);
        }
        pq.display();
        System.out.println("Removed ele is :" +pq.removeMin());
        pq.display();
        System.out.println("Removed ele is :" +pq.removeMin());
        pq.display();
    }

    public static void test2(){
        IPriorityQueue<Integer> pq = new HeapPQueue<Integer>();
        Random r = new Random(56);
        for (int i = 0; i < 10 ; i++) {
            int s= r.nextInt(100)+1;
            System.out.println(s);
            pq.add(s);
        }
        pq.display();
        System.out.println("Removed ele is: "+ pq.removeMin());
        pq.display();
        System.out.println("Removed ele is: "+ pq.removeMin());
        pq.display();

    }
}
