package com.algorithmica.problems.backtracking;

import java.util.Arrays;

/**
 * Created by chch0316 on 7/4/2016.
 */

/*
    Problem: Find an efficient algorithm to place N Queens in a NXN Chess board such that no Two Queens can kill each other
 */

public class QueensProblem {

    //Sol1: Brute force
    //Going through all paths and if it is valid printing at the end
    //TC --> O(N^N)
    public static void findPlacements1(int n){
        int[] out= new int[n];
        auxFindPlacements1(0,n,out);
    }

    public static void auxFindPlacements1(int level,int n,int[]out){
        if(level==n){
            if(isValid1(out))
                System.out.println(Arrays.toString(out));
            return;
        }
        for(int q=0; q< n;q++){
            out[level]=q;
            auxFindPlacements1(level+1,n,out);
        }
    }
    public static boolean isValid1(int[] out){
        for (int i = 1; i < out.length ; i++) {
            for (int j = 0; j < i ; j++) {
                //If both are same column or in diagonal --> not valid
               if(out[i]==out[j]|| Math.abs(i-j)==Math.abs(out[i]-out[j]))
                   return false;
            }
        }
        return true;
    }

    //Solution2 : Back tracking
    //Branch pruning if it is invalid
    public static void findPlacements2(int n){
        int[] out= new int[n];
        auxFindPlacements2(0,n,out);
    }
    public static void auxFindPlacements2(int level,int n,int[]out){
        if(level==n){
            System.out.println(Arrays.toString(out));
            return;
        }
        for(int q=0; q< n;q++){
            if(isValid2(level,out,q)){
                out[level]=q;
                auxFindPlacements2(level+1,n,out);
            }
            else continue;
        }
    }
    public static boolean isValid2(int level,int[] out, int q){
        for (int i = 0; i < level ; i++) {
                //If both are same column or in diagonal --> not valid
                if(out[i]==q|| Math.abs(i-level)==Math.abs(out[i]-q))
                    return false;
        }
        return true;
    }

    //Solution3 : Back tracking (Going through Half columns in row -0 and reverse the Founded solutions)
    //Branch pruning if it is invalid
    //
    public static void findPlacements3(int n){
        int[] out= new int[n];
        int row0Loop ;
        if(n%2==0)
            row0Loop = n/2;
        else
            row0Loop = n/2+1;
        int level=0;
        for(int q=0;q <row0Loop;++q){
            if(isValid3(level,out,q)){
                out[level]=q;
                auxFindPlacements3(level+1,n,out);
            }
            else continue;
        }

    }
    public static void auxFindPlacements3(int level,int n,int[]out){
        if(level==n){
            System.out.println(Arrays.toString(out));
            int [] rev = new int[out.length];
            for (int i = 0; i < out.length ; i++) {
                rev[i]= out[out.length-1-i];
            }
            System.out.println(Arrays.toString(rev));
            return;
        }
        for(int q=0; q< n;q++){
            if(isValid3(level,out,q)){
                out[level]=q;
                auxFindPlacements3(level+1,n,out);
            }
            else continue;
        }
    }
    public static boolean isValid3(int level,int[] out, int q){
        for (int i = 0; i < level ; i++) {
            //If both are same column or in diagonal --> not valid
            if(out[i]==q|| Math.abs(i-level)==Math.abs(out[i]-q))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        int n= Integer.parseInt(args[0]);
        long start= System.currentTimeMillis();
        findPlacements1(n);
        long end1= System.currentTimeMillis();
        System.out.println("Time taken: "+(end1-start));
        findPlacements2(n);
        long end2=System.currentTimeMillis();
        System.out.println("Time taken: "+(end2-end1));

        findPlacements3(n);
        long end3=System.currentTimeMillis();
        System.out.println("Time taken: "+(end3-end2));

    }


}
