package com.algorithmica.dynamicprogramming;

import java.util.Random;

/**
 * Created by chch0316 on 6/28/2016.
 */

public class MaxItemsInGrid {

    //To Store the count of max no of items and path for it
    public static int maxSum =0;
    public static String maxSumPath = "";

    // Here n in Time and Space analysis indicates no of input elements in a matrix i.e in a m*n matrix = m*n elements

    public static void auxMaxItemsCollected1(int i,int j,int[][] in,int pathSum,String path){
        if(i >= in.length || j>= in[0].length)
            return;
        if(i== in.length-1 && j== in[0].length-1)
        {
            pathSum +=  in[i][j];
            if(maxSum < pathSum) {
                maxSum = pathSum;
                maxSumPath = path;
            }
            System.out.println(path+" : " +pathSum);
            return;
        }
        auxMaxItemsCollected1(i,j+1,in,pathSum+in[i][j], (path+ "--> ("+i+","+(j+1)+")"));
        auxMaxItemsCollected1(i+1,j,in,pathSum+in[i][j], (path+ "--> ("+(i+1)+","+(j)+")"));
    }

    //Solution 1: Finding all possible paths
    //If it is max, storing in global static variables
    //TC--> O(2^n) && SC --> O(n)
    public static void findMAxItems1(int[][] in){
        auxMaxItemsCollected1(0,0,in,0,"(0,0)");
        System.out.println("1. The Maximum items can be collected in following path : ");
        System.out.println(maxSumPath + " : " + maxSum);
    }

    public static int auxMaxItemsCollected2(int i,int j,int[][] in,int pathSum){
        if(i >= in.length || j>= in[0].length)
            return 0;
        int right = auxMaxItemsCollected2(i,j+1,in,pathSum);
        int bottom = auxMaxItemsCollected2(i+1,j,in,pathSum);
        pathSum = Math.max(right,bottom)+in[i][j];
        return pathSum;
    }
    //Solution 2: finding only max count
    //TC--> O(2^n) && SC --> O(n)
    public static void findMAxItems2(int[][] in){
        System.out.println("2. Max Items can be collected : "+auxMaxItemsCollected2(0,0,in,0));
    }

    public static int auxMaxItemsCollected3(int i,int j,int[][] in,int pathSum,int[][] mem){
        if(i >= in.length || j>= in[0].length)
            return 0;
        int right = ((j+1)<in[0].length && mem[i][j+1]!=0)?mem[i][j+1]:auxMaxItemsCollected3(i,j+1,in,pathSum,mem);
        int bottom = ((i+1)<in.length && mem[i+1][j]!=0)?mem[i+1][j]:auxMaxItemsCollected3(i+1,j,in,pathSum,mem);
        mem[i][j] = Math.max(right,bottom)+in[i][j];
        return mem[i][j];
    }
    //Solution 3: Memoization (recursion+memory) Top-down approach
    //TC--> O(n) && SC--> O(n)
    public static void findMAxItems3(int[][] in){
        int[][] mem = new int[in.length][in[0].length];
        System.out.println("3. Max Items can be collected : "+auxMaxItemsCollected3(0,0,in,0,mem));
    }

    //Solution 4 : Dynamic programming (bottom-top approach)
    //TC--> O(n) and Sc--> O(n)
   public static int findMAxItems4(int[][] in){
        int[][] mem = new int[in.length][in[0].length];

        for (int i = 0; i < in.length ; i++) {
            for (int j = 0; j < in[0].length ; j++) {
                int a = (i<=0)?0:mem[i-1][j];
                int b = (j<=0)?0:mem[i][j-1];
                mem[i][j] = Math.max(a,b)+in[i][j];
            }
        }
        return mem[in.length-1][in[0].length-1];
    }

    public static void main(String[] args){

        Integer rows = Integer.parseInt(args[0]);
        Integer cols = Integer.parseInt(args[1]);
        int[][] in = new int[rows][cols];

        Random r = new Random(30);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                in[i][j] = r.nextInt(20)+1;
                System.out.print(in[i][j]+"\t");
            }
            System.out.println();
        }
        findMAxItems1(in);
        findMAxItems2(in);
        findMAxItems3(in);
        System.out.println("4. Max items can be collected : "+findMAxItems4(in));
    }
}
