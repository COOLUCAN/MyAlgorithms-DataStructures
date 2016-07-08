package com.algorithmica.problems;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by chch0316 on 7/1/2016.
 */
/*
    Generate all possible binary numbers to the given integer N
 */
public class GenerateBinarySequence {

    public static void displayBS(int n){
        for (int i = 0; i < (1<<n); i++) {
            auxDisplay1(i);
            System.out.println();
            auxDisplay2(i,n);
            System.out.println();
        }

    }
    public static void auxDisplay1(int n){
        int mask = 0X80000000;
        for (int i = 0; i < 32 ; i++) {
            if((n&mask)==0)
                System.out.print(0);
            else
                System.out.print(1);
            mask= (mask >>> 1);
        }
    }
    public static void auxDisplay2(int digit,int n){
       int mask = 0b1;
        for (int i = 0; i < n-1; i++) {
            mask=mask<<1;
        }
        for (int i = 0; i < n ; i++) {
            if((digit&mask)==0)
                System.out.print(0);
            else
                System.out.print(1);
            mask= (mask >>> 1);
        }
    }

    //
    public static void displayBSBT1(int n){
        String s= "";
        auxdisplayBSBT1(0,n+1,s);
    }

    public static void auxdisplayBSBT1(int level,int depth, String s){
        if(level+1==depth){
            System.out.println(s);
            return;
        }
        //For terniary sequences increase the limit to (i<=2)
        for (int i = 0; i <=1 ; i++) {
            String backup = s;
            s+=i;
            auxdisplayBSBT1(level+1,depth,s);
            s= backup;
        }
    }

    public static void displayBSBT2(int n){
        int[] out = new int[n];
        auxdisplayBSBT2(0,n+1,out);
    }
    public static void auxdisplayBSBT2(int level,int depth, int[] out){
        if(level+1==depth){
            System.out.println(Arrays.toString(out));
            return;
        }
        //For terniary sequences increase the limit to (i<=2)
        for (int i = 0; i <=1 ; i++) {
            out[level]=i;
            auxdisplayBSBT2(level+1,depth,out);
        }
    }

    public static void main(String[] args) {
        //displayBS(4);
        //depth= n+1
        int input = Integer.parseInt(args[0]);
        displayBS(input);
        displayBSBT1(input);
        displayBSBT2(input);
    }


}
